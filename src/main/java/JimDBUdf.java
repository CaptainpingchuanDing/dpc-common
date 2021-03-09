import com.jd.jim.cli.Cluster;
import com.jd.jim.cli.ReloadableJimClientFactory;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorConverters;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

/**
 * Created by zhentingzhang on 2018/4/3.
 */
public class JimDBUdf extends GenericUDF {

    private boolean inited = false;

    private String keyPrefix;

    private Cluster jimClient;

    private ObjectInspectorConverters.Converter keyConverter;

    private ObjectInspectorConverters.Converter valueConverter;

    private ObjectInspectorConverters.Converter prefixConverter;

    private ObjectInspectorConverters.Converter urlConverter;

    private int cnt = 0;

    @Override
    public ObjectInspector initialize(ObjectInspector[] args) throws UDFArgumentException {

        keyConverter = ObjectInspectorConverters.getConverter(args[0], PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        valueConverter = ObjectInspectorConverters.getConverter(args[1], PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        prefixConverter = ObjectInspectorConverters.getConverter(args[2], PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        urlConverter = ObjectInspectorConverters.getConverter(args[3], PrimitiveObjectInspectorFactory.javaStringObjectInspector);

        return PrimitiveObjectInspectorFactory.javaStringObjectInspector;
    }

    private void init(String clientUrl) {
        ReloadableJimClientFactory clientFactory = new ReloadableJimClientFactory();
        clientFactory.setJimUrl(clientUrl);
        jimClient = clientFactory.getClient();
    }

    @Override
    public Object evaluate(DeferredObject[] args) throws HiveException {
        if (args == null || args.length < 4) {
            throw new HiveException("Args: (key, value, jimdb_url)");
        }

        if (!inited) {
            inited = true;
            keyPrefix = (String) prefixConverter.convert(args[2].get());
            String jimDBClientUrl = (String) urlConverter.convert(args[3].get());
            init(jimDBClientUrl);
            System.err.println("Initialized");
        }

        String key = (String) keyConverter.convert(args[0].get());
        String value = (String) valueConverter.convert(args[1].get());

        if (key == null || key.isEmpty() || value == null || value.isEmpty()) {
            return "0";
        }

        key = keyPrefix + key;
        jimClient.del(key);
        jimClient.set(key, value);

        if ((cnt % 1000) == 0) {
            System.err.println(key + " " + value);
        }
        cnt++;

        return "1";
    }

    @Override
    public String getDisplayString(String[] arg0) {
        return "JimUDF";
    }
}

package pers.dingpingchuan.hive.udf;

import com.jd.jimi3.kbqa.service.KbqaService;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorConverters;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class DeleteAttrESDataEveryDayUDF extends GenericUDF {



    private static ApplicationContext applicationContext;
    private static KbqaService kbqaService;
    private ObjectInspectorConverters.Converter primary_key;


    static {
        applicationContext = new ClassPathXmlApplicationContext("spring/spring-base.xml");
        System.err.println("spring start up");
        kbqaService = (KbqaService) applicationContext.getBean("kbqaService");
        System.err.println("obtain kbqaService successful");
    }


    @Override
    public ObjectInspector initialize(ObjectInspector[] args) {
        primary_key = ObjectInspectorConverters.getConverter(args[0], PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        return PrimitiveObjectInspectorFactory.javaStringObjectInspector;
    }


    @Override
    public Object evaluate(DeferredObject[] args) throws HiveException {

        if (args == null || args.length < 1) {
            throw new HiveException("Args: is illegal");
        }
        String result = "";
        String id = null;
        try {
            id = (String) primary_key.convert(args[0].get());
            if(StringUtils.isNotBlank(id)){
                Map<String, String> condition = new HashMap<>();
                condition.put("primary_key", id);
//                kbqaService.deleteAttrData(condition);
            }
        } catch (Exception e) {
            result = String.format("delete data id={} met error", id);
        }
        return result;
    }

    @Override
    public String getDisplayString(String[] strings) {
        return "DeleteAttrESDataEveryDayUDF";
    }

}

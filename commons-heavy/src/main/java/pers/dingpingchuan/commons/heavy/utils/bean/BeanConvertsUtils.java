package pers.dingpingchuan.commons.heavy.utils.bean;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanConvertsUtils {

    private Object dest;

    public static String test(String s) {
        return s;
    }

    public static void convert(Object a, Object b) {
//        ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
//        BeanB b = (BeanB) convertUtilsBean.convert(object, BeanB.class);
        try {
            BeanUtils.copyProperties(a, b);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public static void copySameProperties(Object dest, Object orig) {
        if (dest == null) {
            throw new IllegalArgumentException("No destination bean specified");
        } else if (orig == null) {
            throw new IllegalArgumentException("No origin bean specified");
        } else {
            try {
                Field[] destFields = dest.getClass().getDeclaredFields();
                Field[] origFields = orig.getClass().getDeclaredFields();
                Map<String, Object> originFieldValue = new HashMap<String, Object>();
                for (Field origField : origFields) {
                    String fieldGetName = parGetName(origField.getName());
                    Method fieldGetMet = orig.getClass().getMethod(fieldGetName, new Class[]{});
                    Object fieldVal = fieldGetMet.invoke(orig, new Object[]{});
//                    originFieldValue.put(originFieldValue)
                }
            } catch (Exception e) {

            }
        }
    }

    public static Map<String, String> getFieldValueMap(Object bean) {
        Class<?> cls = bean.getClass();
        Map<String, String> valueMap = new HashMap<String, String>();
        Method[] methods = cls.getDeclaredMethods();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            try {
                String fieldType = field.getType().getSimpleName();
                System.out.println("fieldType: " + fieldType);
                String fieldGetName = parGetName(field.getName());
//                if (!checkGetMet(methods, fieldGetName)) {
//                    continue;
//                }
                Method fieldGetMet = cls.getMethod(fieldGetName, new Class[]{});
                Object fieldVal = fieldGetMet.invoke(bean, new Object[]{});

                String result = String.valueOf(fieldGetName + ": " + fieldVal);
                System.out.println(result);
                System.out.println();
//                if ("Date".equals(fieldType)) {
//                    result = fmtDate((Date) fieldVal);
//                } else {
//                    if (null != fieldVal) {
//                        result = String.valueOf(fieldVal);
//                    }
//                }
                valueMap.put(field.getName(), result);
            } catch (Exception e) {
                continue;
            }
        }
        return valueMap;
    }

    public static String parGetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_')
            startIndex = 1;
        return "get"
                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
                + fieldName.substring(startIndex + 1);
    }


    public static void main(String[] args)throws Exception {

        BeanA beanA = new BeanA();

        beanA.setAge(2);
        beanA.setName("ff");
        beanA.setComment("dsad");
        beanA.setSex("男");
        System.out.println(beanA);

//        getFieldValueMap(beanA);
        BeanB b = new BeanB();
//        BeanConvertsUtils.copySameProperties(b, beanA);
//        System.out.println(b);
//        BeanB b = (BeanB) ConvertUtils.convert(beanA.toString(), BeanB.class);
        PropertyUtils.copyProperties(b,beanA);
        System.out.println(b);
    }

}

class BeanA {
    private String name;
    private int age;
    private String sex;
    private String comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "BeanA{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}

class BeanB {
    private String name;
    private int age;
    private String sex;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "BeanB{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}



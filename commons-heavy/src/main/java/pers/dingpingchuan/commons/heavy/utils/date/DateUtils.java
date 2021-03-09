package pers.dingpingchuan.commons.heavy.utils.date;

import java.util.Date;

public class DateUtils {

    public static String formateDate(Date date){
        String result = "";
        if(date!=null){
            result = date.toString();
        }
        return result;
    }

    public static void main(String[] args) {
        String dateStr = DateUtils.formateDate(new Date());
        System.out.println(dateStr);
    }
}

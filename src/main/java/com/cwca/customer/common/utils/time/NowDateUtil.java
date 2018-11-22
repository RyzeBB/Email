package com.cwca.customer.common.utils.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NowDateUtil {
    public static String getNowDate(){
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.MONTH) + 1;
        String newDate = ""+calendar.get(Calendar.YEAR)+i+calendar.get(Calendar.DATE);
        return newDate;
    }
    public static Boolean compareDate(String str1,String str2,String nowStr){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
        try {
            Date date1 = sdf.parse(str1);
            Date date2 = sdf.parse(str2);
            Date nowDate = sdf.parse(nowStr);
            System.out.println(nowDate.after(date1));
            System.out.println(nowDate.before(date2));
            if(nowDate.after(date1) && nowDate.before(date2))
                return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args) {

        System.out.println(compareDate("","","2018-02-22"));
    }
}

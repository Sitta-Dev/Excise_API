package com.xcs.phase2.units;

import org.springframework.util.StringUtils;

import java.util.Calendar;

public class StringUtil {

    public static String checkNull(String str){
        return !StringUtils.isEmpty(str) ? str : "";
    }

    public static String getYear(){
        int year = Calendar.getInstance().get(Calendar.YEAR)+543;
        String strYear =""+year;
        return strYear.substring(2, 4);
    }

}

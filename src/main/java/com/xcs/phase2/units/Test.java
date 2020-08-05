package com.xcs.phase2.units;

import java.util.Calendar;

public class Test {

    public static void main(String[] args){
        int year = Calendar.getInstance().get(Calendar.YEAR)+543;
        String text=""+year;
        String t1=text.substring(2, 4);
        System.out.println(t1);
    }
}

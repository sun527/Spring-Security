package com.shangma.cn.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Number {

    public static void main(String[] args) {
        method2();
    }

    public static void method1(){
        NumberFormat numberFormat = new DecimalFormat(".###");
        String format = numberFormat.format(1256.265214);
        System.out.println(format);
    }

    public static void method2(){
        NumberFormat numberFormat = new DecimalFormat(".##%");
        String format = numberFormat.format(0.2627625);
        System.out.println(format);
    }

    public static void method3(){
        NumberFormat numberFormat = new DecimalFormat("###,###.####");
        double num = 78523694522.1374568;
        String format = numberFormat.format(num);
        System.out.println(format);
    }
}

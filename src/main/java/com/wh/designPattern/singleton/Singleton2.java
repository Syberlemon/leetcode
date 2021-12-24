package com.wh.designPattern.singleton;

/**
 * Created on 2021-09-07
 *
 * @author wanghao1
 */

public class Singleton2 {
    private static final Singleton2 instance;
    static {
        instance = new Singleton2();
    }

    public static Singleton2 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        Singleton2 s1 = Singleton2.getInstance();
        Singleton2 s2 = Singleton2.getInstance();
        System.out.println(s1 == s2);
    }
}

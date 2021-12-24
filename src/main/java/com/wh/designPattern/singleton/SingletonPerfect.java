package com.wh.designPattern.singleton;

/**
 * Created on 2021-09-09
 *
 * @author wanghao1
 */

/**
 * 静态内部类
 * 将实例初始化放在静态内部类里
 * 静态内部类在外部类加载的时候 是不会初始化的，当调用getInstance时才实例化
 * jvm保证加载class时，仅加载一次
 */
public class SingletonPerfect {

    private SingletonPerfect(){}

    private static class SingletonHolder{
        private static SingletonPerfect instance = new SingletonPerfect();
    }

    public static SingletonPerfect getInstance(){
        return SingletonHolder.instance;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++) {
            new Thread(()-> System.out.println(SingletonPerfect.getInstance().hashCode())).start();
        }
    }
}

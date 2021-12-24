package com.wh.designPattern.singleton;


/**
 * 总结：饿汉 懒汉加锁 懒汉加锁双重检查 静态内部类 枚举
 */

public class Singleton {

    /**
     *  注意final，容易落了
     *  饿汉式
     *  类加载到内存后，就实例化一个单例，JVM保证线程安全
     *  简单实用，推荐使用！
     *  唯一缺点：不管用到与否，类装载时就完成实例化
     *  （话说你不用的，你装载它干嘛）
     */
    private static final Singleton instance = new Singleton();
    private Singleton(){}
    //获取实例方法静态，则不需要new对象
    public static Singleton getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1 == s2);
    }
}

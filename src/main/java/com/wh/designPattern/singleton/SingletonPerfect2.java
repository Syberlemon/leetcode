package com.wh.designPattern.singleton;

/**
 * Created on 2021-09-09
 *
 * @author wanghao1
 */

/**
 * 枚举单例
 * effective java作者给的例子
 * 不仅可以解决线程同步，还可以防止反序列化
 * 为什么要防止反序列化：因为反序列化能new实例
 * java语言规定枚举类没有构造方法，枚举本质是abstract class，所以反序列化后只有枚举里面的对象，跟类初始化创建的是同一个对象
 */
public enum SingletonPerfect2 {

    INSTANCE;

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++) {
            new Thread(()-> System.out.println(SingletonPerfect2.INSTANCE.hashCode())).start();
        }
    }
}

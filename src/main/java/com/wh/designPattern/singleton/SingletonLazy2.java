package com.wh.designPattern.singleton;


/**
 * Created on 2021-09-07
 *
 * @author wanghao1
 */

public class SingletonLazy2 {

    /**
     * 懒汉式
     * 加锁解决线程不安全，但是效率会下降
     * 锁是synchronized，锁定的是类的对象
     */
    private static SingletonLazy2 instance;
    private SingletonLazy2(){}
    //获取实例方法静态，则不需要new对象
    public static synchronized SingletonLazy2 getInstance(){
        if(instance == null){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new SingletonLazy2();
        }
        return instance;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++) {
            new Thread(()-> System.out.println(SingletonLazy3.getInstance().hashCode())).start();
        }
    }
}

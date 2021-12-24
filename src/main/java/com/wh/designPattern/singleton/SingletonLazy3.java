package com.wh.designPattern.singleton;


/**
 * Created on 2021-09-07
 *
 * @author wanghao1
 */

public class SingletonLazy3 {

    /**
     * 懒汉式 加锁 还试图保证效率
     *
     */
    private static SingletonLazy3 instance;
    private SingletonLazy3(){}
    //获取实例方法静态，则不需要new对象
    public static SingletonLazy3 getInstance(){
        if(instance == null){
            //妄图通过减小同步代码块的方式提高效率，然后不可行..等于没锁
            synchronized(SingletonLazy3.class){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                instance = new SingletonLazy3();
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++) {
            new Thread(()-> System.out.println(SingletonLazy3.getInstance().hashCode())).start();
        }
    }
}

package com.wh.designPattern.singleton;


/**
 * Created on 2021-09-07
 *
 * @author wanghao1
 */

public class SingletonLazy4 {

    /**
     * 懒汉式 加锁 还试图保证效率
     * 双重检查 需要是volatile
     * jvm对java语言汇编优化有一个重排，尤其是jit
     * JIT里有语句重排，如果不加volatile，会在还没有初始化的时候就返回instance
     * 测试的时候发现不加没问题是因为没有进行jit的优化
     */
    private static volatile SingletonLazy4 instance;
    private SingletonLazy4(){}
    //获取实例方法静态，则不需要new对象
    public static SingletonLazy4 getInstance(){
        //第一次判断是有必要的，避免产生不必要的锁
        if(instance == null){
            synchronized(SingletonLazy4.class){
                if(instance == null){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new SingletonLazy4();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++) {
            new Thread(()-> System.out.println(SingletonLazy4.getInstance().hashCode())).start();
        }
    }
}

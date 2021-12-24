package com.wh.designPattern.singleton;


/**
 * Created on 2021-09-07
 *
 * @author wanghao1
 */

public class SingletonLazy {

    /**
     * 懒汉式 引入了线程不安全
     * 构造方法私有化 new不出来
     * 变量加不了final，因为final必须初始化
     */
    private static SingletonLazy instance;
    private SingletonLazy(){}
    //获取实例方法静态，则不需要new对象
    public static SingletonLazy getInstance(){
        if(instance == null){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new SingletonLazy();
        }
        return instance;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++) {
//            new Thread(()-> System.out.println(SingletonLazy3.getInstance().hashCode())).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    /**
                     * 这里写 new Runn 会有提示 打出来这段
                     * 这里new的是实现了Runnable接口的匿名内部类
                     * 因为只有一个方法，lamda表达式可以简写
                     * 同一个类的hashCode不同的说明是不同对象，这里也可以打印地址
                     * hashCode相同不能保证是同一个对象，是因为hashCode的最大值是int类型的最大值，是有一定范围的
                     * 但内存中的对象比这大得多，所以当对象数量超出这个范围就会出现hashCode相同但为两个不同的对象
                     */
                    System.out.println(SingletonLazy3.getInstance().hashCode());
                }
            }).start();
        }
    }
}

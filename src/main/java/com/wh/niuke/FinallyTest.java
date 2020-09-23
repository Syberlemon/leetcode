package com.wh.niuke;

/**
 * 测试finally语句什么时候执行: try和catch中的return的结果暂存，去执行finally，然后再将暂存结果return
 */

public class FinallyTest {

    public static void main(String[] args) {
//        System.out.println(beforeFinally());
        FinallyTest test = new FinallyTest();
        int c = test.add(9,34);
        System.out.println("和是："+c);
    }

    public static int beforeFinally(){
        int a = 0;
        try{
            a = 1;
            System.out.println("a:"+a);
            return a;
        }finally {
            a = 2;
            System.out.println("a:"+a);
        }
    }

    public int add(int a, int b){
        try{
            return a+b;
        } catch (Exception e){
            System.out.println("catch 语句块");
        } finally {
            System.out.println("finally 语句块");
        }
        return 0;
    }
}

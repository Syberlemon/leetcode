package com.wh.designPattern.strategy;

/**
 * Created on 2021-09-09
 *
 * @author wanghao1
 * 函数式接口 里头只能有一个abstract方法,可以有其他default方法
 */
@FunctionalInterface
public interface Comparator<T> {
    int compare(T o1, T o2);

    default void m(){
        System.out.println("default m");
    }
}

package com.wh.designPattern.factory;

/**
 * Created on 2021-09-09
 *
 * @author wanghao1
 */

public class Plane implements Moveable{

    @Override
    public void go() {
        System.out.println("plane go wengwengweng");
    }
}

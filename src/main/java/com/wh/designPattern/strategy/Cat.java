package com.wh.designPattern.strategy;

/**
 * Created on 2021-09-09
 *
 * @author wanghao1
 */

public class Cat implements Comparable<Cat>{
    private int weight;
    private int height;

    public Cat(int weight){
        this.weight = weight;
    }

    public Cat(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

    //    public int compareTo(Cat c){
//        if(this.weight > c.weight){
//            return 1;
//        } else if(this.weight < c.weight){
//            return -1;
//        } else {
//            return 0;
//        }
//    }


    @Override
    public String toString() {
        return "Cat{" +
                "weight=" + weight +
                ", height=" + height +
                '}';
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int compareTo(Cat c) {
        if(this.weight > c.weight){
            return 1;
        } else if(this.weight < c.weight){
            return -1;
        } else {
            return 0;
        }
    }
}

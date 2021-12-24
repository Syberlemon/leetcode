package com.wh.designPattern.strategy;

/**
 * Created on 2021-09-09
 *
 * @author wanghao1
 */

public class Dog implements Comparable<Dog> {
    int weight;

    public Dog(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Dog c) {
        if(this.weight > c.weight){
            return 1;
        } else if(this.weight < c.weight){
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Dog{" +
                "weight=" + weight +
                '}';
    }
}

package com.wh.designPattern.strategy;

/**
 * Created on 2021-09-09
 *
 * @author wanghao1
 */

public class CatHeightComparator implements Comparator<Cat> {

    @Override
    public int compare(Cat o1, Cat o2) {
        if(o1.getHeight() > o2.getHeight()){
            return 1;
        } else if(o1.getHeight() < o2.getHeight()){
            return -1;
        } else {
            return 0;
        }
    }
}

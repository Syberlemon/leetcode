package com.wh.PageReplacementAlgorithm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU: Least Recently Used 最近最少使用
 */

public class LRU<k, v> extends LinkedHashMap<k, v> {
    private final int MAX_SIZE;
    public LRU(int capacity){
        super(8, 0.75f, true);
        this.MAX_SIZE = capacity;
    }

    @Override
    public boolean removeEldestEntry(Map.Entry<k, v> eldest){
        if(size() > MAX_SIZE){
            System.out.println("移除的元素为："+eldest.getValue());
        }
        return size() > MAX_SIZE;
    }

    public static void main(String[] args){
        Map<Integer, Integer> map = new LRU<>(5);
        for(int i = 1; i <= 11; i++) {
            map.put(i, i);
            System.out.println("cache的容量为：" + map.size());
//          为了测试，在加入元素4的时候，访问了一下元素1，然后看到，在缓存达到限制时，最先移除的不是1，而是2，3然后是1
            if (i == 4) {
                map.get(1);
            }
        }
        System.out.println("map元素：");
        map.entrySet().forEach(integerIntegerEntry -> System.out.println(integerIntegerEntry.getValue()));
    }
}

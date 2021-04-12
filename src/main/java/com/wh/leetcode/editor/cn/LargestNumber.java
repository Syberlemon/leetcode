package com.wh.leetcode.editor.cn;
//给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。 
//
// 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [10,2]
//输出："210" 
//
// 示例 2： 
//
// 
//输入：nums = [3,30,34,5,9]
//输出："9534330"
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出："1"
// 
//
// 示例 4： 
//
// 
//输入：nums = [10]
//输出："10"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 109 
// 
// Related Topics 排序 
// 👍 614 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class LargestNumber {
    public static void main(String[] args) {
        Solution solution = new LargestNumber().new Solution();
        int[] nums = {0,0,0};
        System.out.println(solution.largestNumber(nums));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
//  末尾补最后一位来比较的思路不对，因为补充的数大小不确定，会导致结果错误
    public String largestNumber(int[] nums) {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            list.add(String.valueOf(nums[i]));
        }
        Collections.sort(list, new Comparator<String>() {
            @Override public int compare(String s1, String s2) {
                return (s1+s2).compareTo((s2+s1));
            }
        });
        Collections.reverse(list);
        String str = String.join("", list);
        //如何把开头多余的0去掉呢：开头是0只有全为0的情况，否则0会排到最后
        if("".equals(str.replace("0", ""))){
            return "0";
        }
        return str;
    }
    //题解sx是可以和x做加法的倍数，比如x=5，那么sx=10，比如x=15,那么sx=100;
//        sx * y + x 为 y+x ; sy * x + y 为 x+y
     public String largestNumber_answer(int[] nums){
        int n = nums.length;
        Integer[] numsArr = new Integer[n];
        for(int i = 0; i < n; i++){
            numsArr[i] = nums[i];
        }
        Arrays.sort(numsArr, (x, y)->{
            long sx = 10, sy = 10;
            while(sx <= x){
                sx *= 10;
            }
            while(sy <= y){
                sy *= 10;
            }
            return (int)((sx * y + x) - (sy * x + y));
        });
        if(numsArr[0] == 0){
            return "0";
        }
        StringBuilder ret = new StringBuilder();
        for(int num: numsArr){
            ret.append(num);
        }
        return ret.toString();
     }

}
//leetcode submit region end(Prohibit modification and deletion)

}
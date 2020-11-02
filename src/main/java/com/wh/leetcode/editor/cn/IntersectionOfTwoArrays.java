package com.wh.leetcode.editor.cn;
//给定两个数组，编写一个函数来计算它们的交集。 
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2]
// 
//
// 示例 2： 
//
// 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[9,4] 
//
// 
//
// 说明： 
//
// 
// 输出结果中的每个元素一定是唯一的。 
// 我们可以不考虑输出结果的顺序。 
// 
// Related Topics 排序 哈希表 双指针 二分查找 
// 👍 267 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IntersectionOfTwoArrays {
    public static void main(String[] args) {
        Solution solution = new IntersectionOfTwoArrays().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        for (int i : nums1){
            if(!list.contains(i)){
                list.add(i);
            }
        }
        for (int j: nums2){
            if(list.contains(j) && !res.contains(j)){
                res.add(j);
            }
        }
        int[] inter = new int[res.size()];
        for (int i = 0; i < res.size(); i++){
            inter[i] = res.get(i);
        }
        return inter;
    }

        /**
         * 答案收获
         * F1 用Set new HashSet
         * F2 用了Arrays的方法 Arrays.sort Arrays.copyOfRange
         * 抄完答案后，感觉还是我的方法最好理解，就是时间空间复杂度高
         */
    public int[] intersection_answer(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums1){
            set1.add(num);
        }
        for (int num: nums2){
            set2.add(num);
        }
        return getIntersection(set1, set2);
    }
    private int[] getIntersection(Set<Integer> set1, Set<Integer> set2){
        if(set1.size() > set2.size()){
            return getIntersection(set2, set1);
        }
        Set<Integer> intersectionSet = new HashSet<>();
        for (int num : set1){
            if(set2.contains(num)){
                intersectionSet.add(num);
            }
        }
        int[] intersection = new int[intersectionSet.size()];
        int index = 0;
        for (int num : intersectionSet){
            intersection[index++] = num;
        }
        return intersection;
    }

    public int[] intersection_answer2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[length1 + length2];
        int index = 0, index1 = 0, index2=0;
        while(index1 < length1 && index2 < length2){
            int num1 = nums1[length1], num2 = nums2[length2];
            if(num1 == num2){
                if(index == 0 || num1 != intersection[index - 1]){
                    intersection[index++] = num1;
                }
                index1++;
                index2++;
            } else if(num1 < num2){
                index1++;
            } else {
                index2++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
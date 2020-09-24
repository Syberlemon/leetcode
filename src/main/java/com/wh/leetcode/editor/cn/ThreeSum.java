package com.wh.leetcode.editor.cn;
//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复
//的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例： 
//
// 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//
//满足要求的三元组集合为：
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
// 
// Related Topics 数组 双指针 
// 👍 2606 👎 0

import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThreeSum {

    public static void main(String[] args) {
        Solution solution = new ThreeSum().new Solution();
        int[] nums = {-1,0,1,2,-1,-4};
        System.out.println(JSONObject.toJSONString(solution.threeSum3(nums)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //三重循环，超时做法
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    for (int k = j + 1; k < nums.length; k++) {
                        if (nums[i] + nums[j] + nums[k] == 0) {
                            List<Integer> tempRes = new ArrayList<>();
                            tempRes.add(nums[i]);
                            tempRes.add(nums[j]);
                            tempRes.add(nums[k]);
                            //三个数从小到大排序，然后判重
                            Collections.sort(tempRes);
                            if (!res.contains(tempRes)) {
                                res.add(tempRes);
                            }
                        }
                    }

                }
            }
            return res;
        }

        public List<List<Integer>> threeSum2(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> originNums = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                originNums.add(nums[i]);
            }
            Collections.sort(originNums);
            for (int i = 0; i < originNums.size() - 2; i++) {
                if (i > 0 && originNums.get(i) == originNums.get(i - 1)) {
                    continue;
                }
                int j = i + 1;
                int k = originNums.size() - 1;
                while (k > i && k > j) {
                    int sum = originNums.get(i) + originNums.get(j) + originNums.get(k);
                    System.out.println("i:" + i + ", j:" + j + ", k:" + k + ", sum:" + sum);
                    if (sum == 0) {
                        List<Integer> tempRes = new ArrayList<>();
                        tempRes.add(originNums.get(i));
                        tempRes.add(originNums.get(j));
                        tempRes.add(originNums.get(k));
                        if (!res.contains(tempRes)) {
                            res.add(tempRes);
                        }
                        j++;
                        k--;
                    } else if (sum > 0) {
                        k--;
                    } else {
                        j++;
                    }
                }
            }
            return res;
        }

        public List<List<Integer>> threeSum3(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(nums);
            for (int first = 0; first < nums.length; first++) {
                if (first > 0 && nums[first] == nums[first - 1]) {
                    continue;
                }
                int third = nums.length - 1;
                for (int second = first + 1; second < nums.length; second++) {
                    //如果写second>0 就不对,大于first+1是为了保证second-1仍然大于first
                    // 即second-1和first的组合被计算过，避免second和first相同的情况被漏掉
                    if (second > first + 1 && nums[second] == nums[second - 1]) {
                        continue;
                    }
                    if (second == third) {
                        break;
                    }
                    if (second < third && nums[second] + nums[third] > -nums[first]) {
                        third--;
                    }
                    if (nums[second] + nums[third] == -nums[first]) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[first]);
                        list.add(nums[second]);
                        list.add(nums[third]);
                        res.add(list);
                    }
                }
            }
            return res;
        }

        public List<List<Integer>> threeSum_answer(int[] nums) {
            int n = nums.length;
            Arrays.sort(nums);
            List<List<Integer>> ans = new ArrayList<>();
            for (int first = 0; first < n; ++first) {
                if (first > 0 && nums[first] == nums[first - 1]) {
                    continue;
                }
                int third = n - 1;
                int target = -nums[first];
                for (int second = first + 1; second < n; ++second) {
                    if (second > first + 1 && nums[second] == nums[second - 1]) {
                        continue;
                    }
                    //因为是while循环，所以必须有终止条件
                    while (second < third && nums[second] + nums[third] > target) {
                        --third;
                    }
                    if (second == third) {
                        break;
                    }
                    if (nums[second] + nums[third] == target) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[first]);
                        list.add(nums[second]);
                        list.add(nums[third]);
                        ans.add(list);
                    }
                }
            }
            return ans;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
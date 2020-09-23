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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ThreeSum {

    public static void main(String[] args) {
        Solution solution = new ThreeSum().new Solution();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(JSONObject.toJSONString(solution.threeSum(nums)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            //需要去重
            for (int i = 0; i < nums.length; i++) {
                for (int j = i+1; j <  nums.length; j++) {
                    for (int k = j+1; k < nums.length; k++){
                        if(nums[i] + nums[j] + nums[k] == 0){
                            List<Integer> tempRes = new ArrayList<>();
                            tempRes.add(nums[i]);
                            tempRes.add(nums[j]);
                            tempRes.add(nums[k]);
                            res.add(tempRes);
                        }
                    }

                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
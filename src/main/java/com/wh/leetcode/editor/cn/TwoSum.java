package com.wh.leetcode.editor.cn;
//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。 
//
// 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表 
// 👍 9180 👎 0

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;

public class TwoSum {

    public static void main(String[] args) {

        Solution solution = new TwoSum().new Solution();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] res = solution.twoSum2(nums, target);
        System.out.println(JSONObject.toJSONString(res));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //两遍哈希表
        public int[] twoSum_answer(int[] nums, int target) throws Exception {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int num = target - nums[i];
                if (map.containsKey(num)) {
                    return new int[]{map.get(num), i};
                }
                map.put(nums[i], i);
            }
            throw new Exception("no two sum solution");
        }

        //暴力法：两遍for循环
        public int[] twoSum_answer2(int[] nums, int target) throws Exception {
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] + nums[j] == target) {
                        return new int[]{i, j};
                    }
                }
            }
            throw new Exception("no two sum solution");
        }

        /**
         * 扩展
         * 1、数组中一个元素可以用两遍
         * 2、可能是多个数字之和，将可能的组合都找出来
         */

        public int[] twoSum(int[] nums, int target) {
            int[] res = new int[2];
            for (int i = 0; i<nums.length; i++){
                int temp = target - nums[i];
                for (int j = i + 1; j < nums.length; j++){
                    if(nums[j] == temp){
                        res[0] = i;
                        res[1] = j;
                        return res;
                    }
                }
            }
            return res;
        }

        public int[] twoSum2(int[] nums, int target) {
            int[] res = new int[2];
            HashMap<Integer, Integer> map = new HashMap();
            for (int i = 0; i < nums.length; i++){
                map.put(nums[i], i);
            }
            for (Integer k: map.keySet()){
                int temp = target - k;
                //注意一定要判断不等于k
                if(map.containsKey(temp) && map.get(temp) != k){
                    res[0] = map.get(k);
                    res[1] = map.get(temp);
                    return res;
                }
            }
            return res;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
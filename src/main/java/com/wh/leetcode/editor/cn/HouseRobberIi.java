package com.wh.leetcode.editor.cn;
//你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋
//装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。 
//
// 示例 1: 
//
// 输入: [2,3,2]
//输出: 3
//解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
// 
//
// 示例 2: 
//
// 输入: [1,2,3,1]
//输出: 4
//解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
//     偷窃到的最高金额 = 1 + 3 = 4 。
// Related Topics 动态规划 
// 👍 380 👎 0

public class HouseRobberIi {
    public static void main(String[] args) {
        Solution solution = new HouseRobberIi().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
//  增加了一个条件，是所有房屋围成一圈，思路一样，只是最后一个元素时还需要判断是否有第一个元素
//  自测案例[2,2,4,3,2,5]
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int length = nums.length;
        if(length == 1){
            return nums[0];
        }
        if(length == 2){
            return Math.max(nums[0], nums[1]);
        }
        int[] haveZero = new int[length];
        haveZero[0] = 1;
        if(nums[0] > nums[1]){
            haveZero[1] = 1;
        } else {
            haveZero[1] = 0;
        }
        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);
        for(int i = 2; i < nums.length; i++){
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
            if(second != temp){
                haveZero[i] = haveZero[i-2];
            } else {
                haveZero[i] = haveZero[i-1];
            }
            //最后一个元素的时候，我需要知道他前面的两个值是否包含了第一个元素，包含的话就不能再加他自身了
            if(i == nums.length - 1){
                if(haveZero[i] == 1){
                    second = Math.max(second - nums[i], first);
                } else {
                    return second;//不需要再判断不包含第一个元素的情况，因为当前就不包括
                }
            }
        }
        int newFirst = nums[1];
        int newSecond = nums[2];
        for(int i = 3; i < nums.length; i++){
            int temp = newSecond;
            newSecond = Math.max(newFirst + nums[i], newSecond);
            newFirst = temp;
        }
        return Math.max(second,newSecond);
    }

    public int rob_answer(int[] nums){
        int length = nums.length;
        if(length == 1){
            return nums[0];
        } else if(length == 2){
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(robRange(nums, 0, length - 2), robRange(nums, 1, length-1));
    }
    public int robRange(int[] nums, int start, int end){
        int first = nums[start];
        int second = Math.max(nums[start], nums[start +1]);
        for(int i = start + 2; i <= end; i++){
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
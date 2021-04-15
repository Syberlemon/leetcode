package com.wh.leetcode.editor.cn;
//你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上
//被小偷闯入，系统会自动报警。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 输入：[1,2,3,1]
//输出：4
//解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 2： 
//
// 输入：[2,7,9,3,1]
//输出：12
//解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
//     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 100 
// 0 <= nums[i] <= 400 
// 
// Related Topics 动态规划 
// 👍 1084 👎 0

public class HouseRobber {
    public static void main(String[] args) {
        Solution solution = new HouseRobber().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int rob(int[] nums) {
        //动态规划题目，偷当前房的最大金额为 max(rob(n-1), rob(n-2) + money(n))
        if(nums.length == 0){
            return 0;
        }
        int[] memo = new int[nums.length];//备忘录，不加备忘录会超时
        for(int i = 0; i < nums.length; i++){
            memo[i] = -1;
        }
        return robMaxMoney(nums, nums.length-1, memo);
    }

    //偷n间房最大的钱
    private int robMaxMoney(int[] nums, int n, int[] memo){
        if(n == 0){
            return nums[0];
        }
        if(n == 1){
            return Math.max(nums[0], nums[1]);
        }
        if(memo[n] != -1){
            return memo[n];
        }
        int robN = robMaxMoney(nums, n-2, memo) + nums[n];
        int nrobN = robMaxMoney(nums, n-1, memo);
        memo[n] = Math.max(robN, nrobN);
        return memo[n];
    }

    public int rob_answer(int nums[]){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int length = nums.length;
        //因为后面迭代要用nums[1]，所以对于只有一个元素的需要单拎出来提前返回
        if(length == 1){
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        //用的正向推导，所以dp自身就相当于备忘录了，这样写起来更简单
        for(int i = 2; i < length; i++){
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }
        return dp[length-1];
    }

    public int rob_answer2(int nums[]){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int length = nums.length;
        if(length == 1){
            return nums[0];
        }
        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for(int i = 2; i < length; i++){
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
        // 简化了额外需要的空间，dp数组需要n个空间
        // 而滚动数组只需要两个变量，是题目特殊性，当前房屋最高金额 只和前两个房屋最高金额相关
        // 每个迭代中将first和second的值更新
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
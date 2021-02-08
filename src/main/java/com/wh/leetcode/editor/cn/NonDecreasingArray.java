package com.wh.leetcode.editor.cn;
//给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。 
//
// 我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。 
//
// 
//
// 示例 1: 
//
// 输入: nums = [4,2,3]
//输出: true
//解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
// 
//
// 示例 2: 
//
// 输入: nums = [4,2,1]
//输出: false
//解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
// 
//
// 
//
// 说明： 
//
// 
// 1 <= n <= 10 ^ 4 
// - 10 ^ 5 <= nums[i] <= 10 ^ 5 
// 
// Related Topics 数组 
// 👍 483 👎 0

public class NonDecreasingArray {
    public static void main(String[] args) {
        Solution solution = new NonDecreasingArray().new Solution();
        int[] nums = new int[]{1,4,2,3};
        System.out.println(solution.checkPossibility(nums));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean checkPossibility(int[] nums) {
        //长度小于等于2，直接返回true
        if(nums.length <= 2){
            return true;
        }
        //依次跳过每个元素，检测剩下元素是否符合非递减（循环走到最后没中断），符合则返回true，其余都为false
        for(int i = 0; i < nums.length; i++){
            int j = 0;
            if(j == i){
                j += 1;
            }
            int front = nums[j];
            while(j < nums.length && front <= nums[j]){
                front = nums[j];
                j++;
                if(j == i){
                    j+=1;
                }
            }
            if(j == nums.length){
                return true;
            }
        }
        return false;
    }

        /**
         * 遍历一遍即可
         * 此题最容易出错的地方是情况考虑不全
         * 4 2 5 改4
         * 1 4 2 5 改4
         * 3 4 2 5 如果2比3小，就要改2
         */
    public boolean checkPossibility_answer(int[] nums) {
        if(nums.length <= 2){
            return true;
        }
        int cnt = 0;
        for(int i = 0; i < nums.length - 1; i++){
            int x = nums[i], y = nums[i+1];
            if(x > y){
                cnt ++;
                if(cnt > 1){
                    return false;
                }
                if(i > 0 && y < nums[i-1]){
                    nums[i+1] = nums[i];
                }
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
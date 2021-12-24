package com.wh.leetcode.editor.cn;
//给定一个整数数组 nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。 
//
// 
// 
// 实现 NumArray 类： 
//
// 
// NumArray(int[] nums) 使用数组 nums 初始化对象 
// int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点（也就是 s
//um(nums[i], nums[i + 1], ... , nums[j])） 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["NumArray", "sumRange", "sumRange", "sumRange"]
//[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
//输出：
//[null, 1, -1, -3]
//
//解释：
//NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
//numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
//numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1)) 
//numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 104 
// -105 <= nums[i] <= 105 
// 0 <= i <= j < nums.length 
// 最多调用 104 次 sumRange 方法 
// 
// 
// 
// Related Topics 设计 数组 前缀和 
// 👍 395 👎 0

public class RangeSumQueryImmutable {

    public static void main(String[] args) {
        Solution solution = new RangeSumQueryImmutable().new Solution();
        int[] nums = new int[]{-2, 0, 3, -5, 2, -1};
        int res = solution.test(nums, 2, 5);
        System.out.println(res);
    }

    class Solution {

        public int test(int[] nums, int left, int right) {
            NumArray obj = new NumArray(nums);
            int param_1 = obj.sumRange(left, right);
            return param_1;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class NumArray {
//        int[] nums = new int[]{};
//
//        public NumArray(int[] nums) {
//            this.nums = nums;
//        }
//
//        public int sumRange(int left, int right) {
//            int sum = 0;
//            for(int i = left; i <= right; i++){
//                sum += nums[i];
//            }
//            return sum;
//        }
        /**
         * 看题解说，应该降低sumRange的复杂度，目前是O(n),要降为O(1),则需要在初始化的时候做好预处理，任意两个数字之间的情况全存储下来？
         * 既然叫前缀树，应该是有剪枝的，比如将循环转换为某几个数的和之类的方法。 题解说，将求和运算 转换为两个前缀和的差。前缀和即从当前节点到最后节点的和。
         */
//        int[] numArr;
////
////        public NumArray(int[] nums) {
////            //计算前缀和
////            numArr = new int[nums.length];
////            numArr[0] = nums[0];
////            for (int i = 1; i < nums.length; i++) {
////                numArr[i] = numArr[i - 1] + nums[i];
////            }
////        }
////
////        public int sumRange(int left, int right) {
////            if(left == 0) {
////                return numArr[right];
////            } else {
////                return numArr[right] - numArr[left - 1];
////            }
////        }

        int[] sums;
        public NumArray(int[] nums){
            int n = nums.length;
            sums = new int[n+1];
            for (int i = 0; i < n; i++){
                //第i+1个位置 存的是 前i个元素的和，这个和不包括自身
                sums[i+1] = sums[i] + nums[i];
            }
        }
        public int sumRange(int i, int j){
            return sums[j + 1] - sums[i];
        }

//        还可以懒计算，用到的时候再算，如果所用的j 大于之前计算过的count,就把count到j之间的计算一下
    }

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
package com.wh.leetcode.editor.cn;
//数组中占比超过一半的元素称之为主要元素。给定一个整数数组，找到它的主要元素。若没有，返回-1。 
//
// 示例 1： 
//
// 输入：[1,2,5,9,5,9,5,5,5]
//输出：5 
//
// 
//
// 示例 2： 
//
// 输入：[3,2]
//输出：-1 
//
// 
//
// 示例 3： 
//
// 输入：[2,2,1,1,1,2,2]
//输出：2 
//
// 
//
// 说明： 
//你有办法在时间复杂度为 O(N)，空间复杂度为 O(1) 内完成吗？ 
// Related Topics 位运算 数组 分治算法 
// 👍 81 👎 0

public class FindMajorityElementLcci {
    public static void main(String[] args) {
        Solution solution = new FindMajorityElementLcci().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int majorityElement(int[] nums) {
        int major = -1;
        int count = 0;
        if(nums.length <= 0){
            return major;
        }
        for(int i = 0; i < nums.length; i++){
            if(major == -1){//山顶没人，直接占领
                major = nums[i];
                count = 1;
            }else if(nums[i] == major){//山顶是队友
                count ++;
            }else{
                count--;
                if(count == 0){
                    major = -1;//敌军将队友打完了
                }
            }
        }
        if(major != -1){
            //判断major是否为主元素
            int majorCount = 0;
            for(int j = 0; j < nums.length; j++){
                if(nums[j] == major){
                    majorCount++;
                }
            }
            if(majorCount <= nums.length / 2){
                major = -1;
            }
        }
        return major;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
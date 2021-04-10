package com.wh.leetcode.editor.cn;
//已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变
//化后可能得到：
// 
// 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2] 
// 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7] 
// 
//
// 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], 
//..., a[n-2]] 。 
//
// 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [3,4,5,1,2]
//输出：1
//解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
// 
//
// 示例 2： 
//
// 
//输入：nums = [4,5,6,7,0,1,2]
//输出：0
//解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
// 
//
// 示例 3： 
//
// 
//输入：nums = [11,13,15,17]
//输出：11
//解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 5000 
// -5000 <= nums[i] <= 5000 
// nums 中的所有整数 互不相同 
// nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转 
// 
// Related Topics 数组 二分查找 
// 👍 442 👎 0

public class FindMinimumInRotatedSortedArray {
    public static void main(String[] args) {
        Solution solution = new FindMinimumInRotatedSortedArray().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
//  第一感觉：这个题简单到怀疑人生，居然是中等难度
//  我的思路：因为题干是旋转的，所以部分有序，如果前一个大于后一个，说明旋转过那后一个就是最小值，如果后面都大于前面，说明没旋转过，第一个就是最小值
//  看完答案：这个题解法还可以优化，因为for循环的复杂度是O(n),用二分查找可以降到O(log n)
    public int findMin(int[] nums) {
        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i] > nums[i+1]){
                return nums[i+1];
            }
        }
        return nums[0];
    }
//  二分查找法格式：low high初始化，用while循环，pivot取中间值，来不断缩小low和high框定的区间范围，当low不小于high循环结束，输出low位置的值即目标
    public int findMin_answer(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while(low < high){
            int pivot = low + (high -low)/2;
//            if(nums[low] > nums[pivot]){//左边值都比pivot大，low和pivot之间的数字可以淘汰,这样没法写了
//
//            }
            if(nums[high] > nums[pivot]){//右边值都比pivot大，由于升序，右侧值可以淘汰，都不可能是最小值
                high = pivot;
            } else {
                low = pivot + 1;//这里为什么能把左侧数字淘汰掉
                // 是因为这个一个升序数组进行旋转得到的，当high<pivot，说明pivot前面的元素都是由后面旋转过去的
                // 因为如果在没有旋转的区间内，pivot一定是比high小的，后面旋转过去的都是大值，所以就可以把pivot之前的元素都淘汰掉
            }
        }
        return nums[low];//这里由于low==high，所以返回nums[high]是一样的
        /**
         *   面试别人可以问的地方：
         *   1、if-else判断里面如果 high=pivot-1, low=pivot 还对吗？为啥？
         *   不对，因为数组是升序，所以左侧可以往右多走一步，因为左侧元素是旋转过去的
         *   下一个元素可能是继续增大的，也可能是最小值，不能多走两步，不确定断崖在哪里
         *   右侧往左不可以多走，因为pivot可能就是最小值，往小多走一步可能越过最小值
         *   2、return的nums[low] 如果改成nums[high]还对吗？为啥？
         *   对，因为最后结束while循环的时候一定是low==high，如果low!=high，那么还满足low<high
         */
    }

        public int findMax(int[] nums) {
            int low = 0;
            int high = nums.length - 1;
            while(low < high){
                int pivot = low + (high - low)/2 + 1;//注意如果拿low和pivot比较找最大值这里要+1,
                if(nums[low] < nums[pivot]){
                    low = pivot;
                } else {
                    high = pivot - 1;
                }
            }
            return nums[low];
        }
}
//leetcode submit region end(Prohibit modification and deletion)

}
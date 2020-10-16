package com.wh.leetcode.editor.cn;
//给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。 
//
// 
//
// 示例 1： 
//
// 输入：[-4,-1,0,3,10]
//输出：[0,1,9,16,100]
// 
//
// 示例 2： 
//
// 输入：[-7,-3,2,3,11]
//输出：[4,9,9,49,121]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 10000 
// -10000 <= A[i] <= 10000 
// A 已按非递减顺序排序。 
// 
// Related Topics 数组 双指针 
// 👍 147 👎 0

import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SquaresOfASortedArray {

    public static void main(String[] args) {
        Solution solution = new SquaresOfASortedArray().new Solution();
        int[] A = new int[]{-4, -1, 0, 3, 10};
        System.out.println(JSONObject.toJSONString(solution.sortedSquares(A)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //思路：有序 要利用起来：小于0的 和 大于0的分两组，然后合并为结果集
        public int[] sortedSquares(int[] A) {
            List<Integer> downZero = new ArrayList<>();
            List<Integer> upZero = new ArrayList<>();
            for (int i = 0; i < A.length; i++) {
                if (A[i] < 0) {
                    downZero.add(A[i] * A[i]);
                } else {
                    upZero.add(A[i] * A[i]);
                }
            }
            int[] res = new int[A.length];
            if (downZero.size() == 0) {
                for (int i = 0; i < A.length; i++) {
                    res[i] = upZero.get(i);
                }
                return res;
            }
            if (upZero.size() == 0) {
                for (int i = 0; i < A.length; i++) {
                    res[i] = downZero.get(A.length - i - 1);
                }
                return res;
            }
            int c1 = downZero.size() - 1;
            int c2 = 0;
            for (int i = 0; i < A.length; i++) {
                if (c1 < 0) {
                    res[i] = upZero.get(c2);
                    c2++;
                    continue;
                }
                if (c2 >= upZero.size()) {
                    res[i] = downZero.get(c1);
                    c1--;
                    continue;
                }
                if (downZero.get(c1) < upZero.get(c2)) {
                    res[i] = downZero.get(c1);
                    c1--;
                } else {
                    res[i] = upZero.get(c2);
                    c2++;
                }
            }
            return res;
        }

        //平方后直接排序
        public int[] sortedSquares_answer(int[] A) {
            int[] ans = new int[A.length];
            for (int i = 0; i < A.length; ++i) {
                ans[i] = A[i] * A[i];
            }
            Arrays.sort(ans);
            return ans;
        }

        /**
         * 答案收货：
         * 1、用if... else if... 这样就不用写continue了
         * 2、正负两组数可以不用额外空间记录，分界点找出来，从分界点两个指针往两头走即可
         */
        public int[] sortedSquares_answer2(int[] A) {
            int n = A.length;
            int negative = -1;
            //先找到正负数分界点
            for (int i = 0; i < n; ++i) {
                if (A[i] < 0) {
                    negative = i;
                } else {
                    break;
                }
            }
            int[] ans = new int[n];
            int index = 0, i = negative, j = negative + 1;
            while (i > 0 || j < n) {
                if (i < 0) {
                    ans[index] = A[j] * A[j];
                    ++j;
                } else if (j == n) {
                    ans[index] = A[i] * A[i];
                    --i;
                } else if (A[i] * A[i] < A[j] * A[j]) {
                    ans[index] = A[i] * A[i];
                    --i;
                } else {
                    ans[index] = A[j] * A[j];
                    ++j;
                }
                ++index;
            }
            return ans;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
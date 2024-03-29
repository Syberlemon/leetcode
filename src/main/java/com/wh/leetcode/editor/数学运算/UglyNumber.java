package com.wh.leetcode.editor.数学运算;
//给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。 
//
// 丑数 就是只包含质因数 2、3 和/或 5 的正整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 6
//输出：true
//解释：6 = 2 × 3 
//
// 示例 2： 
//
// 
//输入：n = 8
//输出：true
//解释：8 = 2 × 2 × 2
// 
//
// 示例 3： 
//
// 
//输入：n = 14
//输出：false
//解释：14 不是丑数，因为它包含了另外一个质因数 7 。
// 
//
// 示例 4： 
//
// 
//输入：n = 1
//输出：true
//解释：1 通常被视为丑数。
// 
//
// 
//
// 提示： 
//
// 
// -231 <= n <= 231 - 1 
// 
// Related Topics 数学 
// 👍 226 👎 0

public class UglyNumber {
    public static void main(String[] args) {
        Solution solution = new UglyNumber().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isUgly(int n) {
        if(n <= 0){//非正整数不是丑数
            return false;
        }
        while(n > 1){
            if(n % 2 == 0){
                n = n /2;
            } else if(n % 3 == 0){
                n = n /3;
            }else if(n % 5 == 0){
                n = n /5;
            } else{
                return false;
            }
        }
        return true;
    }

    public boolean isUgly_answer(int n){
        if(n <= 0){
            return false;
        }
        int[] factors = {2, 3, 5};
        for(int factor: factors){
            while(n % factor == 0){
                n /= factor;
            }
        }
        return n==1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
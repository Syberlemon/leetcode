package com.wh.leetcode.editor.cn;
//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 动态规划 
// 👍 1281 👎 0

import java.util.HashMap;
import java.util.Map;

public class ClimbingStairs {
    public static void main(String[] args) {
        Solution solution = new ClimbingStairs().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        // f(n) = f(n-1) + f(n-2) 超时了
    public int climbStairs0(int n) {
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        return climbStairs0(n-1) + climbStairs0(n - 2);
    }

    Map<Integer, Integer> countMap = new HashMap<>();
    public int climbStairs(int n) {
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        int a = 0;
        int b = 0;
        if(!countMap.containsKey(n-1)){
            a = climbStairs(n-1);
        }else{
            a = countMap.get(n-1);
        }
        if(!countMap.containsKey(n-2)){
            b = climbStairs(n-2);
        }else{
            b = countMap.get(n-2);
        }
        return a+b;
    }

    //此题答案解法很多，不容易看懂
    public int climbStairs_answer(int n){
        int p = 0, q =0 ,r = 1;
        for(int i = 0; i < n; i++){
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
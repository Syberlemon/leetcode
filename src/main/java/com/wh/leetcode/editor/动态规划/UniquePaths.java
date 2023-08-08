package com.wh.leetcode.editor.动态规划;
//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。 
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。 
//
// 问总共有多少条不同的路径？ 
//
// 
//
// 示例 1： 
//
// 
//输入：m = 3, n = 7
//输出：28 
//
// 示例 2： 
//
// 
//输入：m = 3, n = 2
//输出：3
//解释：
//从左上角开始，总共有 3 条路径可以到达右下角。
//1. 向右 -> 向右 -> 向下
//2. 向右 -> 向下 -> 向右
//3. 向下 -> 向右 -> 向右
// 
//
// 示例 3： 
//
// 
//输入：m = 7, n = 3
//输出：28
// 
//
// 示例 4： 
//
// 
//输入：m = 3, n = 3
//输出：6 
//
// 
//
// 提示： 
//
// 
// 1 <= m, n <= 100 
// 题目数据保证答案小于等于 2 * 109 
// 
// Related Topics 数组 动态规划 
// 👍 812 👎 0

public class UniquePaths {

    public static void main(String[] args) {
        Solution solution = new UniquePaths().new Solution();
        System.out.println(solution.uniquePaths_answer2(4, 4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //超出时间限制
        public int uniquePaths(int m, int n) {
            //就是问组合 m-1个1和 n-1个0有多少种不同的组合
            if (m == 1 || n == 1) {
                return 1;
            }
            return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
        }

        /**
         * 为什么第一行和第一列都赋值为1，因为只能向右走或者向下走，所以到达该节点的方式只有一种
         * f[i][j] 为到达该节点的路径个数，右下角节点为 f[m-1][n-1]
         * 当前节点只有两种方式到达，向右走 或者 向下走，所以适合递推，即存在递推公式
         * f[i][j] = f[i-1][j] + f[i][j-1]
         * @date: 2023/7/31 21:03
         */
        public int uniquePaths_answer(int m, int n) {
            int[][] f = new int[m][n];
            for (int i = 0; i < m; ++i) {
                f[i][0] = 1;
            }
            for (int j = 0; j < n; ++j) {
                f[0][j] = 1;
            }
            for (int i = 1; i < m; ++i) {
                for (int j = 1; j < n; ++j) {
                    f[i][j] = f[i - 1][j] + f[i][j - 1];
                }
            }
            return f[m - 1][n - 1];
        }

        //组合数学 O(1)的空间复杂度
        public int uniquePaths_answer2(int m, int n) {
            int max = Math.max(m, n);
            int min = Math.min(m, n);
            long ans = 1;
            for (int x = max, y = 1; y < min; ++x, ++y) {
                //不能写成*= 这个有顺序是因为不先乘会错
                // 整数相除默认取整，比如5/2 就导致错误了，先用long乘法后就是long型了，能得到2.5
                ans = ans * x / y;
            }
            return (int) ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
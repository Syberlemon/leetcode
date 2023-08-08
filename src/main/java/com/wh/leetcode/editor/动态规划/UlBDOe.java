package com.wh.leetcode.editor.动态规划;
//小扣出去秋游，途中收集了一些红叶和黄叶，他利用这些叶子初步整理了一份秋叶收藏集 `leaves`， 字符串 `leaves` 仅包含小写字符 `r` 和 `
//y`， 其中字符 `r` 表示一片红叶，字符 `y` 表示一片黄叶。
//出于美观整齐的考虑，小扣想要将收藏集中树叶的排列调整成「红、黄、红」三部分。每部分树叶数量可以不相等，但均需大于等于 1。每次调整操作，小扣可以将一片红叶替
//换成黄叶或者将一片黄叶替换成红叶。请问小扣最少需要多少次调整操作才能将秋叶收藏集调整完毕。
//
//**示例 1：**
//>输入：`leaves = "rrryyyrryyyrr"`
//>
//>输出：`2`
//>
//>解释：调整两次，将中间的两片红叶替换成黄叶，得到 "rrryyyyyyyyrr"
//
//**示例 2：**
//>输入：`leaves = "ryr"`
//>
//>输出：`0`
//>
//>解释：已符合要求，不需要额外操作
//
//**提示：**
//- `3 <= leaves.length <= 10^5`
//- `leaves` 中只包含字符 `'r'` 和字符 `'y'` 👍 155 👎 0

public class UlBDOe {
    public static void main(String[] args) {
        Solution solution = new UlBDOe().new Solution();
        System.out.println(solution.minimumOperations("ryyryyyrryyyyyryyyrrryyyryryyyyryyrrryryyyryrryrrrryyyrrrrryryyrrrrryyyryyryrryryyryyyyryyrryrryryy"));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //红叶子不在首尾，则必然要替换
    public int minimumOperations(String leaves) {
        int res = 0;
        //红叶子需要操作数量，不在头尾，必然要替换
        int r_need = 0;
        if(leaves.substring(0,1).equals("y")){
            r_need += 1;
        }
        if(leaves.substring(leaves.length()-1,leaves.length()).equals("y")){
            r_need += 1;
        }
        //黄叶子，需要看是黄替换成红更少，还是红替换成黄更少 ??? 这里不会写
        return res;
    }


    //读错题意了，按交换做的
    public int minimumOperations_jiaohuan(String leaves) {
        //从第一个y到最后一个y中间的r的个数，就是需要交换的次数
        //需要记录y的个数，以及y的起止位置，做差即中间的r的个数
        int y_cnt = 0;
        int y_start = -1;
        int y_end = -1;
        for (int i = 0; i < leaves.length(); i++) {
            if("y".equals(leaves.substring(i, i+1))){
                y_cnt += 1;
                if(y_start == -1){
                    y_start = i;
                }
                y_end = i;
            }
        }
        //红叶子需要的交数，不在头尾，不够2个红，那只能替换
        int r_need = 0;
        if(leaves.substring(0,1).equals("y")){
            r_need += 1;
        }
        if(leaves.substring(leaves.length()-1,leaves.length()).equals("y")){
            r_need += 1;
        }
        //黄叶子需要的交换数
        int y_need = y_end - y_start + 1 - y_cnt;
        return Math.max(y_need, r_need);
    }


    public int minimumOperations_answer(String leaves) {
        int n = leaves.length();
        int[][] f = new int[n][3];
        f[0][0] = leaves.charAt(0) == 'y' ? 1 : 0;
        //0开头的红 1黄 2结尾的红
        f[0][1] = f[0][2] = f[1][2] = Integer.MAX_VALUE;
        for (int i = 1; i < n; ++i){
            int isRed = leaves.charAt(i) == 'r' ? 1 : 0;
            int isYellow = leaves.charAt(i) == 'y' ? 1 : 0;
            f[i][0] = f[i - 1][0] + isYellow;
            //替成黄，前一个元素可能是红或者黄，取最小值
            f[i][1] = Math.min(f[i - 1][0], f[i-1][1]) + isRed;
            if(i >= 2){
                //开头至少有一个0 一个1 故判断i>2
                f[i][2] = Math.min(f[i-1][1], f[i-1][2]) + isYellow;
            }
        }
        //因为for循环是从前往后算的，对于前面的状态计算最后的2时都已经包括在里面了
        //故最后要的结果就是计算完最后一个元素的结果
        return f[n-1][2];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
package com.wh.leetcode.editor.cn;

//在桌子上有 n 张卡片，每张卡片的正面和背面都写着一个正数（正面与背面上的数有可能不一样）。 
//
// 我们可以先翻转任意张卡片，然后选择其中一张卡片。 
//
// 如果选中的那张卡片背面的数字 x 与任意一张卡片的正面的数字都不同，那么这个数字是我们想要的数字。 
//
// 哪个数是这些想要的数字中最小的数（找到这些数中的最小值）呢？如果没有一个数字符合要求的，输出 0 。 
//
// 其中, fronts[i] 和 backs[i] 分别代表第 i 张卡片的正面和背面的数字。 
//
// 如果我们通过翻转卡片来交换正面与背面上的数，那么当初在正面的数就变成背面的数，背面的数就变成正面的数。 
//
// 
//
// 示例 1： 
//
// 
//输入：fronts = [1,2,4,4,7], backs = [1,3,4,1,3]
//输出：2
//解释：假设我们翻转第二张卡片，那么在正面的数变成了 [1,3,4,4,7] ， 背面的数变成了 [1,2,4,1,3]。
//接着我们选择第二张卡片，因为现在该卡片的背面的数是 2，2 与任意卡片上正面的数都不同，所以 2 就是我们想要的数字。 
//
// 示例 2： 
//
// 
//输入：fronts = [1], backs = [1]
//输出：0
//解释：
//无论如何翻转都无法得到想要的数字，所以返回 0 。
// 
//
// 
//
// 提示： 
//
// 
// n == fronts.length == backs.length 
// 1 <= n <= 1000 
// 1 <= fronts[i], backs[i] <= 2000 
// 
// Related Topics 数组 哈希表
// 👍 87 👎 0

import java.util.ArrayList;
import java.util.List;

public class CardFlippingGame {
    public static void main(String[] args) {
        Solution solution = new CardFlippingGame().new Solution();
        int[] front = new int[]{1,2,4,4,7};
        int[] backs = new int[]{1,3,4,1,3};
        System.out.println(solution.flipgame(front, backs));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int flipgame(int[] fronts, int[] backs) {
        int min = Integer.MAX_VALUE;
        List<Integer> wrongCard = new ArrayList<>();
        List<Integer> possibleCard = new ArrayList<>();
        for (int i = 0; i < fronts.length; i++) {
            if (fronts[i] == backs[i]) {
                wrongCard.add(fronts[i]);
                continue;
            }
            if (!wrongCard.contains(fronts[i])) {
                possibleCard.add(fronts[i]);
            }
            if (!wrongCard.contains(backs[i])) {
                possibleCard.add(backs[i]);
            }
        }
        for (Integer num : possibleCard) {
            if (!wrongCard.contains(num)) {
                min = Math.min(min, num);
            }
        }

        if(min == Integer.MAX_VALUE){
            min = 0;
        }
        return min;
    }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


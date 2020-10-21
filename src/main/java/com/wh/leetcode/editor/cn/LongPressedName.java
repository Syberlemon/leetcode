package com.wh.leetcode.editor.cn;
//你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。 
//
// 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。 
//
// 
//
// 示例 1： 
//
// 输入：name = "alex", typed = "aaleex"
//输出：true
//解释：'alex' 中的 'a' 和 'e' 被长按。
// 
//
// 示例 2： 
//
// 输入：name = "saeed", typed = "ssaaedd"
//输出：false
//解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
// 
//
// 示例 3： 
//
// 输入：name = "leelee", typed = "lleeelee"
//输出：true
// 
//
// 示例 4： 
//
// 输入：name = "laiden", typed = "laiden"
//输出：true
//解释：长按名字中的字符并不是必要的。
// 
//
// 
//
// 提示： 
//
// 
// name.length <= 1000 
// typed.length <= 1000 
// name 和 typed 的字符都是小写字母。 
// 
//
// 
//
// 
// Related Topics 双指针 字符串 
// 👍 117 👎 0

public class LongPressedName {
    public static void main(String[] args) {
        Solution solution = new LongPressedName().new Solution();
        solution.isLongPressedName("vtkgn","vttkgnn");
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //从前往后匹配，记录前一个字母，如果当前不同了，与前一个相同，则往后移动typed
    public boolean isLongPressedName(String name, String typed) {
        if(typed.length() < name.length()){
            return false;
        }
        int nameCount = 0;
        int typedCount = 0;
        char prev = '0';
        //以要检查的字符串为主，因为要检查的字符串比较长，while循环执行的次数才足够
        while(typedCount < typed.length()){
            if (nameCount < name.length() && name.charAt(nameCount) == typed.charAt(typedCount)) {
                prev = name.charAt(nameCount);
                nameCount += 1;
                typedCount += 1;
            } else if (prev == typed.charAt(typedCount)){
                typedCount += 1;
            } else {
                return false;
            }
        }
        //要匹配的字符串没有走到头 说明匹配不上
        if(nameCount < name.length()){
            return false;
        }
        return true;
    }

    //答案收获：1、不需要记录前一个字母，只需判断j和j-1相同； 2、结束时判断i的值即可得结果； 3、+=1 可以写成++
    public boolean isLongPressedName_answer(String name, String typed){
        int i = 0, j = 0;
        while(j < typed.length()){
            if(i < name.length() && name.charAt(i) == typed.charAt(j)){
                i++;
                j++;
            } else if(j > 0 && typed.charAt(j) ==typed.charAt(j-1)){
                j++;
            } else {
                return false;
            }
        }
        return i == name.length();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
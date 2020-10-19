package com.wh.leetcode.editor.cn;
//给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。 
//
// 注意：如果对空文本输入退格字符，文本继续为空。 
//
// 
//
// 示例 1： 
//
// 输入：S = "ab#c", T = "ad#c"
//输出：true
//解释：S 和 T 都会变成 “ac”。
// 
//
// 示例 2： 
//
// 输入：S = "ab##", T = "c#d#"
//输出：true
//解释：S 和 T 都会变成 “”。
// 
//
// 示例 3： 
//
// 输入：S = "a##c", T = "#a#c"
//输出：true
//解释：S 和 T 都会变成 “c”。
// 
//
// 示例 4： 
//
// 输入：S = "a#c", T = "b"
//输出：false
//解释：S 会变成 “c”，但 T 仍然是 “b”。 
//
// 
//
// 提示： 
//
// 
// 1 <= S.length <= 200 
// 1 <= T.length <= 200 
// S 和 T 只含有小写字母以及字符 '#'。 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？ 
// 
//
// 
// Related Topics 栈 双指针 
// 👍 189 👎 0

import java.util.Stack;

public class BackspaceStringCompare {

    public static void main(String[] args) {
        Solution solution = new BackspaceStringCompare().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //思路：先把退格字符去掉，然后比较是否相等
        public boolean backspaceCompare(String S, String T) {
            return remove(S).equals(remove(T));
        }

        private String remove(String s) {
            String res = "";
            Stack<String> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                String temp = s.substring(i, i + 1);
                if (temp.equals("#")) {
                    if (!stack.empty()) {
                        stack.pop();
                    }
                } else {
                    stack.push(temp);
                }
            }
            for (String t : stack) {
                res += t;
            }
            return res;
        }

        //答案收获：1、采用StringBuffer利用char来比较 2、StringBuffer可以当栈用
        public boolean backspaceCompare_answer(String S, String T) {
            return build(S).equals(build(T));
        }
        public String build(String str){
            StringBuffer ret = new StringBuffer();
            int length = str.length();
            for(int i =0; i < length; i++){
                char ch = str.charAt(i);
                if(ch != '#'){
                    ret.append(ch);
                } else {
                    if(ret.length() > 0){
                        ret.deleteCharAt(ret.length() - 1);
                    }
                }
            }
            return ret.toString();
        }

        //倒序两个指针比较
        public boolean backspaceCompare_answer2(String S, String T) {
            int i = S.length() -1, j = T.length() - 1;
            int skipS = 0, skipT = 0;
            while(i >= 0 || j >= 0){
                while(i >= 0){
                    if(S.charAt(i) == '#'){
                        skipS++;
                        i--;
                    } else if(skipS > 0){
                        skipS--;
                        i--;
                    } else {
                        break;
                    }
                }
                while(j >= 0){
                    if(T.charAt(j) == '#'){
                        skipT++;
                        j--;
                    } else if(skipS > 0){
                        skipT--;
                        j--;
                    } else {
                        break;
                    }
                }
                if(i >= 0 && j >= 0){
                    if(S.charAt(i) != T.charAt(j)){
                        return false;
                    }
                } else {
                    if(i >=0 || j >= 0){
                        return false;
                    }
                }
                i--;
                j--;
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
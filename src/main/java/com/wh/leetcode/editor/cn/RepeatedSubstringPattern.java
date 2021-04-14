package com.wh.leetcode.editor.cn;
//给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。 
//
// 示例 1: 
//
// 
//输入: "abab"
//
//输出: True
//
//解释: 可由子字符串 "ab" 重复两次构成。
// 
//
// 示例 2: 
//
// 
//输入: "aba"
//
//输出: False
// 
//
// 示例 3: 
//
// 
//输入: "abcabcabcabc"
//
//输出: True
//
//解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
// 
// Related Topics 字符串 
// 👍 481 👎 0

import java.util.Arrays;

public class RepeatedSubstringPattern {
    public static void main(String[] args) {
        Solution solution = new RepeatedSubstringPattern().new Solution();
        System.out.println(solution.repeatedSubstringPattern("abab"));;
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean repeatedSubstringPattern_answer_meiju(String s) {
        int n = s.length();
        for(int i = 1; i * 2 <= n; ++i){
            if(n % i == 0){
                boolean match = true;
                for(int j = i; j < n; ++j){
                    if(s.charAt(j) != s.charAt(j-i)){
                        match = false;
                        break;
                    }
                }
                if(match){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean repeatedSubstringPattern_answer_2(String s) {
        return (s + s).indexOf(s, 1) != s.length();
    }

    public boolean repeatedSubstringPattern(String s){
        return kmp(s+s, s);
    }

    private boolean kmp(String query, String pattern){
        int n = query.length();
        int m = pattern.length();
        int[] fail = new int[m];
        Arrays.fill(fail, -1);
        for(int i = 1; i < m; ++i){
            int j = fail[i - 1];
            while(j != -1 && pattern.charAt(j+1) != pattern.charAt(i)){
                j = fail[j];
            }
            if(pattern.charAt(j+1)==pattern.charAt(i)){
                fail[i] = j + 1;
            }
        }
        int match = -1;
        for(int i = 1; i < n - 1; ++i){
            while(match != -1 && pattern.charAt(match+1)!= query.charAt(i)){
                match = fail[match];
            }
            if(pattern.charAt(match+1) == query.charAt(i)){
                ++match;
                if(match == m-1){
                    return true;
                }
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
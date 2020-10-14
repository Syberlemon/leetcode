package com.wh.leetcode.editor.cn;
//给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不
//是 4 次，则需要在最终答案中包含该字符 3 次。 
//
// 你可以按任意顺序返回答案。 
//
// 
//
// 示例 1： 
//
// 输入：["bella","label","roller"]
//输出：["e","l","l"]
// 
//
// 示例 2： 
//
// 输入：["cool","lock","cook"]
//输出：["c","o"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 100 
// 1 <= A[i].length <= 100 
// A[i][j] 是小写字母 
// 
// Related Topics 数组 哈希表 
// 👍 140 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindCommonCharacters {
    public static void main(String[] args) {
        Solution solution = new FindCommonCharacters().new Solution();
        String[] A = new String[]{"cool","look","cook"};
        System.out.println(solution.commonChars_answer(A));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> commonChars(String[] A) {
        List<String> res = new ArrayList<>();
        if(A.length <= 0){
            return res;
        }
        //记录字符的个数，["cool","lock","cook"]，比如此情况，不会输出两个o
        Map<String,Integer> countMap = new HashMap<>();
        for (int i = 0; i < A[0].length(); i++){
            for (int j = 1; j < A.length; j++){
                String c = String.valueOf(A[0].charAt(i));
                if(!A[j].contains(c)){
                    break;
                }
                if(countMap.containsKey(c) && (A[j].length() - A[j].replace(c,"").length() <= countMap.get(c))){
                    break;
                }
                if(j == A.length -1){
                    res.add(c);
                    if(countMap.containsKey(c)){
                        countMap.put(c, countMap.get(c)+1);
                    } else {
                        countMap.put(c, 1);
                    }
                }
            }
        }
        return res;
    }

    //答案讨巧了，题目说了小写字母，共26个
    public List<String> commonChars_answer(String[] A) {
        int[] minfreq = new int[26];
        Arrays.fill(minfreq, Integer.MAX_VALUE);
        //计算每个字母在所有单词中的最小个数
        for (String word: A){
            int[] freq = new int[26];
            int length = word.length();
            for (int i = 0; i < length; ++i){
                char ch = word.charAt(i);
                //为了用数组存数量，而不用map，做差将字符转成数字
                ++freq[ch - 'a'];
            }
            for (int i = 0; i < 26; ++i){
                minfreq[i] = Math.min(minfreq[i], freq[i]);
            }
        }

        //输出答案
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 26; i++){
            for (int j = 0; j < minfreq[i]; ++j){
                ans.add(String.valueOf((char)(i + 'a')));
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
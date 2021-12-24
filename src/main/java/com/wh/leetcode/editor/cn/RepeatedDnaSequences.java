package com.wh.leetcode.editor.cn;
//所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复
//序列有时会对研究非常有帮助。 
//
// 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
//输出：["AAAAACCCCC","CCCCCAAAAA"]
// 
//
// 示例 2： 
//
// 
//输入：s = "AAAAAAAAAAAAA"
//输出：["AAAAAAAAAA"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 105 
// s[i] 为 'A'、'C'、'G' 或 'T' 
// 
// Related Topics 位运算 哈希表 字符串 滑动窗口 哈希函数 滚动哈希 
// 👍 313 👎 0

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepeatedDnaSequences {
    public static void main(String[] args) {
        Solution solution = new RepeatedDnaSequences().new Solution();
        List<String> result = solution.findRepeatedDnaSequences_answer("AAAAAAAAAAA");
        System.out.println(result.toString());
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
//        这是个挺有意思的题，有实际意义，让我想起来《银翼杀手》里看到的dna序列
    public List<String> findRepeatedDnaSequences(String s) {
        //字符串怎么hash呢，加法 乘法的话 因为满足交换律，所以会丢失顺序。如果把字符看做编码，那么字符串就已经是编码后的结果。
        //这个找全部子串，只是固定了长度。而最长重复子序列，是不固定长度的，需要找最长的长度。找重复子串 都是必须做的。
        //按我当前思路：遍历一遍找大于1的，指定长度如果包含仅1次的字符，则剪枝,，此问题场景剪枝可以注释，因为dna总共四个字母，重复可能性太高，几乎不会出现仅有一次的字母
//        Map<Character, Integer> countMap = new HashMap<>();
//        for(int i = 0; i < s.length(); i++){
//            Character c = s.charAt(i);
//            if(countMap.containsKey(c)){
//                countMap.put(c, countMap.get(c)+1);
//            } else {
//                countMap.put(c, 1);
//            }
//        }

        List<String> result = new ArrayList<>();
        for(int i = 0; i < s.length() - 10; i++){
//            int countNum = i+1;
//            boolean flag = true;
//            while(countNum < i+10){
//                if(countMap.get(s.charAt(countNum)) < 2){
//                    flag = false;
//                    break;
//                }
//                countNum++;
//            }
//            if(!flag){
//                continue;
//            }
            // 注意j这里有等于，开始把等于落了导致代码不通过
            for(int j = i+1; j <= s.length() - 10; j++){
                int start = i;
                int end = j;
                while(start < end && end < s.length()){
                    Character s1 = s.charAt(start);
                    Character s2 = s.charAt(end);
                    if(s1 == s2){
                        String tempResult = s.substring(i, i+10);
                        //注意 start判断i+9 因为判断的是10个字符中的最后一个字符
                        if(start == i+9 && !result.contains(tempResult)){
                            result.add(tempResult);
                        }
                        start++;
                        end++;
                    } else {
                        break;
                    }
                }
            }
        }
        return result;
    }

//    滑动窗口+hash

        /**
         * 这个滑动窗口，说白了就是看一个字符串（窗口能看到的内容），将这个字符串看做一个整体来处理，然后不断挪动窗口
         * 滑动窗口应该有自己的适用问题，这类重复字符串问题 肯定是其中一个
         * 此题还妙在，仅第二次出现时加入，这样不需要用set来去重，也解决了重复加入的问题
         * 判断是否存在，还是取巧了，直接利用了hash结构的取值，取不到说明次数为0，取到值则证明存在，且存在几次就拿到了
         * 取不到给个默认值，这种方式是写代码的一个很好的简化，以后可以多采用
         */
    public List<String> findRepeatedDnaSequences_answer(String s) {
        List<String> ans = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        //当且仅当第二次出现的时候放入结果集
        for(int i = 10; i <= s.length(); i++){
            String str = s.substring(i-10, i);
            int cnt = map.getOrDefault(str, 0);
            if(cnt == 1)
                ans.add(str);
            map.put(str, cnt+1);
        }
        return ans;
    }

//    这种重复很有意思，还有那种可以不连续的重复的，感觉更复杂一点，因为那样组合情况会更多，连续相当于一个限制条件

//    还有一个解是使用前缀和，去做303看看什么是前缀和
}
//leetcode submit region end(Prohibit modification and deletion)

}
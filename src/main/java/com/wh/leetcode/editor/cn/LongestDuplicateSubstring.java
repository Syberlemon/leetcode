package com.wh.leetcode.editor.cn;
//给你一个字符串 s ，考虑其所有 重复子串 ：即，s 的连续子串，在 s 中出现 2 次或更多次。这些出现之间可能存在重叠。 
//
// 返回 任意一个 可能具有最长长度的重复子串。如果 s 不含重复子串，那么答案为 "" 。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "banana"
//输出："ana"
// 
//
// 示例 2： 
//
// 
//输入：s = "abcd"
//输出：""
// 
//
// 
//
// 提示： 
//
// 
// 2 <= s.length <= 3 * 104 
// s 由小写英文字母组成 
// 
// Related Topics 字符串 二分查找 后缀数组 滑动窗口 哈希函数 滚动哈希 
// 👍 256 👎 0

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class LongestDuplicateSubstring {

    public static void main(String[] args) {
        Solution solution = new LongestDuplicateSubstring().new Solution();
        String maxSame = solution.longestDupSubstring(
                "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        System.out.println(maxSame);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public String longestDupSubstring(String s) {
            //遍历一遍计算一下字母的个数
            //感觉应该有两个走法，一个走一步，一个走两步，那是在图中找环，感觉这个也是在找环，找环入口，环出口，图不会写了
            if (s == null || "".equals(s)) {
                return "";
            }
            Map<Character, Integer> countMap = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                Character c = s.charAt(i);
                if (countMap.containsKey(c)) {
                    countMap.put(c, countMap.get(c) + 1);
                } else {
                    countMap.put(c, 1);
                }
            }
            String maxSame = "";
//        String countStr = "";
            for (int m = 0; m < s.length() - 1; m++) {
                if (countMap.get(s.charAt(m)) < 2) {
                    continue;
                }
                //之前算过的部分,这么一加就错了。。 因为可能后面的更长
//            if(countStr.indexOf(s.charAt(m)) > 0){
//                continue;
//            } else {
//                countStr += s.charAt(m);
//            }
                for (int n = m + 1; n < s.length(); n++) {
                    int begin = m;
                    int curr = n;
                    StringBuilder same = new StringBuilder("");
                    while (begin < curr && begin < s.length() && curr < s.length()) {
                        if (s.charAt(begin) == s.charAt(curr)) {
                            //可以继续比较下一个字符
                            same.append(s.charAt(begin));
                            if (maxSame.length() < same.length()) {
                                maxSame = same.toString();
                            }
                            begin++;
                            curr++;
                        } else {
                            break;
                        }
                    }
                }
            }
            return maxSame;

        }

        //二分查找确定最长重复子串的长度；Rabin-Karp字符串编码 来高效的判断s中是否有长度为L的重复子串
        public String longestDupSubstring_answer(String s) {
            Random random = new Random();
            int a1 = random.nextInt(75) + 26;
            int a2 = random.nextInt(75) + 26;
            int mod1 = random.nextInt(Integer.MAX_VALUE - 1000000007 + 1) + 1000000007;
            int mod2 = random.nextInt(Integer.MAX_VALUE - 1000000007 + 1) + 1000000007;
            // 对所有字符进行编码
            int n = s.length();
            int[] arr = new int[n];
            for (int i = 0; i < n; ++i) {
                arr[i] = s.charAt(i) - 'a';
            }
            // 二分查找的范围是[1, n-1]
            // 为什么能根据是否有重复子串，决定移动左边界还是右边界？
            int left = 1, right = n - 1;
            int length = 0, start = -1;
            while (left <= right) {
                int middle = left + (right - left + 1) / 2;
                int idx = check(arr, middle, a1, a2, mod1, mod2);
                if (idx != -1) {
                    //有重复子串，移动左边界  ??
                    left = middle + 1;
                    length = middle;
                    start = idx;
                } else {
                    //无重复子串，移动右边界  ??
                    right = middle - 1;
                }
            }
            return start != -1 ? s.substring(start, start + length) : "";
        }

        //这个方法没看懂,middle作为入参传进来，如何判断的有无重复子串？ hash是怎么做的也没看懂
        public int check(int[] arr, int m, int a1, int a2, int mod1, int mod2 ){
            int n = arr.length;
            long aL1 = pow(a1, m, mod1);
            long aL2 = pow(a2, m, mod2);
            long h1 = 0, h2 = 0;
            for (int i = 0; i < m; ++i) {
                h1 = (h1 * a1 % mod1 + arr[i]) % mod1;
                h2 = (h2 * a2 % mod2 + arr[i]) % mod2;
                if(h1 < 0) {
                    h1 += mod1;
                }
                if(h2 < 0) {
                    h2 += mod2;
                }
            }
            // 存储一个编码组合是否出现过
            Set<Long> seen = new HashSet<Long>();
            seen.add(h1 * mod2 + h2);
            for (int start = 1; start <= n-m; ++start) {
                h1 = (h1 * a1 % mod1 - arr[start -1] * aL1 % mod1 + arr[start + m -1]) % mod1;
                h2 = (h2 * a2 % mod2 - arr[start -1] * aL2 % mod2 + arr[start + m -1]) % mod2;
                if(h1 < 0){
                    h1 += mod1;
                }
                if(h2 < 0){
                    h2 += mod2;
                }
                long num = h1 * mod2 + h2;
                //如果重复，则返回重复串的起点
                if(!seen.add(num)){
                    return start;
                }
            }
            return -1;
        }

        public long pow(int a, int m, int mod){
            long ans = 1;
            long contribute = a;
            while(m > 0){
                if(m % 2 == 1){
                    ans = ans * contribute % mod;
                    if(ans < 0){
                        ans += mod;
                    }
                }
                contribute = contribute * contribute % mod;
                if( contribute < 0) {
                    contribute += mod;
                }
                m /= 2;
            }
            return ans;
        }

        // 宫水三叶题解不抄了，直接看吧，有点看不懂
    }
//leetcode submit region end(Prohibit modification and deletion)

}
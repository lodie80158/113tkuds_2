import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return res;

        int wordLen = words[0].length();
        int numWords = words.length;
        int totalLen = wordLen * numWords;

        if (s.length() < totalLen) return res;

        // 統計每個單詞出現的次數
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        // 滑動視窗，分不同 offset 開始檢查
        for (int i = 0; i < wordLen; i++) {
            int left = i, count = 0;
            Map<String, Integer> seen = new HashMap<>();

            for (int j = i; j <= s.length() - wordLen; j += wordLen) {
                String sub = s.substring(j, j + wordLen);
                if (wordCount.containsKey(sub)) {
                    seen.put(sub, seen.getOrDefault(sub, 0) + 1);
                    count++;

                    // 如果某單詞出現次數超過，移動左邊界
                    while (seen.get(sub) > wordCount.get(sub)) {
                        String leftWord = s.substring(left, left + wordLen);
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                    // 找到一個匹配
                    if (count == numWords) {
                        res.add(left);
                        // 移動左邊界，尋找下一個可能解
                        String leftWord = s.substring(left, left + wordLen);
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }
                } else {
                    // 不在 words 裡，重置
                    seen.clear();
                    count = 0;
                    left = j + wordLen;
                }
            }
        }

        return res;
    }
}

/*
解題思路：
1. 因為每個單詞長度相同，可將字串切成固定長度塊來檢查。
2. 使用 HashMap 記錄 words 的目標計數。
3. 滑動視窗逐個檢查 substring：
   - seen 用來記錄目前窗口內單詞出現次數
   - 超過次數時，左邊界右移，更新 seen
   - count == numWords → 找到一個合法起點
4. 注意要從不同 offset (0 ~ wordLen-1) 開始，避免漏掉跨單詞對齊的匹配。
5. 時間複雜度 O(L * N) （L = wordLen, N = s.length / wordLen）
   空間 O(M) （M = words.length）
*/

import java.util.*;

class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res; // 空輸入返回空列表

        // 數字對應的字母表
        String[] map = {
            "",     // 0
            "",     // 1
            "abc",  // 2
            "def",  // 3
            "ghi",  // 4
            "jkl",  // 5
            "mno",  // 6
            "pqrs", // 7
            "tuv",  // 8
            "wxyz"  // 9
        };

        backtrack(res, new StringBuilder(), digits, 0, map); // 開始回溯
        return res;
    }

    private void backtrack(List<String> res, StringBuilder path, String digits, int index, String[] map) {
        // 遞迴結束條件：已處理完所有位數
        if (index == digits.length()) {
            res.add(path.toString()); // 加入一組結果
            return;
        }

        String letters = map[digits.charAt(index) - '0']; // 取得當前數字對應字母
        for (char c : letters.toCharArray()) {
            path.append(c); // 選擇一個字母
            backtrack(res, path, digits, index + 1, map); // 處理下一位數字
            path.deleteCharAt(path.length() - 1); // 回溯，移除最後選的字母
        }
    }
}

/*
解題邏輯與思路：
1. 問題本質：
   - 手機按鍵數字 2~9 對應多個字母，給定數字序列，找出所有可能字母組合。

2. 解法設計（回溯 Backtracking）：
   - 對每個數字，列出其對應的字母。
   - 遞迴構建每一位的字母選擇：
       1. 當 index == digits.length() → 遞迴終止，加入結果。
       2. 否則對當前數字所有字母進行選擇：
           - 選擇字母 → 遞迴下一位數字 → 回溯撤銷選擇。
   - 使用 StringBuilder 避免每次建立新字串，提高效率。

3. 範例：
   - digits = "23"
   - map[2] = "abc", map[3] = "def"
   - 遞迴生成結果：["ad","ae","af","bd","be","bf","cd","ce","cf"]

4. 為什麼可行：
   - 回溯能列舉所有可能組合，確保不漏掉任何一種排列。
   - 去掉重複字母的問題不需特別處理，因為每位數字的字母唯一。

時間複雜度：O(3^m * 4^n)  
- m = digits 中對應 3 個字母的數字個數  
- n = digits 中對應 4 個字母的數字個數  
- 因為每個數字都可能選擇多個字母，所以總組合數就是乘積。

空間複雜度：O(m+n)  
- 遞迴棧深度等於 digits 長度。
*/

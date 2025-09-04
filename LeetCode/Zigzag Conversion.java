class Solution {
    public String convert(String s, int numRows) {
        // 如果只有一行，或字串長度不夠換行，直接返回原字串
        if (numRows == 1 || s.length() <= numRows) return s;

        // 建立每行的 StringBuilder
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int curRow = 0;         // 當前行
        boolean goingDown = false; // 是否往下走

        // 遍歷字元，按照鋸齒形分配到每一行
        for (char c : s.toCharArray()) {
            rows[curRow].append(c);

            // 碰到最上或最下就反轉方向
            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            }
            curRow += goingDown ? 1 : -1;
        }

        // 將各行合併成最終結果
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }
}

/*
解題邏輯與思路：
1. 問題本質：
   - 將字串按鋸齒形 (Z 字形) 排列，然後按行讀出。

2. 解法設計：
   - 用 StringBuilder[] rows 儲存每一行的字元。
   - 使用 curRow 跟蹤當前行，goingDown 控制方向。
   - 遍歷字元：
       - 加入對應行 rows[curRow]
       - 遇到最上或最下行 → 方向反轉
       - 移動到下一行
   - 最後將每行字串合併。

3. 範例：
   - s = "PAYPALISHIRING", numRows = 3
   - 分行後：
     P   A   H   N
     A P L S I I G
     Y   I   R
   - 合併結果："PAHNAPLSIIGYIR"

4. 為什麼可行：
   - 模擬鋸齒形排列，時間 O(n)，空間 O(n)

時間複雜度：O(n)  
空間複雜度：O(n)
*/

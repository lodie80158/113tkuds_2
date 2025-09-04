class Solution {
    public String intToRoman(int num) {
        // 建立羅馬數字對應表：數值 -> 符號
        int[] values =    {1000, 900, 500, 400, 100, 90,  50,  40, 10,  9,   5,  4, 1};
        String[] symbols ={"M", "CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        StringBuilder sb = new StringBuilder(); // 用來累積結果字串

        // 從大到小遍歷 values
        for (int i = 0; i < values.length && num > 0; i++) {
            // 當前數字大於等於 values[i]，就持續減去並加上對應的羅馬符號
            while (num >= values[i]) {
                num -= values[i];          // 減去相應的數值
                sb.append(symbols[i]);     // 拼接對應的羅馬數字
            }
        }

        return sb.toString(); // 回傳轉換後的羅馬數字
    }
}

/*
解題邏輯與思路：
1. 羅馬數字規則：
   - 有固定對應表，例如：
       1000 -> M
        900 -> CM
        500 -> D
        400 -> CD
        100 -> C
         90 -> XC
         50 -> L
         40 -> XL
         10 -> X
          9 -> IX
          5 -> V
          4 -> IV
          1 -> I
   - 透過「貪心法」：每次用最大的符號去表示數字，直到數字變 0。

2. 解法設計：
   - 建立一組數值陣列 values[] 與對應的符號陣列 symbols[]。
   - 從最大值開始遍歷，能減幾次就加幾次符號，直到 num 小於當前值。
   - 繼續往下檢查更小的數值。
   - 最終得到完整的羅馬數字。

3. 為什麼可行：
   - 羅馬數字的組成方式是有限且固定的。
   - 使用從大到小的貪心策略，能保證正確且不會出現非法組合。

時間複雜度：O(1)  
- 雖然用了 while，但 num 最大 3999，最多迴圈幾十次，屬於常數級別。

空間複雜度：O(1)  
- 使用固定大小的陣列與 StringBuilder。
*/

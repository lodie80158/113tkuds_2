import java.util.HashMap;
import java.util.Map;

class Solution {
    public int romanToInt(String s) {
        // 建立羅馬字母與數值的對應表
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int sum = 0;            // 最後結果
        int n = s.length();     // 字串長度

        // 遍歷字串中的每個字元
        for (int i = 0; i < n; i++) {
            int value = map.get(s.charAt(i)); // 當前字元的數值

            // 規則：如果當前數值 < 下一個數值，則要做減法
            if (i < n - 1 && value < map.get(s.charAt(i + 1))) {
                sum -= value;   // 減去當前值
            } else {
                sum += value;   // 否則加上當前值
            }
        }

        return sum;
    }
}

/*
解題邏輯與思路：
1. 羅馬數字的規則：
   - 基本符號：
       I=1, V=5, X=10, L=50, C=100, D=500, M=1000
   - 特殊規則（小在大前面表示減法）：
       IV=4, IX=9, XL=40, XC=90, CD=400, CM=900

2. 解法設計：
   - 建立一個 HashMap 對應表，存每個羅馬字母的數值。
   - 從左到右遍歷字串：
       - 如果當前數值比下一個數值小，代表這是「減法情況」，要減去當前值。
       - 否則就是「正常情況」，直接加上當前值。
   - 最後得到總和。

3. 範例：
   - s = "MCMXCIV"
   - M(1000) → 加 1000
   - C(100) < M(1000) → 減 100
   - M(1000) → 加 1000
   - X(10) < C(100) → 減 10
   - C(100) → 加 100
   - I(1) < V(5) → 減 1
   - V(5) → 加 5
   - 總和 = 1994

4. 為什麼可行：
   - 羅馬數字的減法規則只會出現在特定組合，透過「比較當前與下一個值」就能判斷。

時間複雜度：O(n)  
- n = 字串長度，掃描一次即可完成。

空間複雜度：O(1)  
- HashMap 大小固定，與輸入長度無關。
*/

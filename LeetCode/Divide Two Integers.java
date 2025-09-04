class Solution {
    public int divide(int dividend, int divisor) {
        // 特殊溢位處理
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // 記錄正負號
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // 轉成 long 避免溢位
        long dvd = Math.abs((long) dividend);
        long dvs = Math.abs((long) divisor);

        int result = 0;
        while (dvd >= dvs) {
            long temp = dvs;
            int multiple = 1;
            // 盡量倍增 divisor
            while (dvd >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            dvd -= temp;
            result += multiple;
        }

        return negative ? -result : result;
    }
}

/*
解題邏輯與思路：
1. 特殊情況：
   - dividend = Integer.MIN_VALUE, divisor = -1 → 溢位，返回 Integer.MAX_VALUE

2. 記錄正負號：
   - 當 dividend 與 divisor 符號不同，結果為負

3. 轉成 long：
   - 防止 Math.abs(Integer.MIN_VALUE) 溢位

4. 位移 + 減法：
   - 逐步從 dividend 減去 divisor 的倍數
   - 每次倍增 divisor，使減法次數減少 → 提升效率

5. 最後根據符號返回結果
6. 時間複雜度 O(logN)，空間 O(1)
*/

class Solution {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0;

        int i = 0, n = s.length();
        // 1. 去掉前導空白
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // 如果全是空格
        if (i == n) return 0;

        // 2. 判斷正負號
        int sign = 1;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // 3. 轉數字
        long res = 0; // 用 long 暫存避免溢位
        while (i < n && Character.isDigit(s.charAt(i))) {
            res = res * 10 + (s.charAt(i) - '0');

            // 4. 檢查溢位
            if (sign == 1 && res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (sign == -1 && -res < Integer.MIN_VALUE) return Integer.MIN_VALUE;

            i++;
        }

        return (int)(res * sign);
    }
}

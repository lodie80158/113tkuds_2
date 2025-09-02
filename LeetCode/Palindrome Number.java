class Solution {
    public boolean isPalindrome(int x) {
        // 負數或 (最後一位是0但數字不是0) → 不是回文
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int rev = 0;
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }

        // 偶數長度: x == rev
        // 奇數長度: x == rev/10 (去掉中間的數字)
        return x == rev || x == rev / 10;
    }
}

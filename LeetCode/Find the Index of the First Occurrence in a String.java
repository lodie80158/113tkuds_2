class Solution {
    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();

        if (m == 0) return 0; // 空字串特殊情況

        for (int i = 0; i <= n - m; i++) { // 窗口長度為 needle.length()
            int j;
            for (j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
            if (j == m) return i; // 找到完整匹配
        }

        return -1; // 沒找到
    }
}

/*
解題邏輯與思路：
1. 特殊情況：
   - needle 為空 → 依題意返回 0
   - haystack 長度小於 needle → 不可能匹配，返回 -1

2. 滑動窗口：
   - 將長度為 m 的窗口滑過 haystack
   - 每個窗口逐個字符比對 needle
   - 如果整個窗口比對成功 → 返回當前索引 i

3. 範例：
   - haystack = "sadbutsad", needle = "sad"
   - 第 0 個窗口 "sad" 匹配 → 返回 0

4. 時間與空間：
   - 時間 O((n-m+1)*m) → 最壞情況
   - 空間 O(1)
*/

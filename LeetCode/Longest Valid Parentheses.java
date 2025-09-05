class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // 初始虛擬索引
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(i); // 存索引
            } else {
                stack.pop(); // 嘗試匹配 '('
                if (stack.isEmpty()) {
                    stack.push(i); // 無匹配，重設起點
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }
}

/*
解題思路：
1. 初始化 stack 放 -1 作為虛擬起點。
2. 遇到 '(' 推入索引。
3. 遇到 ')' 彈出，如果 stack 空了就將當前索引作為新起點。
4. 否則，計算 i - stack.peek() 作為有效括號長度，更新 maxLen。
時間複雜度 O(n)，空間 O(n)。
*/

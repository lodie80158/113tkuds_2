import java.util.*;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>(); // 用來存放開括號
        Map<Character, Character> map = new HashMap<>(); // 閉括號對應開括號
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) { // 如果是閉括號
                // 栈为空或与栈顶不匹配 → 無效
                if (stack.isEmpty() || stack.pop() != map.get(c)) {
                    return false;
                }
            } else { // 如果是開括號
                stack.push(c);
            }
        }

        // 最終栈空 → 所有括號匹配，否則無效
        return stack.isEmpty();
    }
}

/*
解題邏輯與思路：
1. 問題本質：
   - 檢查字串 s 的括號是否完全匹配。

2. 解法設計（堆疊）：
   - 遍歷字元：
       1. 遇到開括號 → push 到 stack。
       2. 遇到閉括號 → 檢查 stack 頂端是否為對應開括號：
           - 空或不匹配 → 返回 false
           - 匹配 → pop
   - 遍歷完後 stack 應該為空，否則有未閉合的開括號。

3. 範例：
   - s = "()" → stack push '(' → pop '(' → stack 空 → true
   - s = "([)]" → 遇到 ')' 時 stack top = '[' → 不匹配 → false

4. 為什麼可行：
   - stack 保持最近未匹配的開括號順序，確保嵌套正確。
   - 單遍歷即可完成檢查。

時間複雜度：O(n)  
空間複雜度：O(n)
*/

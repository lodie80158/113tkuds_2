import java.util.*;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) { // 是閉括號
                if (stack.isEmpty() || stack.pop() != map.get(c)) {
                    return false;
                }
            } else { // 是開括號
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }
}

package finalexam;

import java.util.*;

public class LC20_ValidParentheses_AlertFormat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> st = new Stack<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                if (st.isEmpty() || st.pop() != map.get(c)) return false;
            } else {
                st.push(c);
            }
        }
        return st.isEmpty();
    }
}


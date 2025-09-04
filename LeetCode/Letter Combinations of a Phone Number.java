import java.util.*;

class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;

        String[] map = {
            "",     // 0
            "",     // 1
            "abc",  // 2
            "def",  // 3
            "ghi",  // 4
            "jkl",  // 5
            "mno",  // 6
            "pqrs", // 7
            "tuv",  // 8
            "wxyz"  // 9
        };

        backtrack(res, new StringBuilder(), digits, 0, map);
        return res;
    }

    private void backtrack(List<String> res, StringBuilder path, String digits, int index, String[] map) {
        if (index == digits.length()) {
            res.add(path.toString());
            return;
        }

        String letters = map[digits.charAt(index) - '0'];
        for (char c : letters.toCharArray()) {
            path.append(c);
            backtrack(res, path, digits, index + 1, map);
            path.deleteCharAt(path.length() - 1); // 回溯
        }
    }
}

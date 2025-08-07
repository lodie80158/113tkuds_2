import java.util.*;

public class AdvancedStringRecursion {
    public static void main(String[] args) {
        String str = "abc";
        System.out.println("所有排列組合：");
        permutations("", str);

        System.out.println("字串匹配 'abcde' 中是否包含 'bcd'：" + match("abcde", "bcd", 0, 0));

        System.out.println("移除重複字元結果：" + removeDuplicates("banana", "", new HashSet<>(), 0));

        System.out.println("所有子字串組合：");
        substrings("abc", 0, "");
    }

    static void permutations(String prefix, String remaining) {
        if (remaining.length() == 0) {
            System.out.println(prefix);
            return;
        }
        for (int i = 0; i < remaining.length(); i++) {
            permutations(prefix + remaining.charAt(i),
                         remaining.substring(0, i) + remaining.substring(i + 1));
        }
    }

    static boolean match(String text, String pattern, int i, int j) {
        if (j == pattern.length()) return true;
        if (i == text.length()) return false;
        if (text.charAt(i) == pattern.charAt(j)) return match(text, pattern, i + 1, j + 1);
        return match(text, pattern, i + 1, 0);
    }

    static String removeDuplicates(String str, String result, Set<Character> seen, int index) {
        if (index == str.length()) return result;
        char c = str.charAt(index);
        if (!seen.contains(c)) {
            seen.add(c);
            result += c;
        }
        return removeDuplicates(str, result, seen, index + 1);
    }

    static void substrings(String str, int index, String current) {
        if (index == str.length()) {
            if (!current.isEmpty()) System.out.println(current);
            return;
        }
        substrings(str, index + 1, current + str.charAt(index));
        substrings(str, index + 1, current);
    }
}

import java.util.*;

public class RecursionVsIteration {
    public static void main(String[] args) {
        int n = 5, k = 2;
        System.out.println("遞迴 C(" + n + "," + k + ") = " + binomialRec(n, k));
        System.out.println("迭代 C(" + n + "," + k + ") = " + binomialIter(n, k));

        int[] arr = {2, 3, 4};
        System.out.println("遞迴乘積 = " + productRec(arr, 0));
        System.out.println("迭代乘積 = " + productIter(arr));

        String str = "RecursionAndIteration";
        System.out.println("遞迴元音數 = " + vowelCountRec(str, 0));
        System.out.println("迭代元音數 = " + vowelCountIter(str));

        String brackets = "(([]){})";
        System.out.println("遞迴括號檢查 = " + isBalancedRec(brackets));
        System.out.println("迭代括號檢查 = " + isBalancedIter(brackets));
    }

    static int binomialRec(int n, int k) {
        if (k == 0 || k == n) return 1;
        return binomialRec(n - 1, k - 1) + binomialRec(n - 1, k);
    }

    static int binomialIter(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0 || j == i) dp[i][j] = 1;
                else dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        return dp[n][k];
    }

    static int productRec(int[] arr, int index) {
        if (index == arr.length) return 1;
        return arr[index] * productRec(arr, index + 1);
    }

    static int productIter(int[] arr) {
        int result = 1;
        for (int num : arr) result *= num;
        return result;
    }

    static int vowelCountRec(String s, int index) {
        if (index == s.length()) return 0;
        char c = Character.toLowerCase(s.charAt(index));
        int count = "aeiou".indexOf(c) >= 0 ? 1 : 0;
        return count + vowelCountRec(s, index + 1);
    }

    static int vowelCountIter(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            c = Character.toLowerCase(c);
            if ("aeiou".indexOf(c) >= 0) count++;
        }
        return count;
    }

    static boolean isBalancedRec(String s) {
        return checkBalance(s, 0, new Stack<>());
    }

    static boolean checkBalance(String s, int index, Stack<Character> stack) {
        if (index == s.length()) return stack.isEmpty();
        char c = s.charAt(index);
        if (c == '(' || c == '[' || c == '{') {
            stack.push(c);
            return checkBalance(s, index + 1, stack);
        } else if (c == ')' || c == ']' || c == '}') {
            if (stack.isEmpty()) return false;
            char top = stack.pop();
            if ((c == ')' && top != '(') ||
                (c == ']' && top != '[') ||
                (c == '}' && top != '{')) return false;
            return checkBalance(s, index + 1, stack);
        } else {
            return checkBalance(s, index + 1, stack);
        }
    }

    static boolean isBalancedIter(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') stack.push(c);
            else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == ']' && top != '[') ||
                    (c == '}' && top != '{')) return false;
            }
        }
        return stack.isEmpty();
    }
}

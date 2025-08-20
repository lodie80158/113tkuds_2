package midterm;

import java.util.*;

public class M06_PalindromeClean {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();


        StringBuilder cleaned = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                cleaned.append(Character.toLowerCase(c));
            }
        }

        int left = 0, right = cleaned.length() - 1;
        boolean isPalindrome = true;
        while (left < right) {
            if (cleaned.charAt(left) != cleaned.charAt(right)) {
                isPalindrome = false;
                break;
            }
            left++;
            right--;
        }

        System.out.println(isPalindrome ? "Yes" : "No");
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * - 清洗字串需走訪一次輸入字串 O(n)。
 * - 雙指標檢查回文再走訪一次 O(n)。
 * - 整體時間複雜度 O(n)，n 為輸入長度。
 * 空間複雜度：O(n) 用於存放清洗後字串。
 */

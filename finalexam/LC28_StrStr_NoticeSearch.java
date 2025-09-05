package finalexam;

import java.util.*;

public class LC28_StrStr_NoticeSearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String haystack = sc.nextLine();
        String needle = sc.nextLine();
        System.out.println(strStr(haystack, needle));
    }

    static int strStr(String h, String n) {
        if (n.isEmpty()) return 0;
        for (int i = 0; i + n.length() <= h.length(); i++) {
            if (h.substring(i, i+n.length()).equals(n)) return i;
        }
        return -1;
    }
}

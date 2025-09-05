package finalexam;
import java.util.*;

public class LC11_MaxArea_FuelHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] h = new int[n];
        for (int i = 0; i < n; i++) h[i] = sc.nextInt();
        System.out.println(maxArea(h));
    }

    public static int maxArea(int[] h) {
        int l = 0, r = h.length - 1, ans = 0;
        while (l < r) {
            ans = Math.max(ans, (r - l) * Math.min(h[l], h[r]));
            if (h[l] < h[r]) l++;
            else r--;
        }
        return ans;
    }
}

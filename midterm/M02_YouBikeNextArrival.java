package midterm;
import java.util.*;

public class M02_YouBikeNextArrival {
    static int toMinutes(String t) {
        String[] p = t.split(":");
        return Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);
    }
    static String toTime(int m) {
        return String.format("%02d:%02d", m / 60, m % 60);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        int[] times = new int[n];
        for (int i = 0; i < n; i++) times[i] = toMinutes(sc.nextLine().trim());
        int query = toMinutes(sc.nextLine().trim());

        int l = 0, r = n - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (times[mid] > query) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        if (ans == -1) System.out.println("No bike");
        else System.out.println(toTime(times[ans]));
    }
}

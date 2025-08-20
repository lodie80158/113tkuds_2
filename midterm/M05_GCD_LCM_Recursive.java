package midterm;

import java.util.*;

public class M05_GCD_LCM_Recursive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();

        long g = gcd(a, b);
        long l = (a / g) * b;  

        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }

    // 遞迴歐幾里得算法
    private static long gcd(long x, long y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }
}

/*
 * Time Complexity: O(log(min(a, b)))
 * 說明：歐幾里得算法每次遞迴將餘數縮小，收斂速度近似對數級，因此時間複雜度為 O(log(min(a, b)))。
 * 計算 LCM 只需一次除法與乘法，為 O(1)。整體時間複雜度為 O(log(min(a, b)))。
 */


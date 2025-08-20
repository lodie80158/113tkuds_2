package midterm;

import java.util.*;

public class M04_TieredTaxSimple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long total = 0;
        for (int i = 0; i < n; i++) {
            long income = sc.nextLong();
            long tax = calcTax(income);
            total += tax;
            System.out.println("Tax: " + tax);
        }
        System.out.println("Average: " + (total / n));
    }

    private static long calcTax(long income) {
        long tax = 0;
        if (income > 1_000_000) {
            tax += (income - 1_000_000) * 30 / 100;
            income = 1_000_000;
        }
        if (income > 500_000) {
            tax += (income - 500_000) * 20 / 100;
            income = 500_000;
        }
        if (income > 120_000) {
            tax += (income - 120_000) * 12 / 100;
            income = 120_000;
        }
        if (income > 0) {
            tax += income * 5 / 100;
        }
        return tax;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：每筆收入在計算稅額時僅需固定 4 個區間判斷，為 O(1)。總共處理 n 筆輸入，因此時間複雜度為 O(n)。
 */


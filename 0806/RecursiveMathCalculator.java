import java.util.Scanner;

public class RecursiveMathCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("輸入 n 和 k 計算組合數 C(n, k)：");
        int n = sc.nextInt();
        int k = sc.nextInt();
        System.out.println("C(" + n + ", " + k + ") = " + combination(n, k));

        System.out.print("輸入 n 計算第 n 個卡塔蘭數：");
        int catalanN = sc.nextInt();
        System.out.println("Catalan(" + catalanN + ") = " + catalan(catalanN));

        System.out.print("輸入 n 計算漢諾塔移動步數：");
        int hanoiN = sc.nextInt();
        System.out.println("Hanoi(" + hanoiN + ") = " + hanoi(hanoiN));

        System.out.print("輸入一個整數判斷是否為回文數：");
        int number = sc.nextInt();
        System.out.println("是否為回文數：" + isPalindrome(number));
    }

    static int combination(int n, int k) {
        if (k == 0 || k == n) return 1;
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    static int catalan(int n) {
        if (n == 0) return 1;
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += catalan(i) * catalan(n - 1 - i);
        return sum;
    }

    static int hanoi(int n) {
        if (n == 1) return 1;
        return 2 * hanoi(n - 1) + 1;
    }

    static boolean isPalindrome(int n) {
        return n == reverse(n, 0);
    }

    static int reverse(int n, int rev) {
        if (n == 0) return rev;
        return reverse(n / 10, rev * 10 + n % 10);
    }
}

package midterm;

import java.util.*;

public class M11_HeapSortWithTie {
    static class Student {
        int score, index;
        Student(int s, int i) { score = s; index = i; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Student[] arr = new Student[n];
        for (int i = 0; i < n; i++) {
            int s = sc.nextInt();
            arr[i] = new Student(s, i);
        }

        heapSort(arr);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i].score + (i == n - 1 ? "\n" : " "));
        }
    }

    private static void heapSort(Student[] arr) {
        int n = arr.length;

   
        for (int i = n / 2 - 1; i >= 0; i--) heapify(arr, n, i);


        for (int i = n - 1; i > 0; i--) {
            Student temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    private static void heapify(Student[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && compare(arr[l], arr[largest]) > 0) largest = l;
        if (r < n && compare(arr[r], arr[largest]) > 0) largest = r;

        if (largest != i) {
            Student temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);
        }
    }


    private static int compare(Student a, Student b) {
        if (a.score != b.score) return a.score - b.score;
        return b.index - a.index; 
    }
}

/*
 * Time Complexity: O(n log n)
 * - 建堆 O(n)
 * - 每次取最大值並 heapify O(n log n)
 * => 總體 O(n log n)
 * Space Complexity: O(1) 原地排序
 */

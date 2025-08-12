import java.util.*;

public class KthSmallestElement {
    public static int kthSmallestMaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : arr) {
            maxHeap.add(num);
            if (maxHeap.size() > k) maxHeap.poll();
        }
        return maxHeap.peek();
    }

    public static int kthSmallestMinHeap(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) minHeap.add(num);
        int res = -1;
        for (int i = 0; i < k; i++) res = minHeap.poll();
        return res;
    }

    public static void main(String[] args) {
        int[][] arrays = {
            {7, 10, 4, 3, 20, 15},
            {1},
            {3, 1, 4, 1, 5, 9, 2, 6}
        };
        int[] ks = {3, 1, 4};
        for (int i = 0; i < arrays.length; i++) {
            System.out.println("方法1: " + kthSmallestMaxHeap(arrays[i], ks[i]));
            System.out.println("方法2: " + kthSmallestMinHeap(arrays[i], ks[i]));
        }
    }
}

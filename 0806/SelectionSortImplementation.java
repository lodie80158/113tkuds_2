import java.util.Arrays;

public class SelectionSortImplementation {
    public static void main(String[] args) {
        int[] original = {64, 25, 12, 22, 11};
        int[] selectionArr = Arrays.copyOf(original, original.length);
        int[] bubbleArr = Arrays.copyOf(original, original.length);

        int[] selectionResult = selectionSort(selectionArr);
        int selectionComparisons = selectionResult[0];
        int selectionSwaps = selectionResult[1];

        System.out.println("選擇排序結果：" + Arrays.toString(selectionArr));
        System.out.println("比較次數：" + selectionComparisons);
        System.out.println("交換次數：" + selectionSwaps);

        int[] bubbleResult = bubbleSort(bubbleArr);
        int bubbleComparisons = bubbleResult[0];
        int bubbleSwaps = bubbleResult[1];

        System.out.println("氣泡排序結果：" + Arrays.toString(bubbleArr));
        System.out.println("比較次數：" + bubbleComparisons);
        System.out.println("交換次數：" + bubbleSwaps);
    }

    static int[] selectionSort(int[] arr) {
        int n = arr.length;
        int comparisons = 0;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIdx])
                    minIdx = j;
            }
            if (minIdx != i) {
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
                swaps++;
            }
            System.out.println("第 " + (i + 1) + " 輪：" + Arrays.toString(arr));
        }

        return new int[]{comparisons, swaps};
    }

    static int[] bubbleSort(int[] arr) {
        int n = arr.length;
        int comparisons = 0;
        int swaps = 0;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }
            System.out.println("第 " + (i + 1) + " 輪：" + Arrays.toString(arr));
            if (!swapped) break;
        }

        return new int[]{comparisons, swaps};
    }
}

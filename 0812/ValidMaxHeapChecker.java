

public class ValidMaxHeapChecker {
    public static boolean isValidMaxHeap(int[] arr) {
        for (int i = (arr.length - 2) / 2; i >= 0; i--) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < arr.length && arr[i] < arr[left]) return false;
            if (right < arr.length && arr[i] < arr[right]) return false;
        }
        return true;
    }

    public static int firstViolationIndex(int[] arr) {
        for (int i = (arr.length - 2) / 2; i >= 0; i--) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < arr.length && arr[i] < arr[left]) return left;
            if (right < arr.length && arr[i] < arr[right]) return right;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] tests = {
            {100, 90, 80, 70, 60, 75, 65},
            {100, 90, 80, 95, 60, 75, 65},
            {50},
            {}
        };
        for (int[] arr : tests) {
            boolean valid = isValidMaxHeap(arr);
            System.out.print(valid);
            if (!valid) System.out.print(" (索引" + firstViolationIndex(arr) + "的" + arr[firstViolationIndex(arr)] + "大於父節點" + arr[(firstViolationIndex(arr) - 1) / 2] + ")");
            System.out.println();
        }
    }
}


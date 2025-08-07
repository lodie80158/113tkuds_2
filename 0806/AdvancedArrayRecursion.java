import java.util.*;

public class AdvancedArrayRecursion {
    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 7, 3};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("快速排序結果：" + Arrays.toString(arr));

        int[] sortedA = {1, 3, 5, 7};
        int[] sortedB = {2, 4, 6, 8};
        int[] merged = merge(sortedA, sortedB, 0, 0, new ArrayList<>());
        System.out.println("遞迴合併結果：" + Arrays.toString(merged));

        int[] kthArr = {7, 4, 6, 3, 9, 1};
        int k = 3;
        int kth = findKthSmallest(kthArr, 0, kthArr.length - 1, k);
        System.out.println("第 " + k + " 小元素：" + kth);

        int[] sumArr = {3, 1, 7, 5, 4};
        int target = 9;
        boolean hasSubset = subsetSum(sumArr, 0, target);
        System.out.println("是否存在總和為 " + target + " 的子序列：" + hasSubset);
    }

    static void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int pivot = arr[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
            }
        }
        int tmp = arr[i];
        arr[i] = arr[right];
        arr[right] = tmp;
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }

    static int[] merge(int[] a, int[] b, int i, int j, List<Integer> result) {
        if (i == a.length && j == b.length) {
            int[] r = new int[result.size()];
            for (int k = 0; k < result.size(); k++) r[k] = result.get(k);
            return r;
        }
        if (i < a.length && (j == b.length || a[i] <= b[j])) {
            result.add(a[i]);
            return merge(a, b, i + 1, j, result);
        }
        if (j < b.length) {
            result.add(b[j]);
            return merge(a, b, i, j + 1, result);
        }
        return new int[0];
    }

    static int findKthSmallest(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];
        int pivot = arr[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
            }
        }
        int tmp = arr[i];
        arr[i] = arr[right];
        arr[right] = tmp;
        int count = i - left + 1;
        if (k == count) return arr[i];
        else if (k < count) return findKthSmallest(arr, left, i - 1, k);
        else return findKthSmallest(arr, i + 1, right, k - count);
    }

    static boolean subsetSum(int[] arr, int index, int target) {
        if (target == 0) return true;
        if (index == arr.length) return false;
        if (subsetSum(arr, index + 1, target - arr[index])) return true;
        if (subsetSum(arr, index + 1, target)) return true;
        return false;
    }
}

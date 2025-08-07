import java.util.*;

public class NumberArrayProcessor {
    public static void main(String[] args) {
        int[] arr1 = {3, 5, 1, 3, 7, 5, 9, 1};
        int[] unique = removeDuplicates(arr1);
        System.out.println("移除重複後：" + Arrays.toString(unique));

        int[] sortedA = {1, 3, 5, 7};
        int[] sortedB = {2, 4, 6, 8};
        int[] merged = mergeSortedArrays(sortedA, sortedB);
        System.out.println("合併後：" + Arrays.toString(merged));

        int[] freqTest = {1, 2, 2, 3, 3, 3, 4};
        int mostFrequent = findMostFrequent(freqTest);
        System.out.println("最常出現的元素：" + mostFrequent);

        int[] toSplit = {1, 2, 3, 4, 5, 6, 7};
        List<List<Integer>> splitResult = splitArray(toSplit);
        System.out.println("子陣列 A：" + splitResult.get(0));
        System.out.println("子陣列 B：" + splitResult.get(1));
    }

    static int[] removeDuplicates(int[] arr) {
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        for (int n : arr) set.add(n);
        int[] result = new int[set.size()];
        int i = 0;
        for (int n : set) result[i++] = n;
        return result;
    }

    static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length)
            result[k++] = a[i] < b[j] ? a[i++] : b[j++];
        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];
        return result;
    }

    static int findMostFrequent(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int n : arr) freqMap.put(n, freqMap.getOrDefault(n, 0) + 1);
        int maxKey = arr[0], maxFreq = 0;
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > maxFreq) {
                maxFreq = entry.getValue();
                maxKey = entry.getKey();
            }
        }
        return maxKey;
    }

    static List<List<Integer>> splitArray(int[] arr) {
        Arrays.sort(arr);
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        int sumA = 0, sumB = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (sumA <= sumB) {
                a.add(arr[i]);
                sumA += arr[i];
            } else {
                b.add(arr[i]);
                sumB += arr[i];
            }
        }
        return Arrays.asList(a, b);
    }
}

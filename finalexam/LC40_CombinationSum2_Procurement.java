package finalexam;

import java.util.*;

public class LC40_CombinationSum2_Procurement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        Arrays.sort(arr);
        List<List<Integer>> res = new ArrayList<>();
        dfs(arr, target, 0, new ArrayList<>(), res);
        for (List<Integer> comb : res) {
            for (int i = 0; i < comb.size(); i++) {
                System.out.print(comb.get(i) + (i < comb.size()-1 ? " " : ""));
            }
            System.out.println();
        }
    }

    static void dfs(int[] a, int t, int idx, List<Integer> path, List<List<Integer>> res) {
        if (t == 0) { res.add(new ArrayList<>(path)); return; }
        if (t < 0) return;
        for (int i = idx; i < a.length; i++) {
            if (i > idx && a[i] == a[i-1]) continue;
            path.add(a[i]);
            dfs(a, t-a[i], i+1, path, res);
            path.remove(path.size()-1);
        }
    }
}


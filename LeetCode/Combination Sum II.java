import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates); // 先排序，方便跳過重複元素
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }
    
    // 回溯函式
    private void backtrack(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) { // 找到一組總和等於 target
            res.add(new ArrayList<>(path)); // 將當前組合加入結果
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) break; // 剪枝：當前數字大於剩餘 target
            if (i > start && candidates[i] == candidates[i - 1]) continue; // 同層重複跳過
            path.add(candidates[i]); // 選擇當前數字
            backtrack(candidates, target - candidates[i], i + 1, path, res); // 每個元素只能使用一次
            path.remove(path.size() - 1); // 回溯
        }
    }
}

/*
解題思路：
1. 與 combinationSum 類似，但每個數字只能用一次。
2. 排序 candidates 方便：
   - 剪枝（當數字大於 target 時停止）
   - 避免同層重複（i > start && candidates[i] == candidates[i - 1]）
3. 回溯過程：
   - path 保存當前組合
   - 遞迴時傳 i+1，保證每個數字只使用一次
   - 遇到 target == 0 時加入結果
4. 最終回溯完所有組合後返回結果。
時間複雜度：O(2^n)，最壞情況需遍歷所有組合
空間複雜度：O(n)，遞迴棧和 path 使用
*/

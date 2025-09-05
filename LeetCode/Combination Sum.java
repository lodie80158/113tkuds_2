import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }
    
    // 回溯函式
    private void backtrack(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) { // 找到一組總和等於 target
            res.add(new ArrayList<>(path)); // 將當前路徑加入結果
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) continue; // 剪枝：當前數字大於剩餘 target，無法使用
            path.add(candidates[i]); // 選擇當前數字
            backtrack(candidates, target - candidates[i], i, path, res); // 可重複選同一數字，傳 i
            path.remove(path.size() - 1); // 回溯，撤銷選擇
        }
    }
}

/*
解題思路：
1. 目標是找到所有候選數字組合，使其總和等於 target。
2. 使用回溯 (backtracking)：
   - 用一個 list path 紀錄當前選擇的數字組合
   - 從 start 開始遍歷 candidates
   - 若當前數字大於 target，剪枝，直接跳過
   - 否則選擇該數字，遞迴呼叫，並更新 target
   - 回溯時移除最後一個數字，探索其他組合
3. 因為數字可以重複使用，遞迴呼叫時傳入 i，而非 i+1。
4. 最終所有符合條件的組合會加入結果集 res。
時間複雜度：O(2^t)，其中 t 為 target（最壞情況所有組合都需探索）
空間複雜度：O(t)，用於 path 和遞迴棧
*/

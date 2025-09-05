import java.util.HashSet;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        HashSet<String> seen = new HashSet<>();
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') continue;
                
                String rowKey = "row" + i + c;
                String colKey = "col" + j + c;
                String boxKey = "box" + (i / 3) + (j / 3) + c;
                
                if (seen.contains(rowKey) || seen.contains(colKey) || seen.contains(boxKey)) {
                    return false;
                }
                
                seen.add(rowKey);
                seen.add(colKey);
                seen.add(boxKey);
            }
        }
        
        return true;
    }
}

/*
解題思路：
1. 遍歷每個 cell。
2. 對非 '.' 的數字，生成三個 key：
   - "rowiX" 代表第 i 行出現 X
   - "coljX" 代表第 j 列出現 X
   - "boxabX" 代表第 (i/3, j/3) 宮出現 X
3. 用 HashSet 判斷是否已出現過，若有重複則返回 false。
4. 否則最後返回 true。
時間複雜度 O(1)，因為固定 9x9。
*/

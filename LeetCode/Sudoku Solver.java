class Solution {
    public void solveSudoku(char[][] board) {
        backtrack(board, 0, 0);
    }
    
    private boolean backtrack(char[][] board, int row, int col) {
        if (row == 9) return true; // 全部格子都填完
        
        if (col == 9) return backtrack(board, row + 1, 0); // 換下一行
        
        if (board[row][col] != '.') return backtrack(board, row, col + 1); // 已填數字，跳過
        
        for (char c = '1'; c <= '9'; c++) {
            if (isValid(board, row, col, c)) {
                board[row][col] = c;
                if (backtrack(board, row, col + 1)) return true;
                board[row][col] = '.'; // 回溯
            }
        }
        
        return false; // 該格無法填入任何數字，返回 false
    }
    
    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            // 行/列檢查
            if (board[row][i] == c || board[i][col] == c) return false;
            // 3x3 宮檢查
            int boxRow = 3 * (row / 3) + i / 3;
            int boxCol = 3 * (col / 3) + i % 3;
            if (board[boxRow][boxCol] == c) return false;
        }
        return true;
    }
}
/*
解題思路：
1. 題目要求填滿 9x9 的數獨，滿足每行、每列、每個 3x3 宮內數字 1~9 不重複。
2. 使用回溯 (Backtracking) 遞迴：
   - 從左上角 (0,0) 開始，逐格嘗試填入 1~9。
   - 若該格已填，跳到下一格。
   - 嘗試填入數字前，檢查該數字在行、列、3x3 宮中是否合法。
3. 若填入數字後能完成整個棋盤，回傳 true。
4. 若該數字無法繼續填滿整個棋盤，則回溯，把該格還原為 '.'，嘗試下一個數字。
5. 遞迴遍歷完成後，棋盤即被填滿。
時間複雜度：最壞情況 O(9^(m*n))，m*n 為空格數量
空間複雜度：O(m*n) 遞迴堆疊深度
*/
import java.util.Scanner;

public class TicTacToeBoard {
    public static void main(String[] args) {
        char[][] board = new char[3][3];
        Scanner scanner = new Scanner(System.in);
        initBoard(board);
        char currentPlayer = 'X';
        boolean gameOver = false;

        while (!gameOver) {
            printBoard(board);
            System.out.println("玩家 " + currentPlayer + " 請輸入 row 和 col（0~2）:");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (!isValidMove(board, row, col)) {
                System.out.println("無效位置，請重試。");
                continue;
            }

            board[row][col] = currentPlayer;

            if (checkWin(board, currentPlayer)) {
                printBoard(board);
                System.out.println("玩家 " + currentPlayer + " 獲勝！");
                gameOver = true;
            } else if (isBoardFull(board)) {
                printBoard(board);
                System.out.println("平手！");
                gameOver = true;
            } else {
                currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
            }
        }
    }

    static void initBoard(char[][] board) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = ' ';
    }

    static void printBoard(char[][] board) {
        System.out.println("-------");
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            for (int j = 0; j < 3; j++)
                System.out.print(board[i][j] + "|");
            System.out.println();
            System.out.println("-------");
        }
    }

    static boolean isValidMove(char[][] board, int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    static boolean checkWin(char[][] board, char player) {
        for (int i = 0; i < 3; i++)
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player)
                return true;
        for (int j = 0; j < 3; j++)
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player)
                return true;
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player)
            return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player)
            return true;
        return false;
    }

    static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return false;
        return true;
    }
}

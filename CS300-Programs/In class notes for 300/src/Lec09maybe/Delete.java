package Lec09maybe;

public class Delete {

    public static void setBoard(int[][] board) {
        board = new int[3][2];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0;
            }
        }
        return;
    }

    public static void main(String[] args) {
            int[][] board = new int[3][3];
            setBoard(board);
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }

        }



}

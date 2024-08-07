public class Sudokusolver {
    public static void main(String[] args) {
        int[][] board = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        if (solveSudoku(board)) {
            System.out.println("Sudoku puzzle solved successfully.");
            printBoard(board);
        } else {
            System.out.println("Given Sudoku puzzle is not solvable");
        }
    }
    public static boolean solveSudoku(int[][] board) {
        int[] emptyCell = findEmptyCell(board);

        // Base case: if no empty cell is found, puzzle is solved
        if (emptyCell == null) {
            return true;
        }

        int row = emptyCell[0];
        int col = emptyCell[1];

        // Try numbers 1 to 9 in the empty cell
        for (int num = 1; num <= 9; num++) {
            if (isValidMove(board, row, col, num)) {
                // Place the valid number and recursively solve the puzzle
                board[row][col] = num;
                if (solveSudoku(board)) {
                    return true;
                }
                // Undo the move if no solution is found
                board[row][col] = 0;
            }
        }

        // No valid number found for the current cell, backtrack
        return false;
    }

    public static int[] findEmptyCell(int[][] board) {
        int[] cell = new int[2];

        // Iterate through the board to find the first empty cell
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    cell[0] = row;
                    cell[1] = col;
                    return cell;
                }
            }
        }

        // Return null if no empty cell is found
        return null;
    }

    public static boolean isValidMove(int[][] board, int row, int col, int num) {
        // Check if num is already present in the row or column
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        // Check if num is already present in the 3x3 grid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }

        // Return true if the move is valid
        return true;
    }

    /**
     * Prints the Sudoku puzzle board.
     *
     * @param board The Sudoku puzzle board.
     */
    public static void printBoard(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
}

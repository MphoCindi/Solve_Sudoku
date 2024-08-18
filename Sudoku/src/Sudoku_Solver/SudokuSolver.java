package Sudoku.src.Sudoku_Solver;

public class SudokuSolver {
    public int[][] grid;
    public final int COLSIZE;
    public final int ROWSIZE;

    public SudokuSolver(int[][] puzzle){
        this.grid = puzzle;
        this.COLSIZE = grid.length;
        this.ROWSIZE = grid.length;
    }

    public boolean solveSudoku(int row, int col){
        if (row==ROWSIZE-1 && col==COLSIZE) return true;
        if (col==COLSIZE) {
            row++;
            col=0;
        }
        if (grid[row][col]>0) return solveSudoku(row, col+1);
        for (int cell=1; cell<ROWSIZE+1; cell++){
            if (cellMeetsAllConditions(row, col, cell)){
                grid[row][col] = cell;
                if (solveSudoku(row, col+1)) return true;
            }
            grid[row][col] = 0;
        }
        return false;
    }

    public int[][] puzzle(){
        return this.grid;
    }

    private boolean cellMeetsAllConditions(int row, int col, int cell){
        /*Here I will be checking conditions for row and columns and then for the 3x3 block */

        for (int i=0; i<ROWSIZE; i++){
            if (grid[row][i]==cell) return false;
        }

        for (int j=0; j<COLSIZE; j++){
            if (grid[j][col]==cell) return false;
        }

        for (int i=3*(row/3); i<3*(row/3)+3; i++){
            for (int j=3*(col/3); j<3*(col/3)+3; j++){
                if (grid[i][j]==cell) return false;
            }
        }
        return true;
    }
}

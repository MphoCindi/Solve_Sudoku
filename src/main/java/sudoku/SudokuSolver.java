package main.java.sudoku;

public class SudokuSolver {
    public int[][] grid = new int[9][9];
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
            } grid[row][col] = 0;
        }
        return false;
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

    public int[][] returnSolvedSudoku(){
        for (int i=0; i<ROWSIZE; i++){
            for (int j=0; j<COLSIZE; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

        return grid;
    }

    public static void main(String[] args){
        int puzzle[][] = {
            {3,0,6,5,0,8,4,0,0},
            {5,2,0,0,0,0,0,0,0},
            {0,8,7,0,0,0,0,3,1},
            {0,0,3,0,1,0,0,8,0},
            {9,0,0,8,6,3,0,0,5},
            {0,5,0,0,9,0,6,0,0},
            {1,3,0,0,0,0,2,5,0},
            {0,0,0,0,0,0,0,7,4},
            {0,0,5,2,0,6,3,0,0}
        };

        SudokuSolver sudokuSolver = new SudokuSolver(puzzle);
        if (sudokuSolver.solveSudoku(0, 0)) sudokuSolver.returnSolvedSudoku();
        else System.out.println("No solution");
    }

}
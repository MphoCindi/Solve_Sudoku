package main.java.sudoku;

import java.util.Arrays;
/*In this class I'll try to implement logic that will make the cell
 * of the GUI puzzle be highlighted if the value entered by the user 
 * does not meet the sudoku conditions.
 * for now it will just be general logic I'll still modify it to suit
 * GUI purposes
 *for now it returns true or false if the cell should be highlighted
 */

public class SudokuCell {

    int[][] grid = new int[9][9];
    int[] subList;
    int posCol;
    int posRow;
    private final int COLSIZE;

    public SudokuCell(int[][] puzzle, int[] list, int cell){
        this.grid = puzzle;
        this.COLSIZE = grid.length;
        this.subList = list;
        this.posCol = findColPosition(cell);
        this.posRow = findRowPosition();
    }

    private int findColPosition(int num){
        for (int i=0; i<subList.length; i++){
            if (subList[i]==num){
                return i;
            }
        } return -1;
    }

    private int findRowPosition(){
        for (int i=0; i<grid.length; i++){
            if (Arrays.equals(grid[i], subList)){
                return i;
            }
        } return -1;
    }
    
    private boolean rowConditionMet(int num){
        for (int cell: subList){
            if (cell==num) return false;
        } return true;
    }

    private boolean colConditionMet(int num){
        for (int j=0; j<COLSIZE; j++){
            if (grid[j][posCol]==num){
                return false;
            }
        } return true;
    }

    private boolean blockConditionMet(int num){
        for (int i=3*(posRow/3); i<3*(posRow)+3; i++){
            for (int j=3*(posCol/3); j<3*(posCol)+3; j++){
                if (grid[i][j]==num) return false;
            }
        } return true;
    }

    public boolean highlightCell(int num){
        return rowConditionMet(num) && colConditionMet(num) && blockConditionMet(num);
    }

}

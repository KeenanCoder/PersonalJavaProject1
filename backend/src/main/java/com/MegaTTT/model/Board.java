package model;

public class Board {
    
    private char[][] grid;

    public Board(){
        grid = new char[3][3];

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                grid[i][j] = ' ';
            }
        }
    }

    //method that returns the position or space that has been filled
    public char getPosition(int row, int col){
        return grid[row][col];
    }

    //a method that sets the position of whatever player's turn
    //decided on the playervalue parameter, by seeing if that space is empty or not
    public boolean setPosition(int row, int col, char playervalue){
        if(grid[row][col] == ' '){
            grid[row][col] = playervalue;
            return true;
        }
        return false;
    }

    //method for printing out the board of the game
    public void printBoard(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                System.out.print(getPosition(i, j));
                //if the j has passed through the whole array print '|'
                //and move onto the next column
                if(j < 2){
                    System.out.print('|');
                }
            }

            System.out.println();

            //if the i has pasased through the whole array it prints '----'
            //and moves onto the next row
            if(i < 2){
                System.out.println("-----");
            }
        }
    }
}
package com.PersonalJavaProject.megatictactoe.model;

public class Board {
    
    private char[][] grid;

    //constructor for the board class
    public Board(){
        grid = new char[3][3];

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                grid[i][j] = ' ';
            }
        }
    }

    public Board copy(){
    Board newBoard = new Board();
    for(int r = 0; r < 3; r++){
        for(int c = 0; c < 3; c++){
            newBoard.setPosition(r, c, this.grid[r][c]);
        }
    }
    return newBoard;
}

    //a method that validates the row and column position
    //and can be called in the setter and getter methods

    //method set to private because it can only be used within this class
    //because getPosition() already contains it
    private void validatePosition(int row, int col){
        //exception handling that validates the row range
        if(row >= grid.length || row < 0){
           throw new IllegalArgumentException("Row must be in range of 0 to 2");
        }
        //exception handling that validates the column range uses grid[row].length
        //because columns in each row can vary, but not necessarily in tictactoe
        //since the area is given (3x3) but it is best practice
        if(col >= grid[row].length || col < 0){
            throw new IllegalArgumentException("Column must be in range of 0 to 2");
        }
    }

    public char getPosition(int row, int col){
        validatePosition(row, col);
        return grid[row][col];
    }

    //a method that sets the position of whatever player's turn
    //decided on the playervalue parameter, by seeing if that space is empty or not
    public boolean setPosition(int row, int col, char playervalue){
        validatePosition(row, col);
        if(grid[row][col] == ' '){
            grid[row][col] = playervalue;
            return true;
        }
        return false;
    }

    // Counts lines (rows, columns, diagonals) that have exactly 2 of 'player'
// and 1 empty cell — meaning a win is one move away
public int countTwoInARowLines(char player){
    int count = 0;
    int[][] lines = {
        {0,0, 0,1, 0,2}, {1,0, 1,1, 1,2}, {2,0, 2,1, 2,2}, // rows
        {0,0, 1,0, 2,0}, {0,1, 1,1, 2,1}, {0,2, 1,2, 2,2}, // columns
        {0,0, 1,1, 2,2}, {0,2, 1,1, 2,0}                    // diagonals
    };

    for(int[] line : lines){
        char a = grid[line[0]][line[1]];
        char b = grid[line[2]][line[3]];
        char c = grid[line[4]][line[5]];

        int playerCount = 0;
        int emptyCount = 0;

        if(a == player) playerCount++;
        else if(a == ' ') emptyCount++;

        if(b == player) playerCount++;
        else if(b == ' ') emptyCount++;

        if(c == player) playerCount++;
        else if(c == ' ') emptyCount++;

        if(playerCount == 2 && emptyCount == 1){
            count++;
        }
    }

    return count;
}

    //method for checking if there is 3 in a row after every move
    public boolean checkWinCondition(char player){
        //run a for loop so that it checks all positions
        //to see if there is one where there is 3 in a row
        //determining the winner

        //for rows
        for(int row = 0; row < grid.length; row++){
            if(grid[row][0] == player &&
                grid[row][1] == player &&
                grid[row][2] == player){
                    return true;
            }
        }
        //for columns
        for(int col = 0; col < grid[0].length; col++){
            if(grid[0][col] == player &&
                grid[1][col] == player &&
                grid[2][col] == player){
                    return true;
            }
        }
        //diagonals don't need a for loop
        if(grid[0][0] == player &&
            grid[1][1] == player &&
            grid[2][2] == player){
                return true;
        }
        if(grid[0][2] == player &&
            grid[1][1] == player &&
            grid[2][0] == player){
                return true;
        }
        //returns false if there is no win condition
        return false;
    }

    //method for checking who exactly won the game
    //by calling the win condition method and comparing player X and O
    public char getWinner(){
        if(checkWinCondition('X')){
            return 'X';
        }
         if(checkWinCondition('O')){
            return 'O';
        }
        return ' ';
    }

    //method for if the board is full without a winner
    public boolean isFull(){
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[row].length; col++){
                if(grid[row][col] == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    //method for checking if it is a tie since a fullboard can also mean that a player won
    //it returns the methods isFull as true and getWinner as neither X or O player
    public boolean tieCondition(){
        return isFull() && getWinner() == ' ';
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
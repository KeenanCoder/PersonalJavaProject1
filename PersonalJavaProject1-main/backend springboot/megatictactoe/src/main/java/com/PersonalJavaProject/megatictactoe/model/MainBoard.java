package com.PersonalJavaProject.megatictactoe.model;

public class MainBoard {

    //private variables
    private Board[][] miniBoards;
    private Board mainBoard;
    private int activeRow;
    private int activeCol;

    public MainBoard(){
        //the mini boards are 3x3 and will use 2d loops to traverse
        miniBoards = new Board[3][3];
        for(int r = 0; r <3; r++){
            for(int c = 0; c < 3; c++){
                miniBoards[r][c]= new Board();
            }
        }
        mainBoard = new Board();
        activeRow = -1;
        activeCol = -1;
    }

    public int getActiveRow(){
        return activeRow;
    }

    public int getActiveCol(){
        return activeCol;
    }

    //checks to see if the slot holding the mini board has already been completed, tied or won
    public boolean miniBoardAvailability(int mainRow, int mainCol){
        Board mini = miniBoards[mainRow][mainCol];
        //returns the getWinner and isFull method from Board class using
        //the mini Object
        return mini.getWinner() == ' ' && !mini.isFull();
    }

    //gets when the player has won the whole game
    public char getMainBoardWin(){
        return mainBoard.getWinner();
    }

    //this method checks to validate the moves the player can do
    public boolean makeMove(int mainRow, int mainCol, int miniRow, int miniCol, char player){

        if(activeRow != -1 && (mainRow != activeRow || mainCol != activeCol)){
            System.out.println("You must play in the highlighted mini-board!");
            return false;
        }
        
        //checks to see if the place where player chose is available or already taken
        if(!miniBoardAvailability(mainRow, mainCol)){
            System.out.println("That mini-board is already finished. Please choose another one!");
            return false;
        }

        //checks the cell to see if it is already occupied
        Board mini = miniBoards[mainRow][mainCol];
        if(!mini.setPosition(miniRow, miniCol, player)){
            System.out.println("That cell is already occupied");
            return false;
        }

        //checks to see if the player won the square/mini board of the main board
        //and gives the player this square
        if(mini.getWinner() != ' '){
            mainBoard.setPosition(mainRow, mainCol, mini.getWinner());
            System.out.println("Player " + player + " claimed this square (" + mainRow + "," + mainCol + ")!");
        }

        //the miniBoard move sends the player to the main position next
        if(miniBoardAvailability(mainRow, mainCol)){
            activeRow = mainRow;
            activeCol = mainCol;
        }
        else{
            activeRow = -1;
            activeCol = -1;
        }
        return true;
    }

    public char[][] getMiniBoardWinners(){
        char[][] status = new char[3][3];
        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 3; c++){
                Board mini = miniBoards[r][c];
                if(mini.getWinner() != ' '){
                    status[r][c] = mini.getWinner(); // 'X' or 'O'
                }
                else if(mini.tieCondition()){
                    status[r][c] = 'T'; // tied
                }
                else{
                    status[r][c] = ' '; // still in progress
                }
            }
        }
        return status;
    }

    //checks to see if the player loses or ties
    public boolean isGameOver(){
        //if the getWinner method returns anything but ' '
        //then it will return true because someone won
        if(mainBoard.getWinner() != ' '){
            return true;
        }
        
        //loops to see if all the mini boards are completed
        //by calling the miniBoardAvailability method and if  there is no
        //free spots with no winner then it will return false making it
        //a Game Over
        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 3; c++){
                if(miniBoardAvailability(r, c)){
                    return false;
                }
            }
        }
        return true;
    }

    //method for printing the main board (with all the mini boards as well)
    public void printMainBoard(){
        for(int mainRow = 0; mainRow < 3; mainRow++){
            //loops for the mini boards which has 3 rows
            for(int miniRow = 0; miniRow < 3; miniRow++){
                for(int mainCol = 0; mainCol < 3; mainCol++){
                    Board mini = miniBoards[mainRow][mainCol];
                    char winner = mini.getWinner();

                    for(int miniCol = 0; miniCol < 3; miniCol++){
                        //if the mini board is won by a player it will visually
                        //show the winner of that mini board
                        char display = (winner != ' ') ? winner : mini.getPosition(miniRow, miniCol);
                        System.out.print(display);
                        //displays the mini boards column (which is |)
                        if(miniCol < 2){
                            System.out.print(" | ");
                        }
                    }
                    //displays the main board with || as columns
                    if(mainCol < 2){
                        System.out.print(" || ");
                    }

                }
                System.out.println();

            }
            //displays the main board with || as rows
            if(mainRow < 2){
                System.out.println("--------- || --------- || ---------");
            }
        }
        //Shows the active board
        if(activeRow != -1){
            System.out.println("\n>> Next move must be in the mini-board (" + activeRow + "," + activeCol + ")");
        }
        else{
            System.out.println("\n>> Free choice - play in any open mini-board");
        }
    }
}
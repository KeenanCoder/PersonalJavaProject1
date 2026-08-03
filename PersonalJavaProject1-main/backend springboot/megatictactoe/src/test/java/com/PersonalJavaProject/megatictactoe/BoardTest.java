package com.PersonalJavaProject.megatictactoe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.PersonalJavaProject.megatictactoe.model.Board;

class BoardTest {

    //PLACEMENT OF PIECE***************************************
    @Test
    void shouldDetectPlacePiece() {
        //Arrange (set up)
        Board board = new Board();

        //Act (do seomething)
        boolean placed = board.setPosition(1, 1, 'X');

        //Assert (check the result)
        assertTrue(placed);
    }

    @Test
    void shouldRejectOccupiedPosition(){

        Board board = new Board();

        board.setPosition(1, 1, 'X');

        boolean placed = board.setPosition(1, 1, 'O');

        assertFalse(placed);
    }

    @Test 
    void shouldReturnStoredValue(){

        Board board = new Board();

        board.setPosition(0, 0, 'X');

        assertEquals('X', board.getPosition(0, 0));
    }

    //ROW VALIDATION********************************
    @Test
    void shouldValidateRow(){

        Board board = new Board();

        assertThrows(IllegalArgumentException.class, () -> board.setPosition(-1, 0, 'X'));
    }
    
    @Test
    void shouldRejectNegativeRow(){
        Board board = new Board();

        board.setPosition(-1, 0, 'X');
    }

    @Test
    void shouldRejectLargeRow(){
        Board board = new Board();

        board.setPosition(3, 0, 'X');
    }

    //COLUMN VALIDATION*****************************
    @Test
    void shouldValidateColumn(){

        Board board = new Board();

        assertThrows(
            IllegalArgumentException.class, () -> board.getPosition(0, 5)
        );
    }

    @Test
    void shouldRejectNegativeColumn(){
        Board board = new Board();

        board.setPosition(0, -1, 'X');
    }

    @Testvoid shouldRejectLargeColumn(){
        Board board = new Board();

        board.setPosition(0, 3, 'X');
    }

    //FULL OR EMPTY BOARD***************************************
    @Test
    void shouldStartOnEmptyBoard(){
        Board board = new Board();

        assertFalse(board.isFull());
    }

    @Test
    void shouldDetectFullBoard(){
        Board board = new Board();

        board.setPosition(0,0,'X');
        board.setPosition(0,1,'O');
        board.setPosition(0,2,'X');

        board.setPosition(1,0,'O');
        board.setPosition(1,1,'X');
        board.setPosition(1,2,'O');

        board.setPosition(2,0,'X');
        board.setPosition(2,1,'O');
        board.setPosition(2,2,'X');


        assertTrue(board.isFull());
    }

    //WIN DETECTION*******************************************
    @Test
    void shouldReturnWinnerX(){

        Board board = new Board();

        board.setPosition(0, 0, 'X');
        board.setPosition(0, 1, 'X');
        board.setPosition(0, 2, 'X');

        assertEquals('X', board.getWinner());
    }

    @Test
    void shouldReturnWinnerO(){

        Board board = new Board();

        board.setPosition(0, 0, 'O');
        board.setPosition(0, 1, 'O');
        board.setPosition(0, 2, 'O');

        assertEquals('O', board.getWinner());
    }

    @Test
    void shouldReturnNoWinner(){

        Board board = new Board();

        board.setPosition(0, 0, ' ');
        board.setPosition(0, 1, ' ');
        board.setPosition(0, 2, ' ');

        assertEquals(' ', board.getWinner());
    }

    @Test
    void shouldDetectHorizontalWin() {
        //Arrange (set up)
        Board board = new Board();

        board.setPosition(0, 0, 'X');
        board.setPosition(0, 1, 'X');
        board.setPosition(0, 2, 'X');

        //Act (do seomething)
        boolean winner = board.checkWinCondition('X');

        //Assert (check the result)
        assertTrue(winner);
    }

    @Test
    void shouldDetectVerticalWin() {
        Board board = new Board();

        board.setPosition(0, 0, 'X');
        board.setPosition(1, 0, 'X');
        board.setPosition(2, 0, 'X');

        boolean winner = board.checkWinCondition('X');

        //Assert (check the result of the vertical win)
        assertTrue(winner);
    }

    @Test
    void shouldDetectDiagonalWinOne() {
        Board board = new Board();

        board.setPosition(0, 0, 'X');
        board.setPosition(1, 1, 'X');
        board.setPosition(2, 2, 'X');

        boolean winner = board.checkWinCondition('X');

        //Assert (check the result of the diagonal win)
        assertTrue(winner);
    }

    @Test
    void shouldDetectDiagonalWinTwo() {
        Board board = new Board();

        board.setPosition(0, 2, 'X');
        board.setPosition(1, 1, 'X');
        board.setPosition(2, 0, 'X');

        boolean winner = board.checkWinCondition('X');

        //Assert (check the result of the other diagonal win)
        assertTrue(winner);
    }

//TIE**************************************************
    @Test
    void shouldDetectTie() {
        Board board = new Board();

        //FIXME: Add 'O' to test tie condition
        board.setPosition(0, 0, 'X');
        board.setPosition(0, 1, 'O');
        board.setPosition(0, 2, 'X');

        board.setPosition(1, 0, 'X');
        board.setPosition(1, 1, 'O');
        board.setPosition(1, 2, 'O');

        board.setPosition(2, 0, 'O');
        board.setPosition(2, 1, 'X');
        board.setPosition(2, 2, 'X');

        //Assert (check the result of a tie)
        assertTrue(board.tieCondition());
    }

    @Test
    void shouldCopyBoard(){
        Board board = new Board();

        board.setPosition(1, 1, 'X');

        Board copy = board.copy();

        assertEquals('X', copy.getPosition(1, 1));
    }
}
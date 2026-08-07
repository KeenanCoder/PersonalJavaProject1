package com.PersonalJavaProject.megatictactoe.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.PersonalJavaProject.megatictactoe.model.Board;

public class BoardTest {

    //PLACEMENT OF PIECE***************************************
    @Test
    void shouldDetectPlacePiece() {
        Board board = new Board();

        boolean placed = board.setPosition(1, 1, 'X');

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
    void shouldRejectNegativeRow(){
        Board board = new Board();

        assertThrows(IllegalArgumentException.class, () -> board.setPosition(-1, 0, 'X'));
    }

    @Test
    void shouldRejectLargeRow(){
        Board board = new Board();

        assertThrows(IllegalArgumentException.class, () -> board.setPosition(3, 0, 'X'));
    }

    //COLUMN VALIDATION*****************************
    @Test
    void shouldRejectNegativeColumn(){
        Board board = new Board();

        assertThrows(IllegalArgumentException.class, () -> board.setPosition(0, -1, 'X'));
    }

    @Test
    void shouldRejectLargeColumn(){
        Board board = new Board();

        assertThrows(IllegalArgumentException.class, () -> board.setPosition(0, 3, 'X'));
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

        assertEquals(' ', board.getWinner());
    }

    @Test
    void shouldDetectHorizontalWinX() {
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
    void shouldDetectHorizontalWinO() {
        //Arrange (set up)
        Board board = new Board();

        board.setPosition(0, 0, 'O');
        board.setPosition(0, 1, 'O');
        board.setPosition(0, 2, 'O');

        //Act (do seomething)
        boolean winner = board.checkWinCondition('O');

        //Assert (check the result)
        assertTrue(winner);
    }

    @Test
    void shouldDetectVerticalWinX() {
        Board board = new Board();

        board.setPosition(0, 0, 'X');
        board.setPosition(1, 0, 'X');
        board.setPosition(2, 0, 'X');

        //Assert (check the result of the vertical win)
        assertTrue(board.checkWinCondition('X'));
    }

    @Test
    void shouldDetectVerticalWinO(){
        Board board = new Board();

        board.setPosition(0, 0, 'O');
        board.setPosition(1, 0, 'O');
        board.setPosition(2, 0, 'O');

        //Assert (check the result of the vertical win)
        assertTrue(board.checkWinCondition('O'));
    }

    @Test
    void shouldDetectDiagonalWinOneX() {
        Board board = new Board();

        board.setPosition(0, 0, 'X');
        board.setPosition(1, 1, 'X');
        board.setPosition(2, 2, 'X');

        boolean winner = board.checkWinCondition('X');

        //Assert (check the result of the diagonal win)
        assertTrue(winner);
    }

    @Test
    void shouldDetectDiagonalWinTwoX() {
        Board board = new Board();

        board.setPosition(0, 2, 'X');
        board.setPosition(1, 1, 'X');
        board.setPosition(2, 0, 'X');

        boolean winner = board.checkWinCondition('X');

        //Assert (check the result of the other diagonal win)
        assertTrue(winner);
    }

    @Test
    void shouldDetectDiagonalWinOneO() {
        Board board = new Board();

        board.setPosition(0, 0, 'O');
        board.setPosition(1, 1, 'O');
        board.setPosition(2, 2, 'O');

        boolean winner = board.checkWinCondition('O');

        //Assert (check the result of the diagonal win)
        assertTrue(winner);
    }

    @Test
    void shouldDetectDiagonalWinTwoO() {
        Board board = new Board();

        board.setPosition(0, 2, 'O');
        board.setPosition(1, 1, 'O');
        board.setPosition(2, 0, 'O');

        boolean winner = board.checkWinCondition('O');

        //Assert (check the result of the other diagonal win)
        assertTrue(winner);
    }
//MAX VALID ROW NAD COLUMN*****************************
    @Test
    void shouldAcceptMaxValidRow(){
        Board board = new Board();

        boolean placed = board.setPosition(2, 0, 'X');

        assertTrue(placed);
    }

    @Test
    void shouldAcceptMaxValidColumn(){
        Board board = new Board();

        boolean placed = board.setPosition(0, 2, 'X');

        assertTrue(placed);
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

        assertNotSame(board, copy);
        assertEquals('X', copy.getPosition(1, 1));
    }

    //AI TESTER
    @Test
    void shouldCountTwoInARowLine(){
        Board board = new Board();

        board.setPosition(0, 0, 'X');
        board.setPosition(0, 1, 'X');

        assertEquals(1, board.countTwoInARowLines('X'));
    }

    @Test
    void shouldCountMultipleTwoInARowLines(){
        Board board = new Board();

        board.setPosition(0, 0, 'X'); // the shared corner cell
        board.setPosition(0, 1, 'X'); // makes the top row 2/3 full (missing 0,2)
        board.setPosition(1, 0, 'X'); // makes the left column 2/3 full (missing 2,0)

        assertEquals(2, board.countTwoInARowLines('X'));
    }

    @Test
    void shouldReturnZeroTwoInARowLines(){
        Board board = new Board();

        assertEquals(0, board.countTwoInARowLines('X'));
    }
}
package com.PersonalJavaProject.megatictactoe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardTest {

    @Test
    void shouldDetectHorizontalWin() {
        //Arrange (set up)
        Board board = new Board();

        board.placeMove(0, 0, 'X');
        board.placeMove(0, 1, 'X');
        board.placeMove(0, 2, 'X');

        //Act (do seomething)
        boolean winner = board.checkWinner('X');

        //Assert (check the result)
        assertTrue(winner);
    }

    @Test
    void shouldDetectVerticalWin() {
        Board board = new Board();

        board.placeMove(0, 0, 'X');
        board.placeMove(1, 0, 'X');
        board.placeMove(2, 0, 'X');

        board.placeMove(0, 1, 'X');
        board.placeMove(1, 1, 'X');
        board.placeMove(2, 1, 'X');

        board.placeMove(0, 2, 'X');
        board.placeMove(1, 2, 'X');
        board.placeMove(2, 2, 'X');

        boolean winner = board.checkWinner('X');

        //Assert (check the result of the vertical win)
        assertTrue(winner);
    }

    @Test
    void shouldDetectDiagonalWin() {
        Board board = new Board();

        board.placeMove(0, 0, 'X');
        board.placeMove(1, 1, 'X');
        board.placeMove(2, 2, 'X');

        board.placeMove(2, 0, 'X');
        board.placeMove(1, 1, 'X');
        board.placeMove(0, 2, 'X');

        boolean winner = board.checkWinner('X');

        //Assert (check the result of the vertical win)
        assertTrue(winner);
    }

    @Test
    void shouldDetectTie() {
        Board board = new Board();

        //FIXME: Add 'O' to test tie condition
        board.placeMove(0, 0, 'X');
        board.placeMove(1, 1, 'X');
        board.placeMove(2, 2, 'X');

        boolean winner = board.checkWinner('X');

        //Assert (check the result of the vertical win)
        assertTrue(winner);
    }
}
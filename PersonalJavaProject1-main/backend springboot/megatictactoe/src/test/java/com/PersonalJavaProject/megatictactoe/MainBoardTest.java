package com.PersonalJavaProject.megatictactoe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.PersonalJavaProject.megatictactoe.model.MainBoard;

public class MainBoardTest {

    @Test
    void shouldStartWithFreeChoice(){
        MainBoard mainBoard = new MainBoard();

        // a fresh game should have no restriction yet
        assertEquals(-1, mainBoard.getActiveRow());
        assertEquals(-1, mainBoard.getActiveCol());
    }

    @Test
    void shouldUpdateActiveBoardAfterMove(){
        MainBoard mainBoard = new MainBoard();

        // X plays the center cell (1,1) of the top-left mini-board (0,0)
        mainBoard.makeMove(0, 0, 1, 1, 'X');

        // since that mini-board isn't finished, it should now be the active board
        assertEquals(0, mainBoard.getActiveRow());
        assertEquals(0, mainBoard.getActiveCol());
    }

    @Test
    void shouldDetectMegaBoardWinner(){
        MainBoard mainBoard = new MainBoard();

        // no one has won yet on a fresh board
        assertEquals(' ', mainBoard.getMainBoardWin());
    }

    @Test
    void shouldPreventMoveOnWonBoard(){
        MainBoard mainBoard = new MainBoard();

        // X wins the top-left mini-board (0,0) outright
        mainBoard.makeMove(0, 0, 0, 0, 'X');
        mainBoard.makeMove(1, 1, 0, 0, 'O'); // O forced elsewhere, doesn't matter here
        mainBoard.makeMove(0, 0, 0, 1, 'X');
        mainBoard.makeMove(0, 1, 0, 0, 'O');
        mainBoard.makeMove(0, 0, 0, 2, 'X'); // completes top row -> X wins mini-board (0,0)

        // that mini-board should no longer be available
        assertFalse(mainBoard.miniBoardAvailability(0, 0));
    }
}
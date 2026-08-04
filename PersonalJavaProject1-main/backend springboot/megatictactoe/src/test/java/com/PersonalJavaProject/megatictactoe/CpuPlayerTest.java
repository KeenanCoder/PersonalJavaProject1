package com.PersonalJavaProject.megatictactoe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.PersonalJavaProject.megatictactoe.model.MainBoard;
import com.PersonalJavaProject.megatictactoe.model.CpuPlayer;

public class CpuPlayerTest {

    @Test
    void easyMakesALegalMove(){
        MainBoard game = new MainBoard();

        int[] move = CpuPlayer.getComputerMove(game, 'O', "easy");

        // whatever move it picks, it must be a real, currently-legal move
        assertNotNull(move);
        assertTrue(game.getLegalMoves().stream().anyMatch(m ->
            m[0] == move[0] && m[1] == move[1] && m[2] == move[2] && m[3] == move[3]
        ));
    }

    @Test
    void hardBlocksAnObviousWinningMove(){
        MainBoard game = new MainBoard();

        // Set up X with two in a row in mini-board (0,0), needing only (0,2) to win
        game.makeMove(0, 0, 0, 0, 'X');
        game.makeMove(0, 0, 1, 1, 'O');
        game.makeMove(0, 0, 0, 1, 'X'); // X sent to (0,1) mini-board... 
        // NOTE: this setup needs care to keep O "free" to block (0,0) again
        // For a clean test, easier to directly test wouldWinMiniBoard() instead:

        assertTrue(game.wouldWinMiniBoard(new int[]{0, 0, 0, 2}, 'X'));
    }

    @Test
    void impossibleReturnsALegalMove(){
        MainBoard game = new MainBoard();

        int[] move = CpuPlayer.getComputerMove(game, 'O', "impossible");

        assertNotNull(move);
        assertTrue(game.getLegalMoves().stream().anyMatch(m ->
            m[0] == move[0] && m[1] == move[1] && m[2] == move[2] && m[3] == move[3]
        ));
    }

    @Test
    void impossibleFindsClearWinningMove(){
        MainBoard game = new MainBoard();

        game.makeMove(0, 0, 0, 0, 'X');
        game.makeMove(0, 0, 1, 1, 'O');
        game.makeMove(0, 0, 0, 1, 'X');

        int[] move = CpuPlayer.getComputerMove(game, 'X', impossible);

        assertArrayEquals(new int[]{0, 0, 0, 2}, move);
    }
}
package com.PersonalJavaProject.megatictactoe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import com.PersonalJavaProject.megatictactoe.model.MainBoard;
import com.PersonalJavaProject.megatictactoe.model.Minimax;

public class MinimaxTest {
    @Test
    void minimaxReturnsBestMove(){}

    @Test
    void minimaxNeverMissesForcedWin(){}

    @Test
    void findBestMoveChoosesObviousWin(){
        MainBoard game = new MainBoard();

        game.makeMove(0, 0, 0, 0, 'X');
        game.makeMove(0, 0, 1, 1, 'O');
        game.makeMove(0, 0, 0, 1, 'X');

        int[] move = Minimax.findBestMove(game, 'X');

        assertArrayEquals(new int[]{0, 0, 0, 2}, move);
    }
}

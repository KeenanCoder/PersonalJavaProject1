package com.PersonalJavaProject.megatictactoe.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.PersonalJavaProject.megatictactoe.model.MainBoard;
import com.PersonalJavaProject.megatictactoe.model.Minimax;

public class MinimaxTest {
    @Test
    void minimaxReturnsBestMove(){
        MainBoard game = new MainBoard();

        game.makeMove(0, 0, 0, 0, 'X');

        int[] move = Minimax.findBestMove(game, 'O');

        assertNotNull(move);
    }

    @Test
    void minimaxBlockObviousWin(){
        MainBoard game = new MainBoard();

        game.makeMove(0, 0, 0, 0, 'X');
        game.makeMove(0, 0, 1, 1, 'O');
        game.makeMove(0, 0, 0, 1, 'X');

        int[] move = Minimax.findBestMove(game, 'O');

        assertArrayEquals(new int[]{0, 0, 0, 2}, move);
    }

    @Test
    void findBestMoveChoosesObviousWin(){
        MainBoard game = new MainBoard();

        game.makeMove(0, 0, 0, 0, 'X');
        game.makeMove(0, 0, 1, 1, 'O');
        game.makeMove(0, 0, 0, 1, 'X');

        int[] move = Minimax.findBestMove(game, 'X');

        assertArrayEquals(new int[]{0, 0, 0, 2}, move);
    }

    @Test
    void minimaxNeverMissesForcedWin(){
        MainBoard game = new MainBoard();

        game.makeMove(0, 0, 0, 0, 'X');
        game.makeMove(0, 0, 1, 1, 'O');
        game.makeMove(0, 0, 0, 1, 'X');

        int[] move = Minimax.findBestMove(game, 'O');

        System.out.println("Minimax chose: " + move[0] + "," + move[1] + " -> " + move[2] + "," + move[3]);

        MainBoard copy = game.copy();
        copy.makeMove(move[0], move[1], move[2], move[3], 'O', false);

        boolean xCanStillWin = copy.getLegalMoves().stream()
        .anyMatch(m -> copy.wouldWinMiniBoard(m, 'X'));

        System.out.println("Can X still win mini-board (0,0) after O's move? " + xCanStillWin);

        assertFalse(xCanStillWin, "O's move should have prevented X from winning the mini-board");
    }
}

package com.PersonalJavaProject.megatictactoe.model;

import java.util.*;

public class Minimax {

    // How many moves ahead to search. Higher = smarter but slower.
    private static final int MAX_DEPTH = 3;

    public static int[] findBestMove(MainBoard game, char player){
        List<int[]> legalMoves = game.getLegalMoves();
        int[] bestMove = null;
        int bestScore = Integer.MIN_VALUE;

        for(int[] move : legalMoves){
            MainBoard copy = game.copy();
            copy.makeMove(move[0], move[1], move[2], move[3], player, false);

            int score = minimax(copy, MAX_DEPTH - 1, Integer.MIN_VALUE, Integer.MAX_VALUE, false, player);

            if(score > bestScore){
                bestScore = score;
                bestMove = move;
            }
        }

        return bestMove;
    }

    private static int minimax(MainBoard game, int depth, int alpha, int beta, boolean maximizing, char player){
        char opponent = (player == 'X') ? 'O' : 'X';

        if(game.isGameOver()){
            char winner = game.getMainBoardWin();
            if(winner == player) return 10000 + depth;   // prefer faster wins
            if(winner == opponent) return -10000 - depth; // prefer slower losses
            return 0; // tie
        }

        if(depth == 0){
            return evaluate(game, player);
        }

        List<int[]> legalMoves = game.getLegalMoves();
        char currentPlayer = maximizing ? player : opponent;

        if(maximizing){
            int maxEval = Integer.MIN_VALUE;
            for(int[] move : legalMoves){
                MainBoard copy = game.copy();
                copy.makeMove(move[0], move[1], move[2], move[3], currentPlayer, false);
                int eval = minimax(copy, depth - 1, alpha, beta, false, player);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if(beta <= alpha) break; // pruning
            }
            return maxEval;
        }
        else{
            int minEval = Integer.MAX_VALUE;
            for(int[] move : legalMoves){
                MainBoard copy = game.copy();
                copy.makeMove(move[0], move[1], move[2], move[3], currentPlayer, false);
                int eval = minimax(copy, depth - 1, alpha, beta, true, player);
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if(beta <= alpha) break; // pruning
            }
            return minEval;
        }
    }

    // Scores a position when we can't search any deeper.
    // Positive = good for 'player', negative = good for opponent.
    private static int evaluate(MainBoard game, char player){
        char opponent = (player == 'X') ? 'O' : 'X';
        int score = 0;

        char[][] miniWinners = game.getMiniBoardWinners();

        // Center mini-board is most valuable, corners next, edges least
        int[][] weights = {
            {3, 2, 3},
            {2, 4, 2},
            {3, 2, 3}
        };

        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 3; c++){
                if(miniWinners[r][c] == player) score += 100 * weights[r][c];
                else if(miniWinners[r][c] == opponent) score -= 100 * weights[r][c];
            }
        }

        // Reward setups (2-in-a-row with open third cell) in still-open mini-boards
        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 3; c++){
                if(miniWinners[r][c] == ' '){
                    score += game.countTwoInARowSetupsForBoard(r, c, player) * 5;
                    score -= game.countTwoInARowSetupsForBoard(r, c, opponent) * 5;
                }
            }
        }

        return score;
    }
}
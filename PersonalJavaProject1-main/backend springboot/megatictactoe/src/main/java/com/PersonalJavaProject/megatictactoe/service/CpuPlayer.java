package com.PersonalJavaProject.megatictactoe.model;

import java.util.*;

public class CpuPlayer {

    private static final Random random = new Random();

    // Picks a move based on difficulty — main entry point
    public static int[] getComputerMove(MainBoard game, char player, String difficulty){
        if(difficulty.equalsIgnoreCase("impossible")){
            return Minimax.findBestMove(game, player);
        }

        double smartChance = getSmartChance(difficulty);
        double roll = random.nextDouble();

        if(roll < smartChance){
            int[] smart = getSmartMove(game, player, difficulty);
            if(smart != null) return smart;
        }

        return getRandomMove(game);
    }

    private static double getSmartChance(String difficulty){
        switch(difficulty.toLowerCase()){
            case "easy": return 0.20;
            case "normal": return 0.50;
            case "hard": return 0.80;
            case "impossible": return 0.95;
            default: return 0.50;
        }
    }

    private static double getCenterChance(String difficulty){
        switch(difficulty.toLowerCase()){
            case "easy": return 0.25;
            case "normal": return 0.50;
            case "hard": return 0.75;
            case "impossible": return 1.0;
            default: return 0.50;
        }
    }

    // Heuristic, checked in priority order:
    // 1. Win a mini-board right now
    // 2. Block opponent from winning a mini-board
    // 3. Create a 2-in-a-row setup (with an open third cell) for next turn
    // 4. No smart move found
    private static int[] getSmartMove(MainBoard game, char player, String difficulty){
        char opponent = (player == 'X') ? 'O' : 'X';
        List<int[]> legalMoves = game.getLegalMoves();

        // 1. Can I win a mini-board right now?
        for(int[] move : legalMoves){
            if(game.wouldWinMiniBoard(move, player)) return move;
        }

        // 2. Can I block the opponent from winning a mini-board?
        for(int[] move : legalMoves){
            if(game.wouldWinMiniBoard(move, opponent)) return move;
        }

        // 3. Can I set up a 2-in-a-row with an open third cell?
        int[] bestSetupMove = null;
        int bestSetupCount = 0;

        for(int[] move : legalMoves){
            int setupCount = game.countTwoInARowSetups(move, player);
            if(setupCount > bestSetupCount){
                bestSetupCount = setupCount;
                bestSetupMove = move;
            }
        }

        if(bestSetupMove != null) return bestSetupMove;

        // 4. Chance to prefer the center cell of whichever mini-board(s) are legal
        double centerChance = getCenterChance(difficulty); // 50% chance to favor center when available
        if(random.nextDouble() < centerChance){
            int[] centerMove = findCenterMove(legalMoves);
            if(centerMove != null) return centerMove;
        }

        // 5. No smart move found
        return null;
    }

    // Looks for a legal move that targets the center cell (1,1) of its mini-board
    private static int[] findCenterMove(List<int[]> legalMoves){
        for(int[] move : legalMoves){
            int miniRow = move[2];
            int miniCol = move[3];
            if(miniRow == 1 && miniCol == 1){
                return move;
            }
        }
        return null;
    }

    private static int[] getRandomMove(MainBoard game){
        List<int[]> legalMoves = game.getLegalMoves();
        if(legalMoves.isEmpty()) return null;
        return legalMoves.get(random.nextInt(legalMoves.size()));
    }
}
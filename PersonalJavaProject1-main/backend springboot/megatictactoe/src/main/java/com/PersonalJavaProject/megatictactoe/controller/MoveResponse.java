package com.PersonalJavaProject.megatictactoe.controller;

public class MoveResponse{
    public boolean success;
    public char winner;
    public boolean gameOver;
    public int activeRow;
    public int activeCol;
    public char[][] miniBoardWinners;
    public int[] cpuMove;

    public MoveResponse(boolean success, char winner, boolean gameOver,
        int activeRow, int activeCol, char[][] miniBoardWinners, int[] cpuMove){
        this.success = success;
        this.winner = winner;
        this.gameOver = gameOver;
        this.activeRow = activeRow;
        this.activeCol = activeCol;
        this.miniBoardWinners = miniBoardWinners;
        this.cpuMove = cpuMove;
    }
}
package com.MegaTTT.gameFunctions

import java.util.*;

public class gameTTT{
	protected String username;
	//sets the game over to false until a player wins or ends in a tie
	protected boolean gameOver = false;
	
	//add variables here
	public gameTTT() {
		
	}
	
	//method about the main game
	public void playGame() {
		if(mode == 1) {
			game = new onePlayerGame(username, difficulty);
		} else {
			game = new twoPlayerGame(username, username2);
		}
		//add catch exception or when the player choses to leave game
		//System.exit(3);
		while(!gameOver) {
			//display methods here like the board, turns, win condition, space condition etc.
			
		}
	}
	
	@Override
	public String toString() {
		return "hello";
	}
}
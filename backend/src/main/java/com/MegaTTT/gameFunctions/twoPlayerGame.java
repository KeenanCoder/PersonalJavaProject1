package com.MegaTTT.gameFunctions

import java.util.*;
//FIXME: future implement extends gameTTT class or something
public class twoPlayerGame extends gameTTT{
	
	private String username1;
	private String username2;
	
	public twoPlayerGame(){
	}
	
	public twoPlayerGame(String username1, String username2) {
		super();
		this.username1 = username1;
		this.username2 = username2;
	}
	
	public String getUserName1() {
		return username1;
	}
	
	public String getUserName2() {
		return username2;
	}
	
	public void setUserName1(String username1) {
		this.username1 = username1;
	}
	
	public void setUserName2(String username2) {
		this.username2 = username2;
	}
	
	@Override
	public String toString() {
		return super.toString() + username1 + "vs" + username2;
	}
}
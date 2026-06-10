package com.MegaTTT.gameFunctions

import java.util.*;
//FIXME: future implement extends gameTTT class or something
public class onePlayerGame extends gameTTT{
	
	private String username1;
	
	public onePlayerGame(){
	}
	
	public onePlayerGame(String username1) {
		super();
		this.username1 = username1;
	}
	
	public String getUserName() {
		return username1;
	}
	
	public void setUserName(String username1) {
		this.username1 = username1;
	}
	
	@Override
	public String toString() {
		return super.toString() + "Person" + username1 + "is playing";
	}
}
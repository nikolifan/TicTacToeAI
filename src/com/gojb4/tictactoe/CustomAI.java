package com.gojb4.tictactoe;

public abstract class CustomAI {

	private int player;

	public CustomAI(int player) {
		this.player = player;
	}

	public int getPlayer() {
		return player;
	}

	public abstract void move(Board board);

}

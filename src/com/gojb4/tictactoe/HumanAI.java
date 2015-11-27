package com.gojb4.tictactoe;

import java.util.Scanner;

public class HumanAI extends CustomAI {

	public HumanAI(int player) {
		super(player);
	}

	@SuppressWarnings("resource")
	private int getNextMove() {
		System.out.print("\nEnter your move (1-9): ");
		Scanner scanner = new Scanner(System.in);
		int pos = scanner.nextInt();
		//scanner.close();
		return pos - 1;
	}
	
	@Override
	public void move(Board board) {
		int pos = getNextMove();
		board.setMove(pos, getPlayer());
	}

}

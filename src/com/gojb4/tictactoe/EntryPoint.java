package com.gojb4.tictactoe;

public class EntryPoint {

	public static void drawBoard(Board board) {
		int i = 0;
		System.out.println("");
		for (char c : board.toCharArray()) {
			i++;
			System.out.print(" " + c + " ");
			if (i % 3 == 0) {
				System.out.println("");
				if (i < 9)
					System.out.println("---+---+---");
			} else
				System.out.print("|");
		}
	}

	public static void main(String[] args) {

		//CustomAI x = new HumanAI(Board.PLAYER_X);
		CustomAI x = new RomanAI(Board.PLAYER_X);
		CustomAI o = new KossAI(Board.PLAYER_O);

		Board board = new Board();
		board.newGame();
		int nextPlayer = Board.PLAYER_X;
		while (board.isGameOver() == 0) {
			CustomAI player = (nextPlayer == Board.PLAYER_X) ? x : o;
			player.move(board);
			nextPlayer = -nextPlayer;
			drawBoard(board);
		}

		int end = board.isGameOver();
		if (end == Board.PLAYER_X_WON)
			System.out.println("\n Player X WON!");
		if (end == Board.PLAYER_O_WON)
			System.out.println("\n Player O WON!");
		if (end == Board.DRAWN_GAME)
			System.out.println("\n Drawn game!");

	}

}

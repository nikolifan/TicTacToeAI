package com.gojb4.tictactoe;

public class EntryPoint {
	private static Board board = new Board();
	private static CustomAI x = new HumanAI(Board.PLAYER_X);
	private static CustomAI o = new RomanAI(Board.PLAYER_O);

	public static void drawBoard(Board board) {
		int i = 0;
		System.out.println("");
		for (char c : board.toCharArray()) {
			i++;
			System.out.print(" " + c + " ");
			if (i % 3 == 0) {
				System.out.println("");
				if (i < 9) System.out.println("---+---+---");
			} else System.out.print("|");
		}
	}	

	public static void main(String[] args) {

		board.newGame();
		int nextPlayer = Board.PLAYER_X;
		while (board.isGameOver() == 0) {
			CustomAI p = (nextPlayer == Board.PLAYER_X) ? x : o;
			p.move(board);
			nextPlayer = -nextPlayer;
			drawBoard(board);
		}

		int end = board.isGameOver();
		if (end == Board.PLAYER_X_WON)
			System.out.println("\n Player X WON!");
		if (end == Board.PLAYER_O_WON)
			System.out.println("\n Player O WON!");
		if (end == Board.DRAWN_GAME) System.out.println("\n Drawn game!");
		
	}

}

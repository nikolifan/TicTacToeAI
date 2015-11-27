package com.gojb4.tictactoe;

public class KossAI extends CustomAI {

	private class Node {
		public int score;
		public int index;

		public Node() {
		}

		public Node(int score, int index) {
			this.score = score;
			this.index = index;
		}
	}
	

	public KossAI(int player) {
		super(player);
	}

	@Override
	public void move(Board board) {
		int nextMove = -1;
		if (board.isEmpty()) {
			nextMove = (int) (Math.random() * (board.getBoard().length));
		} else {
			Node node = minimax(board, getPlayer());
			nextMove = node.index;
		}
		if (nextMove != -1) {
			board.setMove(nextMove, getPlayer());
		}
	}
	
	public Node minimax(Board board, int player) {
		int end = board.isGameOver();
		if (end != 0) {
			return new Node(end, 0);
		}
		Node best = new Node();
		best.score = (player == Board.PLAYER_X) ? Integer.MIN_VALUE: Integer.MAX_VALUE;
		for (int i : board.getAvailableMoves()) {
			board.setMove(i, player);
			Node opp = minimax(board, -player);
			board.setMove(i, 0);
			if ((player * opp.score) > (player * best.score)) {
				best.index = i;
				best.score = opp.score;
			}

		}
		return best;
	}

}
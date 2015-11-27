package com.gojb4.tictactoe;

public class RomanAI extends CustomAI {

	private static final int RATIO = 100;
	private static final int X_WIN = Board.PLAYER_X * RATIO;
	private static final int O_WIN = Board.PLAYER_O * RATIO;
	private static final int DRAW = 0;

	public RomanAI(int player) {
		super(player);
	}

	@Override
	public void move(Board board) {
		int maxIndex = -1;
		if (board.isEmpty()) {
			maxIndex = (int) (Math.random() * board.getBoard().length);
		} else {
			int res = benefitsOfProgress(board, getPlayer(), 0);
			maxIndex = res - cutOffMove(res);
		}
		board.setMove(maxIndex, getPlayer());
		;
	}

	private int cutOffMove(int i) {
		if (i == 0) {
			return i;
		}
		return ((int) (i / RATIO)) * RATIO;
	}

	private int benefitsOfProgress(Board board, int player, int depth) {
		if (board.hasWin(Board.PLAYER_X)) {
			return X_WIN;
		} else if (board.hasWin(Board.PLAYER_O)) {
			return O_WIN;
		} else if (board.isFull()) {
			return DRAW;
		}

		int resVal = 0;
		if (player == Board.PLAYER_X) {
			resVal = O_WIN;
		} else {
			resVal = X_WIN;
		}
		for (int i : board.getAvailableMoves()) {
			board.setMove(i, player);
			int enemyVal = benefitsOfProgress(board, -player, depth + 1);
			// System.out.println("player = " + player + ", move = " + i + ", resVal = " + resVal + ", enemyVal = " + enemyVal + ", depth = " + depth);
			if (((player == Board.PLAYER_X) && (cutOffMove(enemyVal) > cutOffMove(resVal)))
					|| ((player == Board.PLAYER_O) && (cutOffMove(enemyVal) < cutOffMove(resVal)))) {
				resVal = cutOffMove(enemyVal);
				if (resVal < 0) {
					resVal -= i;
				} else {
					resVal += i;
				}
			}
			board.setMove(i, 0);
		}
		return resVal;
	}

}

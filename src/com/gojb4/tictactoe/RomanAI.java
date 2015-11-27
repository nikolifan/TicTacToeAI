package com.gojb4.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class RomanAI extends CustomAI {

	public static final int ENEMY_CAN_WIN = 5;
	public static final int DRAW = 2;
	public static final int UNCLEAR_SITUATION = 3;
	public static final int I_CAN_WIN = 4;

	public static final int CENTER_OF_THE_BOARD = 4;

	public static final int CORNER_1 = 0;
	public static final int CORNER_2 = 2;
	public static final int CORNER_3 = 6;
	public static final int CORNER_4 = 8;

	static List<Abc> listWinLines = new ArrayList<Abc>();

	public RomanAI(int player) {
		super(player);

		listWinLines.add(new Abc(0, 1, 2));
		listWinLines.add(new Abc(3, 4, 5));
		listWinLines.add(new Abc(6, 7, 8));

		listWinLines.add(new Abc(0, 3, 6));
		listWinLines.add(new Abc(1, 4, 7));
		listWinLines.add(new Abc(2, 5, 8));

		listWinLines.add(new Abc(0, 4, 8));
		listWinLines.add(new Abc(2, 4, 6));

	}

	public static boolean checkLine(int x, int[] board, int a, int b, int c) {
		return (board[a] == x) & (board[b] == x) & (board[c] == x);
	}

	private static boolean playerCanWin(int player, int[] board) {
		boolean res = false;

		for (Abc e : listWinLines) {
			res = checkLine(player, board, e.getA(), e.getB(), e.getC());
			if (res == true) {
				return true;
			}
		}

		return res;

	}

	@Override
	public void move(Board board) {

		int pos;
		int[] inputBoard = board.getBoard().clone();
		int countFillCells = countOfFillCells(inputBoard);

		if (countFillCells <= 1) {
			pos = getIndexOfGoodPosition(inputBoard);
		} else {
			pos = benefitsOfProgress(getPlayer(), inputBoard, null);
		}
		board.setMove(pos, getPlayer());

	}

	private int benefitsOfProgress(int player, int[] board, Integer cell) {

		int maxValue, maxIndex, resValue;
		resValue = maxValue = 0;
		maxIndex = -1;
		int enemy = -player;

		int[] localBoard = board.clone();
		if (cell != null) {
			localBoard[cell] = enemy;

			return getWeigthOfThatStep(enemy, localBoard);

		} else {

			for (int i = 0; i < localBoard.length; i++) {

				localBoard = board.clone();
				if (localBoard[i] == 0) {
					localBoard[i] = player;
					if (playerCanWin(player, localBoard)) {
						return i;// WIN;
					}

					resValue = benefitsOfProgress(player, localBoard, i);

					if (resValue >= maxValue) {
						maxValue = resValue;
						maxIndex = i;
					}

				}
			}
		}

		if (maxValue == UNCLEAR_SITUATION) {
			int IndexOfGoodPos = getIndexOfGoodPosition(localBoard);
			if (IndexOfGoodPos != -1) {
				maxIndex = IndexOfGoodPos;
			}
		}

		return maxIndex;
	}

	public static int getWeigthOfThatStep(int enemy, int[] board) {
		if (playerCanWin(enemy, board)) {
			return ENEMY_CAN_WIN;
		}

		if (canMove(board))
			return UNCLEAR_SITUATION;
		else
			return DRAW;
	}

	public static int countOfFillCells(int[] board) {
		int count = 0;
		for (int intCell : board) {
			if (intCell != 0) {
				count++;
			}
		}
		return count;
	}

	public static boolean canMove(int[] board) {

		for (int i = 0; i < board.length; i++) {
			if (board[i] == 0)
				return true;
		}
		return false;
	}

	public static int getIndexOfGoodPosition(int[] board) {
		int res = -1;
		if (board[CENTER_OF_THE_BOARD] == 0) {
			return CENTER_OF_THE_BOARD;
		} else if (board[CORNER_1] == 0) {
			return CORNER_1;
		} else if (board[CORNER_2] == 0) {
			return CORNER_2;
		} else if (board[CORNER_3] == 0) {
			return CORNER_3;
		} else if (board[CORNER_4] == 0) {
			return CORNER_4;
		}
		return res;
	}

	private class Abc {
		private int a;
		private int b;
		private int c;

		public Abc(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

		public int getA() {
			return a;
		}

		public int getB() {
			return b;
		}

		public int getC() {
			return c;
		}

	}


}

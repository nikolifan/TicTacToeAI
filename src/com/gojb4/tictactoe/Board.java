package com.gojb4.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class Board {

	public static final int PLAYER_X = 1;
	public static final int PLAYER_O = -1;
	public static final int PLAYER_X_WON = 4;
	public static final int DRAWN_GAME = 3;
	public static final int PLAYER_O_WON = 2;
	private static final int BOARD_SIZE = 9;
	
	private static final int[][] WIN_PATTERNS = { 
			{ 0, 1, 2 }, // Row 1
			{ 3, 4, 5 }, // Row 2
			{ 6, 7, 8 }, // Row 3
			{ 0, 3, 6 }, // Column 1
			{ 1, 4, 7 }, // Column 2
			{ 2, 5, 8 }, // Column 3
			{ 0, 4, 8 }, // Diagonal 1
			{ 2, 4, 6 }  // Diagonal 2
	};
	
	private int[] board = new int[BOARD_SIZE];
	
	public Board() {
	}
	
	public int[] getBoard() {
		return board;
	}

	public char[] toCharArray() {
		char[] res =  new char[board.length];
		for (int i = 0; i < res.length; i++) {
			if (board[i] == PLAYER_X) {
				res[i] = 'X';	
			} else if (board[i] == PLAYER_O) {
				res[i] = 'O';	
			} else res[i] = ' ';
		}
		return res;
	}
	
	public void setMove(int index, int player) {
		// some check
		board[index] = player;
	}
	
	public boolean isEmpty() {
		for (int i = 0; i < board.length; i++) {
			if (board[i] != 0)
				return false;
		}
		return true;
	}
	
	public boolean isFull() {
		for (int i = 0; i < board.length; i++) {
			if (board[i] == 0)
				return false;
		}
		return true;
	}	

	public int getWin() {
		for (int i = 0; i < WIN_PATTERNS.length; i++) {
			if (board[WIN_PATTERNS[i][0]] != 0 
					&& board[WIN_PATTERNS[i][0]] == board[WIN_PATTERNS[i][1]]
					&& board[WIN_PATTERNS[i][0]] == board[WIN_PATTERNS[i][2]]) {
				return board[WIN_PATTERNS[i][0]];
			}
		}
		return 0;
	}

	public int isGameOver() {
		int win = getWin();
		if (win == PLAYER_X) {
			return PLAYER_X_WON;
		} else if (win == PLAYER_O) {
			return PLAYER_O_WON;
		} else if (isFull()) {
			return DRAWN_GAME;
		} else
			return 0;
	}
	
	public void newGame() {
		for (int i = 0; i < board.length; i++) {
			board[i] = 0;
		}
	}
	
	public List<Integer> getAvailableMoves() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < board.length; i++) {
			if (board[i] == 0)
				list.add(i);
		}
		return list;
	}
	

}

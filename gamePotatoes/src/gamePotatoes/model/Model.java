package gamePotatoes.model;

import java.util.ArrayList;
import java.util.List;

import gamePotatoes.controler.Coordinates;
import javafx.scene.layout.HBox;

public class Model {
	// possible states of the field
	enum state {
		EMPTY, DOT, ROW, COLUMN, BOTH
	};

	// board with states
	state[][] board;
	// player scores
	private int scorePlayer1 = 0;
	private int scorePlayer2 = 0;
	// lists with the changes to be done in the view after the turn
	private List<String> toRowCross = new ArrayList<String>();
	private List<String> toColumnCross = new ArrayList<String>();
	private List<String> toBothCross = new ArrayList<String>();
	private List<String> toDot = new ArrayList<String>();
	// flag if the field was clicked again
	boolean doubleClicked = false;
	// fields used to check if it is the end of the game
	int counterBoth = 0;
	public boolean end = false;

	/**
	 * public constructor
	 * @param size size of the board
	 */
	public Model(int size) {
		end = false;
		board = new state[size + 1][size + 1];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j <= i; j++) {
				board[i][j] = state.EMPTY;
			}

		}
	}

	private void changeStateToBothCross(int i, int j) {
		board[i][j] = state.BOTH;
		toBothCross.add(Coordinates.createCoordinates(i, j));
		counterBoth++;
		System.out.println(counterBoth);
	}

	private void changeStateToRowCross(int i, int j) {
		board[i][j] = state.ROW;
		toRowCross.add(Coordinates.createCoordinates(i, j));
	}

	private void changeStateToColumnCross(int i, int j) {
		board[i][j] = state.COLUMN;
		toColumnCross.add(Coordinates.createCoordinates(i, j));
	}

	private void changeStateToDot(int i, int j) {
		board[i][j] = state.DOT;
		toDot.add(Coordinates.createCoordinates(i, j));
	}

	

	/**
	 * 
	 * @param i number of the row
	 * @return if the row is to be crossed out
	 */
	private boolean checkRow(int i) {
		boolean toCross = true;
		for (int x = 0; x <= i; x++) {
			if (board[i][x] == state.EMPTY) {
				toCross = false;
				break;
			}
		}
		return toCross;
	}
	/**
	 * 
	 * @param j number of the column
	 * @return if the column is to be crossed out
	 */
	private boolean checkColumn(int j) {
		boolean toCross = true;
		for (int x = j; x < board.length - 1; x++) {
			if (board[x][j] == state.EMPTY) {
				toCross = false;
				break;
			}
		}
		return toCross;
	}
	
	/**
	 * crosses out the row - changes states of the fields
	 * @param i row that is to be crossed out
	 */
	private void crossRow(int i) {
		for (int x = 0; x <= i; x++) {

			if (board[i][x] == state.COLUMN) {
				changeStateToBothCross(i, x);

				// Controller.crossBoth(i, x);
			} else {
				changeStateToRowCross(i, x);
				// Controller.crossRow(i, x);
			}
		}
	}

	/**
	 * crosses out the column - changes states of the fields
	 * @param i column that is to be crossed out
	 */
	private void crossColumn(int j) {
		for (int x = j; x < board.length - 1; x++) {

			if (board[x][j] == state.ROW) {
				changeStateToBothCross(x, j);
				// Controller.crossBoth(x, j);
			} else {
				board[x][j] = state.COLUMN;
				changeStateToColumnCross(x, j);
				// Controller.crossColumn(x, j);
			}
		}
	}

	/**
	 * changes the states of the field with given coordinates, checks states of
	 * fields that may be affected by this move and writes the fields with changed
	 * states to the lists of orders
	 * 
	 * @param coordinates coordinates of the field in a string
	 * @param player player that is scoring points in current move
	 */
	public void move(String coordinates, boolean player) {
		clear();
		int i = Coordinates.getRow(coordinates);
		int j = Coordinates.getColumn(coordinates);
		if (board[i][j] != state.EMPTY) {
			doubleClicked = true;
		} else {
			changeStateToDot(i, j);
			boolean col = checkColumn(j);
			boolean row = checkRow(i);
			if (col) {
				crossColumn(j);
				if (player) {
					scorePlayer1 += (board.length - j - 1);
				} else {
					scorePlayer2 += (board.length - j - 1);
				}
			}
			if (row) {
				crossRow(i);
				if (player) {
					scorePlayer1 += (i + 1);
				} else {
					scorePlayer2 += (i + 1);
				}
			}
			if (counterBoth == numberOfFields()) {
				end = true;
			}
		}
	}

	/**
	 * changes the states of the field with given coordinates, checks states of
	 * fields that may be affected by this move and writes the fields with changed
	 * states to the lists of orders
	 * 
	 * @param i      x - coordiante
	 * @param j      y - coordinate
	 * @param player player that is scoring points in current move
	 */
	public void move(int i, int j, boolean player) {

		System.out.println(numberOfFields());

		clear();
		if (board[i][j] != state.EMPTY) {
			doubleClicked = true;
		} else {
			board[i][j] = state.DOT;
			toDot.add(Coordinates.createCoordinates(i, j));
			boolean col = checkColumn(j);
			boolean row = checkRow(i);
			if (col) {
				crossColumn(j);
				if (player) {
					scorePlayer1 += (board.length - j - 1);
				} else {
					scorePlayer2 += (board.length - j - 1);
				}
			}
			if (row) {
				crossRow(i);
				if (player) {
					scorePlayer1 += (i + 1);
				} else {
					scorePlayer2 += (i + 1);
				}
			}
			if (counterBoth == numberOfFields()) {
				end = true;
			}

		}
	}

	/**
	 * 
	 * @return number of fields in the board
	 */
	private int numberOfFields() {
		int i = board.length - 1;

		int x = 0;
		while (i > 0) {
			x = x + i;
			i--;
		}
		return x;

	}

	/**
	 * clearing the lists of orders and flags
	 */
	private void clear() {
		toBothCross.clear();
		toColumnCross.clear();
		toRowCross.clear();
		toDot.clear();
		doubleClicked = false;
	}

	/**
	 * 
	 * @return if the field was double clicked
	 */
	public boolean ifDoubleClicked() {
		return doubleClicked;
	}

	/**
	 * 
	 * @return if it is the end of the game
	 */
	public boolean ifEnd() {
		return end;
	}

	/**
	 * 
	 * @return the list of potatoes to be horizontally crossed
	 */
	public List getToRowCross() {
		return toRowCross;
	}

	/**
	 * 
	 * @return the list of potatoes to be vertically crossed
	 */
	public List getToColumnCross() {
		return toColumnCross;
	}

	/**
	 * 
	 * @return the list of potatoes to be completely crossed
	 */
	public List getToBothCross() {
		return toBothCross;
	}

	/**
	 * 
	 * @return the list of potatoes to be marked with a dot
	 */
	public List getToDot() {
		return toDot;
	}

	/**
	 * 
	 * @return first player's score
	 */
	public int getScorePlayer1() {
		return scorePlayer1;
	}

	/**
	 * 
	 * @return second player's score
	 */
	public int getScorePlayer2() {
		return scorePlayer2;
	}
}

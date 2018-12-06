package gamePotatoes.model;

import java.util.ArrayList;
import java.util.List;

import gamePotatoes.controler.Coordinates;
import javafx.scene.layout.HBox;

public class Model {
	enum state {
		EMPTY, DOT, ROW, COLUMN, BOTH
	};

	state[][] board;
	private int scorePlayer1 = 0;
	private int scorePlayer2 = 0;

	private List<String> toRowCross = new ArrayList<String>();
	private List<String> toColumnCross = new ArrayList<String>();
	private List<String> toBothCross = new ArrayList<String>();
	private List<String> toDot = new ArrayList<String>();
	
	boolean doubleClicked = false;

	int counterBoth = 0;
	public boolean end = false;
	
	public boolean ifDoubleClicked() {
		return doubleClicked;
	}
	
	public boolean ifEnd() {
		return end;
	}
//listy pól do zmiany
	public List getToRowCross() {
		return toRowCross;
	}

	public List getToColumnCross() {
		return toColumnCross;
	}

	public List getToBothCross() {
		return toBothCross;
	}
	
	public List getToDot() {
		return toDot;
	}

//gettery wyników graczy
	public int getScorePlayer1() {
		return scorePlayer1;
	}

	public int getScorePlayer2() {
		return scorePlayer2;
	}

//konstruktor
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
	//Skreœlenie 
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

	// Sprawdzenie czy dany rz¹d jest do wywalenia
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

	public void move(String coordinates, boolean player) {
		clear();
		int i = Coordinates.getRow(coordinates);
		int j = Coordinates.getColumn(coordinates);
		if(board[i][j]!=state.EMPTY) {
			doubleClicked=true;
		}else {
			changeStateToDot(i, j);
		boolean col = checkColumn(j);
		boolean row = checkRow(i);
		if (col) {
			crossColumn(j);
			if(player) {
				scorePlayer1 += (board.length - j - 1);}else {
					scorePlayer2 += (board.length - j - 1);
				}
		}
		if (row) {
			crossRow(i);
			if(player) {
			scorePlayer1 += (i + 1);}else {
				scorePlayer2 += (i + 1);
			}
		}
		if(counterBoth == numberOfFields()) {
			end = true;
		}
		}
	}

	public void move(int i, int j, boolean player) {
		
		System.out.println(numberOfFields());
		
		clear();
		if(board[i][j]!=state.EMPTY) {
			doubleClicked=true;
		}else {
		board[i][j] = state.DOT;
		toDot.add(Coordinates.createCoordinates(i, j));
		boolean col = checkColumn(j);
		boolean row = checkRow(i);
		if (col) {
			crossColumn(j);
			if(player) {
				scorePlayer1 += (board.length - j - 1);}else {
					scorePlayer2 += (board.length - j - 1);
				}
		}
		if (row) {
			crossRow(i);
			if(player) {
				scorePlayer1 += (i + 1);}else {
					scorePlayer2 += (i + 1);
				}
		}
		if(counterBoth == numberOfFields()) {
			end = true;
		}
		
		}
		System.out.println("counter:"+counterBoth);
	}
	
	private int numberOfFields() {
		int i = board.length-1;
		
		int x=0;
		while(i>0) {
			x = x+i;
			i--;
		}
		System.out.println(x);
		return x;
		
	}
	
	private void clear() {
		toBothCross.clear();
		toColumnCross.clear();
		toRowCross.clear();
		toDot.clear();
		doubleClicked=false;
	}
}

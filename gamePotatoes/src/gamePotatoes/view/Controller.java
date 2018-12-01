package gamePotatoes.view;

import javafx.scene.Scene;
import javafx.stage.Stage;

public final class Controller {

	
	static Model model;
	static Panel panel;
	static AgainWindow again;
	
	static private Stage primaryStage;
	static private int size;
	static private String name;

	public Controller(int _size, String _name, Stage stage) {
		model = new Model(_size);
		panel = new Panel(_size, _name);
		size=_size;
		name=_name;
		primaryStage = stage;
		primaryStage.setTitle("Game of Potatoes");
		primaryStage.setScene(panel.getScene());
		primaryStage.show();
	}
	
	private static void again() {
		model = new Model(size);
		panel = new Panel(size, name);
		primaryStage.setTitle("Game of Potatoes");
		primaryStage.setScene(panel.getScene());
		primaryStage.show();
	}

	static void move(String coordinates) {
		model.move(coordinates);
		panel.getStatusPanel1().updateResult(model.getScorePlayer1());
		panel.getStatusPanel2().updateResult(model.getScorePlayer2());
		updatePotatoBoard();
	}

	static void move(int row, int column) {
		model.move(row, column);
		if (model.end == true) {
			if (model.getScorePlayer1() > model.getScorePlayer2()) {
				again = new AgainWindow(panel.getStatusPanel1().getName());	
			} else {
				again = new AgainWindow(panel.getStatusPanel2().getName());
			}
			if(again.ifAgain()) {again();}else{
				primaryStage.close();
			};
		}
		if (model.ifDoubleClicked() == true) {
			AlertWindow.showAlert("This Potatoe was already clicked!");
		} else {
			panel.getStatusPanel1().updateResult(model.getScorePlayer1());
			panel.getStatusPanel2().updateResult(model.getScorePlayer2());
			updatePotatoBoard();
		}
	}

	private static void updatePotatoBoard() {
		for (int i = 0; i < model.getToDot().size(); i++) {
			Dot((String) model.getToDot().get(i));
		}
		for (int i = 0; i < model.getToRowCross().size(); i++) {
			System.out.println(model.getToRowCross());
			crossRow((String) model.getToRowCross().get(i));
		}
		for (int i = 0; i < model.getToColumnCross().size(); i++) {
			crossColumn((String) model.getToColumnCross().get(i));
		}
		for (int i = 0; i < model.getToBothCross().size(); i++) {
			crossBoth((String) model.getToBothCross().get(i));
		}

	}

	private static void crossRow(String coordinates) {
		int i = Coordinates.getRow(coordinates);
		int j = Coordinates.getColumn(coordinates);
		panel.getPotatoBoard().getPotatoButton(i, j).crossRow();

	}

	private static void crossColumn(String coordinates) {
		int i = Coordinates.getRow(coordinates);
		int j = Coordinates.getColumn(coordinates);
		panel.getPotatoBoard().getPotatoButton(i, j).crossColumn();

	}

	private static void crossBoth(String coordinates) {
		int i = Coordinates.getRow(coordinates);
		int j = Coordinates.getColumn(coordinates);
		panel.getPotatoBoard().getPotatoButton(i, j).crossBoth();

	}

	private static void Dot(String coordinates) {
		int i = Coordinates.getRow(coordinates);
		int j = Coordinates.getColumn(coordinates);
		panel.getPotatoBoard().getPotatoButton(i, j).Dot();

	}

	public Panel getPanel() {
		return panel;

	}
}

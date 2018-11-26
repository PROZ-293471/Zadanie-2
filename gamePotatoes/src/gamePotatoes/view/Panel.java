package gamePotatoes.view;


import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Panel {

	
	
	
	Scene scene;
	BorderPane borderPane = new BorderPane();
	Board board;
	
	private Label Title = new Label("Game of Potatoes");
	private statusPanel player1 = new statusPanel("player 1");
	private statusPanel player2 = new statusPanel("player 2");
	
	Panel() {
		board = new Board(10);
		
		borderPane.setCenter(board);
		borderPane.setMargin(board, new Insets(20));
		borderPane.setLeft(player1);
		borderPane.setMargin(player1, new Insets(20));
		borderPane.setRight(player2);
		borderPane.setMargin(player2, new Insets(20));
		scene = new Scene(borderPane, 550, 330);
	};

	public Scene getScene() {
		return scene;

	};

}
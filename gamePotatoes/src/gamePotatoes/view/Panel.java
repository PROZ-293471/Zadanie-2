package gamePotatoes.view;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
/**
 * 
 * @author Rafal
 *Managing the view of the Board and Player Infos
 */
public class Panel {
	//all needed view objects
	private Scene scene;
	private BorderPane borderPane = new BorderPane();
	private PotatoBoard potatoBoard;
	private statusPanel player1 = new statusPanel("player 1");
	private statusPanel player2 = new statusPanel("player 2");
	
	public statusPanel getStatusPanel1() {
		return player1;
	}
	
	public statusPanel getStatusPanel2() {
		return player2;
	}
	/**
	 * Initialization of the new board, calculating the size of the window
	 * @param size size of the potato board
	 */
	@SuppressWarnings("static-access")
	public Panel(int size) {
		potatoBoard = new PotatoBoard(size);
		borderPane.setCenter(potatoBoard);
		borderPane.setMargin(potatoBoard, new Insets(20));
		borderPane.setLeft(player1);
		borderPane.setMargin(player1, new Insets(20));
		borderPane.setRight(player2);
		borderPane.setMargin(player2, new Insets(20));
		scene = new Scene(borderPane, 500+size*20, 50+size*25);
	};

	public Scene getScene() {
		return scene;
	};
	
	public PotatoBoard getPotatoBoard() {
		return potatoBoard;
	}

}
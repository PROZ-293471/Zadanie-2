package gamePotatoes.view;

import javafx.geometry.Orientation;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/**
 * 
 * @author Rafal
 *Managing the view of the triangular potato board
 */
public class PotatoBoard extends FlowPane{

	private PotatoButton[][] potatoes;
	private HBox[] rows;
	private VBox vbox;

/**
 * Initialization of the board
 * @param size size of the board
 */
public PotatoBoard(int size) {
		rows = new HBox[size];
		potatoes = new PotatoButton[size][size];
		for(int i=0; i<size; i++) {
			rows[i]= new HBox();
			for(int j=0; j<=i; j++) {
				potatoes[i][j]= new PotatoButton(i,j);
				rows[i].getChildren().add(potatoes[i][j]);
			}
			
		}
		vbox = new VBox(rows);
		this.setPrefSize(20, 20);
		this.getChildren().add(vbox);
		this.setOrientation(Orientation.HORIZONTAL);
		

	};
	
	public PotatoButton getPotatoButton(int i, int j) {
		return potatoes[i][j];
	}
	
	
}

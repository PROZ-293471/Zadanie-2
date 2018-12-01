package gamePotatoes.view;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Board extends FlowPane{

	private ActionButton[][] potatoes;
	private HBox[] rows;
	private VBox vbox;
	

public Board(int size) {
		rows = new HBox[size+1];
		potatoes = new ActionButton[size+1][size+1];
		for(int i=0; i<=size; i++) {
			rows[i]= new HBox();
			for(int j=0; j<i; j++) {
				potatoes[i][j]= new ActionButton();
				rows[i].getChildren().add(potatoes[i][j]);
			}
			
		}
		vbox = new VBox(rows);
		this.setPrefSize(20, 20);
		this.getChildren().add(vbox);
		this.setOrientation(Orientation.HORIZONTAL);
		

	};
	
}

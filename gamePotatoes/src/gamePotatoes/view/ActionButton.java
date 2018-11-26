package gamePotatoes.view;


import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ActionButton extends Button {
	
	Boolean row;
	Boolean column;
	List<String> states = List.of("·","|","-","+"); 
	
	
	ActionButton(){
		new Button();
		this.setText(" ");
		this.setPrefSize(25, 25);
		this.setOnAction(e -> standardAction());
	};


	private void standardAction() {
		this.setText("·");
	}
}

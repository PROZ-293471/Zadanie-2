package gamePotatoes.view;


import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class PotatoButton extends Button {
	
	private int column;
	private int row;
	
	
	
	private final String[] states = {"·","|","--","+"};
	
	
	PotatoButton(int i, int j){
		new Button();
		row = i;
		column = j;
		this.setText(" ");
		this.setPrefSize(25, 25);
		this.setOnAction(e -> standardAction());
	};


	private void standardAction() {
		Controller.move(row, column);
	}
	
	public void crossColumn() {
		this.setText(states[1]);
	}
	
	public void crossRow() {
		this.setText(states[2]);
	}
	
	public void crossBoth() {
		this.setText(states[3]);
	}
	
	public void Dot() {
		this.setText(states[0]);
	}
	
	public String getCoordinates() {
		return Integer.toString(row)+","+Integer.toString(column);
	}
	
	
}

package gamePotatoes.view;


import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/**
 * 
 * @author Rafal
 *Managing the view of the single potato
 */
public class PotatoButton extends Button {
	//coordinates
	private int column;
	private int row;
	//possible states - pictures displayed on the potatoButton
	private final String[] states = {"•","|","—","┼"};
	
	/**
	 * Managing the view of each potatoButton
	 * @param i row coordinate
	 * @param j column coordinate
	 */
	PotatoButton(int i, int j){
		new Button();
		row = i;
		column = j;
		this.setShape(new Circle(50));
		this.setText(" ");
		this.setFont(Font.font("Verdana",FontWeight.BOLD,10));
		this.setPrefSize(25, 25);
	}

	//actions that can be done by potatoButton
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
	/**
	 * 
	 * @return coordinates of the potatoButton
	 */
	public String getCoordinates() {
		return Integer.toString(row)+","+Integer.toString(column);
	}
	
	
}

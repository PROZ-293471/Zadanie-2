package gamePotatoes.view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class statusPanel extends VBox{
	private Label player = new Label();
	private Label result = new Label();
	private int value;
	private String name;
	
	statusPanel(String nam){
		name = nam;
		value = 0;
		update();
		this.getChildren().addAll(player, result);
		
	}
	
	private void update() {
		player.setText(name);
		result.setText(Integer.toString(value));
	}

	public int getResult() {
		return value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String nam) {
		name = nam;
		update();
	}
	
	
	public void updateResult(int x) {
		value=x;
		update();
	}
}

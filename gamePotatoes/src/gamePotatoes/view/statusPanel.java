package gamePotatoes.view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class statusPanel extends VBox{
	Label player = new Label();
	Label result = new Label();
	int value;
	String name;
	
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
	
	public void updateResult(int x) {
		value+=x;
	}
}

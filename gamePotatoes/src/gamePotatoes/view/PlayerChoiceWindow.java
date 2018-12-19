package gamePotatoes.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.ChoiceDialog;
/**
 * 
 * @author Rafal
 *Managing the view of the window in which the player is chosen 
 */

public class PlayerChoiceWindow{
	String size;
	
	private List<String> options = new ArrayList<String>();
	ChoiceDialog<String> dialog;
	

	public PlayerChoiceWindow(){
	options.add("Player 1");
	options.add("Player 2");
	dialog = new ChoiceDialog<>("Player 1",options);
	dialog.setTitle("Game of potatoes");
	dialog.setHeaderText("Game Settings");
	dialog.setContentText("Choose the player:");
	}
	/**
	 * 
	 * @return selected size
	 */
	public String getPlayer() {
		String player = "Player 1";
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    player=result.get();
		}
		return player;
	}
}

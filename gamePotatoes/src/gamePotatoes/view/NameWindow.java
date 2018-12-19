package gamePotatoes.view;

import java.util.Optional;

import javafx.scene.control.TextInputDialog;

/**
 * Managing the view of the window in which name is typed
 * @author Rafal
 *
 */
public class NameWindow {
String size;
	
	TextInputDialog dialog;
	

	public NameWindow(){

	dialog = new TextInputDialog("Player");
	dialog.setTitle("Game of potatoes");
	dialog.setHeaderText("Game Settings");
	dialog.setContentText("Insert your name:");
	}
	
	public String getName() {
		String value = "Player";
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    value=result.get();
		}
		return value;
	}
}

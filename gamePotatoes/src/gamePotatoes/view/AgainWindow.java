package gamePotatoes.view;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AgainWindow {
	Alert alert = new Alert(AlertType.CONFIRMATION);
	
	public AgainWindow(String player){
	alert.setTitle("End of the game");
	alert.setHeaderText("Player "+player+" won!");
	alert.setContentText("Do you want to play again?");
	}
	
	public boolean ifAgain(){
	Optional<ButtonType> result = alert.showAndWait();
	boolean again;
	if (result.get() == ButtonType.OK){
	    again = true;
	} else {
	    again = false;
	}
	return again;
	}
}

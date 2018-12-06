package gamePotatoes.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;

public class NameWindow {
String size;
	
	TextInputDialog dialog;
	

	public NameWindow(){

	dialog = new TextInputDialog("Player");
	dialog.setTitle("Game of potatoes");
	dialog.setHeaderText("Ustawienia gry");
	dialog.setContentText("Podaj nazwê gracza:");
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

package gamePotatoes.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
/**
 * 
 * @author Rafal
 *Managing the view of the window in which size of the board is selected
 */
public class SizeWindow{
	String size;
	
	private List<String> options = new ArrayList<String>();
	ChoiceDialog<String> dialog;
	

	public SizeWindow(){
	options.add("5");
	options.add("10");
	options.add("15");
	options.add("20");
	options.add("25");
	dialog = new ChoiceDialog<>("5",options);
	dialog.setTitle("Game of potatoes");
	dialog.setHeaderText("Ustawienia gry");
	dialog.setContentText("Set the board size:");
	}
	/**
	 * 
	 * @return selected size
	 */
	public int getSize() {
		int value = 0;
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    value=Integer.parseInt(result.get());
		}
		return value;
	}
}

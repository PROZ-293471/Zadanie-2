package gamePotatoes.view;



import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
/**
 * 
 * @author Rafal
 *Managing the view of the player panel - name and score
 */
public class statusPanel extends VBox{
	private Text player;
	private Label result = new Label();
	private int value;
	private String name;
	
	statusPanel(String nam){
		player = new Text(nam);
		player.setFont(Font.font("Verdana",20));
		result.setFont(Font.font(18));
		value = 0;
		update();
		this.setPrefWidth(130);
		this.getChildren().addAll(player, result);
		
	}
	
	private void update() {
		player.setText(name);
		result.setText("Score: "+Integer.toString(value));
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
	/**
	 * method used to highlight the player that is currently moving
	 */
	public void redFont() {
		player.setText("►"+name);
		player.setFill(Color.RED);
		player.setFont(Font.font("Verdana",FontWeight.BOLD, 20));
	}
	
	public void blackFont() {
		player.setFill(Color.BLACK);
		player.setFont(Font.font("Verdana", 20));
		update();
	}
}

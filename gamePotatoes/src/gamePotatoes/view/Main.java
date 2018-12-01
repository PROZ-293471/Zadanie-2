package gamePotatoes.view;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	
	Scene scene;

	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		SizeWindow dialog = new SizeWindow();
		NameWindow name = new NameWindow();

		Controller controller = new Controller(dialog.getSize(), name.getName(), primaryStage);
		/*scene = controller.getPanel().getScene();
		primaryStage.setTitle("Game of Potatoes");
		primaryStage.setScene(scene);
		primaryStage.show();*/
	}

}

package gamePotatoes.view;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	Panel panel = new Panel();
	Scene scene;

	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		scene = panel.getScene();
		primaryStage.setTitle("Game of Potatoes");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}

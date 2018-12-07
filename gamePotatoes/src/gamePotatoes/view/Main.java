package gamePotatoes.view;

import javax.jms.JMSException;

import gamePotatoes.controler.Consumer;
import gamePotatoes.controler.Controler;
import gamePotatoes.controler.Producer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	
	Scene scene;

	public static void main(String[] args) throws JMSException, InterruptedException {

		/*Producer producer = new Producer("localhost:4848/jms", "WSQueue");
		Consumer consumer = new Consumer("localhost:4848/jms", "ATJQueue");
		  producer.sendQueueMessage("POTATOE");
		  consumer.receiveQueueMessageAsync();*/
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		SizeWindow dialog = new SizeWindow();
		NameWindow name = new NameWindow();

		Controler controler = new Controler(dialog.getSize(), name.getName(), primaryStage);
		/*scene = controller.getPanel().getScene();
		primaryStage.setTitle("Game of Potatoes");
		primaryStage.setScene(scene);
		primaryStage.show();*/
	}

}

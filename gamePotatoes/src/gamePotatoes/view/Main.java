package gamePotatoes.view;

import java.net.ConnectException;

import javax.jms.JMSException;

import gamePotatoes.controler.Consumer;
import gamePotatoes.controler.Controler;
import gamePotatoes.controler.Producer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	
	Scene scene;

	public static void main(String[] args){

	launch(args);
	}

	@Override
	public void start(Stage primaryStage){

		
		NameWindow name = new NameWindow();
		SizeWindow dialog = new SizeWindow();
		//first player: "WSQueue", "ATJQueue", true
		//second player: "ATJQueue", "WSQueue", false
		Controler controler = new Controler( name.getName(), dialog.getSize(), primaryStage, "WSQueue", "ATJQueue", true);
	}

}

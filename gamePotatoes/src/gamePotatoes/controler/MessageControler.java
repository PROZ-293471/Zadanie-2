package gamePotatoes.controler;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import javafx.application.Platform;
import javafx.stage.Stage;

public class MessageControler {
	Controler controler;
	Producer producer;
	Consumer consumer;

	public MessageControler(int size, String name, Stage stage) throws JMSException {
		
		producer = new Producer("localhost:4848/jms", "WSQueue");
		producer.sendQueueMessage("Wiadomosc");
		consumer = new Consumer("localhost:4848/jms", "ATJQueue");
		consumer.getJMSConsumer().setMessageListener(new Listener());
		controler = new Controler(size, name, stage);
	}

private class Listener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage)
			try {
				System.out.printf("Ruch:'%s'%n", ((TextMessage) message).getText());
				String coordinates = ((TextMessage) message).getText();
				System.out.printf("Ruch:'%s'%n", coordinates);
				Platform.runLater(() -> controler.move(coordinates,false));
			} catch (JMSException e) {
				e.printStackTrace();
			}
		
	}
	
}
	/*public void listen() {
		while (true) {
		
		if (controler.myMove == false) {
			consum
		}
		}	
	}*/
}

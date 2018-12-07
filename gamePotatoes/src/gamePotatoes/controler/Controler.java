package gamePotatoes.controler;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import gamePotatoes.model.Model;
import gamePotatoes.view.AgainWindow;
import gamePotatoes.view.AlertWindow;
import gamePotatoes.view.Panel;
import javafx.application.Platform;
import javafx.stage.Stage;

public final class Controler {

	public boolean myMove = true;

	static Model model;
	static Panel panel;
	static AgainWindow again;

	static private Stage primaryStage;
	static private int size;
	static private String name1;
	private String name2 = "Player";

	Controler controler;
	static Producer producer;
	Consumer consumer;
	int counter = 0;

	/**
	 * Initializes View and Model, sets Actions to the Buttons
	 * 
	 * @param _size size of the game board
	 * @param _name name of the player
	 * @param stage the base of the display
	 * @throws JMSException
	 */
	public Controler(int _size, String _name, Stage stage) throws JMSException {
		model = new Model(_size);
		panel = new Panel(_size, _name);
		size = _size;
		name1 = _name;
		panel.getStatusPanel2().setName(name2);
		primaryStage = stage;
		primaryStage.setTitle("Game of Potatoes");
		primaryStage.setScene(panel.getScene());
		primaryStage.show();
		setActionsToPotatoeButtons();

		producer = new Producer("localhost:4848/jms", "WSQueue");
		producer.sendQueueMessage(name1);
		consumer = new Consumer("localhost:4848/jms", "ATJQueue");
		consumer.getJMSConsumer().setMessageListener(new Listener());
	}

	private class Listener implements MessageListener {
		

		@Override
		public void onMessage(Message message) {
			if (message instanceof TextMessage)
				try {
					if (counter == 0) {
						Platform.runLater(()->{
							try {
								name2 =   ((TextMessage) message).getText();
								panel.getStatusPanel2().setName(name2);
							} catch (JMSException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						});
						counter++;
					} else {

						System.out.printf("Ruch:'%s'%n", ((TextMessage) message).getText());
						String coordinates = ((TextMessage) message).getText();
						System.out.printf("Ruch:'%s'%n", coordinates);
						Platform.runLater(() -> move(coordinates, false));
					}
				} catch (JMSException e) {
					e.printStackTrace();
				}

		}
	}

	/**
	 * Sets move actions to each button in the game board
	 */
	private static void setActionsToPotatoeButtons() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j <= i; j++) {
				int ii = i;
				int jj = j;
				panel.getPotatoBoard().getPotatoButton(i, j)
						.setOnAction(e -> move(panel.getPotatoBoard().getPotatoButton(ii, jj).getCoordinates(), true));
			}
		}

	}

	/**
	 * Main move action
	 * 
	 * @param coordinates coordinates in the board in the form of string
	 */
	static void move(String coordinates, boolean player) {

		if (player == true) {
			producer.sendQueueMessage(coordinates);
		}

		move(Coordinates.getRow(coordinates), Coordinates.getColumn(coordinates), player);
	}

	/**
	 * Main move action
	 * 
	 * @param row    coordinate in the board
	 * @param column coordinate in the board
	 */
	static private void move(int row, int column, boolean player) {
		model.move(row, column, player);
		panel.getStatusPanel1().updateResult(model.getScorePlayer1());
		panel.getStatusPanel2().updateResult(model.getScorePlayer2());
		updatePotatoBoard();
		if (model.ifEnd() == true) {
			if (model.getScorePlayer1() > model.getScorePlayer2()) {
				again = new AgainWindow(panel.getStatusPanel1().getName());
			} else {
				again = new AgainWindow(panel.getStatusPanel2().getName());
			}
			if (again.ifAgain()) {
				again();
			} else {
				primaryStage.close();
			}
			;
		}
		if (model.ifDoubleClicked() == true) {
			AlertWindow.showAlert("This Potatoe was already clicked!");
		}

	}

	/**
	 * updates the view by taking the data from the model
	 */
	private static void updatePotatoBoard() {
		for (int i = 0; i < model.getToDot().size(); i++) {
			Dot((String) model.getToDot().get(i));
		}
		for (int i = 0; i < model.getToRowCross().size(); i++) {
			System.out.println(model.getToRowCross());
			crossRow((String) model.getToRowCross().get(i));
		}
		for (int i = 0; i < model.getToColumnCross().size(); i++) {
			crossColumn((String) model.getToColumnCross().get(i));
		}
		for (int i = 0; i < model.getToBothCross().size(); i++) {
			crossBoth((String) model.getToBothCross().get(i));
		}

	}

	/**
	 * updating the symbol of specific button
	 * 
	 * @param coordinates coordinates in the board game in form of string
	 */
	private static void crossRow(String coordinates) {
		int i = Coordinates.getRow(coordinates);
		int j = Coordinates.getColumn(coordinates);
		panel.getPotatoBoard().getPotatoButton(i, j).crossRow();

	}

	/**
	 * updating the symbol of specific button
	 * 
	 * @param coordinates coordinates in the board game in form of string
	 */
	private static void crossColumn(String coordinates) {
		int i = Coordinates.getRow(coordinates);
		int j = Coordinates.getColumn(coordinates);
		panel.getPotatoBoard().getPotatoButton(i, j).crossColumn();

	}

	/**
	 * updating the symbol of specific button
	 * 
	 * @param coordinates coordinates in the board game in form of string
	 */
	private static void crossBoth(String coordinates) {
		int i = Coordinates.getRow(coordinates);
		int j = Coordinates.getColumn(coordinates);
		panel.getPotatoBoard().getPotatoButton(i, j).crossBoth();

	}

	/**
	 * updating the symbol of specific button
	 * 
	 * @param coordinates coordinates in the board game in form of string
	 */
	private static void Dot(String coordinates) {
		int i = Coordinates.getRow(coordinates);
		int j = Coordinates.getColumn(coordinates);
		panel.getPotatoBoard().getPotatoButton(i, j).Dot();

	}

	/**
	 * Creating a new View and Model with the same starting parameters
	 */
	private static void again() {
		model = new Model(size);
		panel = new Panel(size, name1);
		primaryStage.setTitle("Game of Potatoes");
		primaryStage.setScene(panel.getScene());
		primaryStage.show();
		setActionsToPotatoeButtons();
	}

}

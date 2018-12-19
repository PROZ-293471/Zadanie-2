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

	// size of the gameboard used to initialize Model and view
	static private int boardSize;
	// model managing
	static Model model;
	// view managing
	static Panel panel;
	static AgainWindow again;
	static private Stage primaryStage;
	// player names
	static private String name1;
	private static String name2 = "Waiting for player...";
	// flag representing whose turn is it
	public static boolean myMove;
	// message managing
	static Producer producer;
	Consumer consumer;
	static int messageCounter = 0;

	/**
	 * Initializes View and Model, sets Actions to the Buttons
	 * 
	 * @param _size size of the game board
	 * @param _name name of the player
	 * @param stage the base of the display
	 * @throws InterruptedException
	 * @throws JMSException
	 */
	public Controler(String _name, int _size, Stage stage, String producerQueue, String consumerQueue, boolean player)
			throws InterruptedException {
		// initialization of message managing
		name1 = _name;
		boardSize = _size;
		
		try {
			producer = new Producer("localhost:4848/jms", producerQueue);
			consumer = new Consumer("localhost:4848/jms", consumerQueue);
		} catch (JMSException e) {
			AlertWindow.showAlert("Problem occured when tried to connect to a server!");
		}

		producer.sendQueueMessage(name1);
		consumer.getJMSConsumer().setMessageListener(new Listener());

		// initialization of the objects
		primaryStage = stage;
		primaryStage.setTitle("Game of Potatoes");
		
		model = new Model(boardSize);
		panel = new Panel(boardSize);

		panel.getStatusPanel2().setName(name2);
		panel.getStatusPanel1().setName(name1);

		primaryStage.setScene(panel.getScene());
		primaryStage.show();
		updateStatusPanel();
		setActionsToPotatoeButtons();
		// setting the player (true - first turn, false - second turn)
		myMove = player;

	}

	/**
	 * 
	 * @author Rafal implements onMessage method
	 */
	private class Listener implements MessageListener {

		private void setPlayer2Name(Message message) {
			Platform.runLater(() -> {
				try {
					name2 = ((TextMessage) message).getText();
					panel.getStatusPanel2().setName(name2);
					updateStatusPanel();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			});
		}
		
		private void player2Move(Message message) throws JMSException {
			String coordinates = ((TextMessage) message).getText();
			Platform.runLater(() -> move(coordinates, false));
		}

		@Override
		public void onMessage(Message message) {
			if (message instanceof TextMessage)
				try {

					// very first message - message with the second player name
					switch (messageCounter) {
					case 0:
						setPlayer2Name(message);
						messageCounter++;
						if (myMove == true) {
							messageCounter++;
						}
						break;

					// second player move
					default:
						player2Move(message);
						break;
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
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j <= i; j++) {
				int ii = i;
				int jj = j;
				panel.getPotatoBoard().getPotatoButton(i, j).setOnAction(e -> {
					// move is enabled only when it is first player turn
					if (myMove == true && messageCounter != 0) {
						move(panel.getPotatoBoard().getPotatoButton(ii, jj).getCoordinates(), true);
					} else {
						AlertWindow.showAlert("Waiting for the opponent!");
					}
				});
			}
		}

	}

	/**
	 * Main move action
	 * 
	 * @param coordinates coordinates in the board in the form of string
	 */
	static void move(String coordinates, boolean player) {
		move(Coordinates.getRow(coordinates), Coordinates.getColumn(coordinates), player);
	}

	/**
	 * Main move action
	 * 
	 * @param row    coordinate in the board
	 * @param column coordinate in the board
	 */
	static private void move(int row, int column, boolean player) {
		// making a turn in model
		model.move(row, column, player);
		//special action - double clicked
		if (model.ifDoubleClicked() == true) {
			AlertWindow.showAlert("This Potatoe was already clicked!");
			return;
		}
		// sending message only if it is first player turn
		if (player == true) {
			producer.sendQueueMessage(Coordinates.createCoordinates(row, column));
		}
		// setting the flag - turn of another player
		myMove = !myMove;
		// updating the view
		updateStatusPanel();
		updatePotatoBoard();

		// special actions - end of the game or double clicked button
		if (model.ifEnd() == true) {
			endOfTheGame();
		}
		

	}

	/**
	 * updating the score and setting the red font for currently moving player
	 */
	private static void updateStatusPanel() {
		panel.getStatusPanel1().updateResult(model.getScorePlayer1());
		panel.getStatusPanel2().updateResult(model.getScorePlayer2());

		if (myMove == true) {
			panel.getStatusPanel1().redFont();
			panel.getStatusPanel2().blackFont();
		} else {
			panel.getStatusPanel2().redFont();
			panel.getStatusPanel1().blackFont();
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
	
	private static void endOfTheGame() {
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
	}

	/**
	 * Creating a new View and Model with the same starting parameters
	 */
	private static void again() {
		model = new Model(boardSize);
		panel = new Panel(boardSize);
		primaryStage.setTitle("Game of Potatoes");
		primaryStage.setScene(panel.getScene());
		primaryStage.show();
		setActionsToPotatoeButtons();
		panel.getStatusPanel2().setName(name2);
		panel.getStatusPanel1().setName(name1);
		updateStatusPanel();
	}

}

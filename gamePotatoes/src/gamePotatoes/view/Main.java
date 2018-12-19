package gamePotatoes.view;

import gamePotatoes.controler.Controler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Regu�y gry: Gra polega na tym, �e gracze na zmiane zaznaczaj� "ziemniaki" na
 * planszy. Ten gracz, kt�ry jako ostatni zaznaczy "ziemniaka" w kolumnie lub
 * rz�dzie zdobywa tyle punkt�w ile by�o ��cznie "ziemniak�w" w tej kolumnie lub
 * rz�dzie. Gra toczy si� do momentu a� wszystkie ziemniaki zostan� zaznaczone.
 * Wygrywa gracz, kt�ry na ko�cu rozgrywki ma wi�cej punkt�w na koncie.
 * 
 * Og�lny opis architektury projektu:
 * 
 * Pierwszy gracz wybiera nazw� i rozmiar planszy i przesy�a asynchronicznie do
 * gracza drugiego. Drugi gracz wybiera nazw� i ewentualnie czeka a� gracz 1
 * wybierze rozmiar planszy
 * 
 * Po klikni�ciu kontroler przekazuje do klasy modelu wsp�rz�dne "ziemniaka",
 * kt�ry zosta� naci�ni�ty. Oraz wysy�a te same wsp�rz�dne asynchronicznie do
 * drugiego gracza.
 * 
 * Model sprawdza, czy dane pole nie by�o wcze�niej naci�ni�te i wylicza jakie
 * zmiany powinny zaj�� na planszy, zapisuje je.
 * 
 * Kontroler odczytuje zmiany wyliczone przez model i wywo�uje odpowiednie
 * funkcje widoku zmieniaj�ce jego stan.
 * 
 * Kontroler blokuje mo�liwo�� ruchu do czasu a� ruch nie zostanie wykonany
 * przez przeciwnika. Wsp�rz�dne pola wybranego przez przeciwnika s� przesy�ane
 * asynchronicznie i przekazywane przez kontroler do modelu, kt�ry podobnie jak
 * przy w�asnym ruchu zapisuje zmiany, kt�re kontroler nast�pnie aplikuje do
 * widoku.
 * 
 * W jednej z instancji programu w oknie dialogowym "WindowPlayer" nale�y wybra�
 * Player1 a w drugiej Player2, wywo�a konstruktor kontrolera z odpowiednimi
 * parametrami
 * 
 * @author Rafal
 * 
 */
public class Main extends Application {

	Scene scene;

	public static void main(String[] args) {

		launch(args);
	}

	@SuppressWarnings("unused")
	@Override
	public void start(Stage primaryStage) {

		int boardSize = 10;
		NameWindow name = new NameWindow();
		PlayerChoiceWindow player = new PlayerChoiceWindow();
		// first player: "WSQueue", "ATJQueue", true
		// second player: "ATJQueue", "WSQueue", false
		try {
			if (player.getPlayer().equals("Player 1")) {
				Controler controler = new Controler(name.getName(), boardSize, primaryStage, "WSQueue", "ATJQueue",
						true);
			} else {
				Controler controler = new Controler(name.getName(), boardSize, primaryStage, "ATJQueue", "WSQueue",
						false);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

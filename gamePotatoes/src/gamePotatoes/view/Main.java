package gamePotatoes.view;

import java.net.ConnectException;

import javax.jms.JMSException;

import gamePotatoes.controler.Consumer;
import gamePotatoes.controler.Controler;
import gamePotatoes.controler.Producer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Regu³y gry: Gra polega na tym, ¿e gracze na zmiane zaznaczaj¹ "ziemniaki" na
 * planszy. Ten gracz, który jako ostatni zaznaczy "ziemniaka" w kolumnie lub
 * rzêdzie zdobywa tyle punktów ile by³o ³¹cznie "ziemniaków" w tej kolumnie lub
 * rzêdzie. Gra toczy siê do momentu a¿ wszystkie ziemniaki zostan¹ zaznaczone.
 * Wygrywa gracz, który na koñcu rozgrywki ma wiêcej punktów na koncie.
 * 
 * Ogólny opis architektury projektu:
 * 
 * Pierwszy gracz wybiera nazwê i rozmiar planszy i przesy³a asynchronicznie do
 * gracza drugiego. Drugi gracz wybiera nazwê i ewentualnie czeka a¿ gracz 1
 * wybierze rozmiar planszy
 * 
 * Po klikniêciu kontroler przekazuje do klasy modelu wspó³rzêdne "ziemniaka",
 * który zosta³ naciœniêty. Oraz wysy³a te same wspó³rzêdne asynchronicznie do
 * drugiego gracza.
 * 
 * Model sprawdza, czy dane pole nie by³o wczeœniej naciœniête i wylicza jakie
 * zmiany powinny zajœæ na planszy, zapisuje je.
 * 
 * Kontroler odczytuje zmiany wyliczone przez model i wywo³uje odpowiednie
 * funkcje widoku zmieniaj¹ce jego stan.
 * 
 * Kontroler blokuje mo¿liwoœæ ruchu do czasu a¿ ruch nie zostanie wykonany
 * przez przeciwnika. Wspó³rzêdne pola wybranego przez przeciwnika s¹ przesy³ane
 * asynchronicznie i przekazywane przez kontroler do modelu, który podobnie jak
 * przy w³asnym ruchu zapisuje zmiany, które kontroler nastêpnie aplikuje do
 * widoku.
 * 
 * Programy graczy ró¿ni¹ siê jedynie parametrami konstruktora Controlera w metodzie start:
 * 
 * Gracz 1: Controler(name.getName(), primaryStage, "WSQueue", "ATJQueue", true);
 * Gracz 2: Controler(name.getName(), primaryStage, "ATJQueue", "WSQueue", false);
 * 
 * @author Rafal
 * 
 */
public class Main extends Application {

	Scene scene;

	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		NameWindow name = new NameWindow();
		// SizeWindow dialog = new SizeWindow();
		// first player: "WSQueue", "ATJQueue", true
		// second player: "ATJQueue", "WSQueue", false
		try {
			Controler controler = new Controler(name.getName(), primaryStage, "WSQueue", "ATJQueue", true);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

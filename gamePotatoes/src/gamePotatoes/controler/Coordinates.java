package gamePotatoes.view;

public class Coordinates {
	
	private static int getCommaPosition(String coordinates) {
		return coordinates.indexOf(",");
	}

	static int getRow(String coordinates) {
		return Integer.parseInt(coordinates.substring(0, getCommaPosition(coordinates)));
	}
	
	static int getColumn(String coordinates) {
		return Integer.parseInt(coordinates.substring((getCommaPosition(coordinates))+1));
	}
	
	static String createCoordinates(int i, int j) {
		
		return Integer.toString(i)+","+Integer.toString(j);
	}
	
	
}

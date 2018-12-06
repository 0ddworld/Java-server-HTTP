package iut.rt.hachettp;
/**
 * Cette classe est une reponse d'erreur de version HTTP
 * 
 * @author Lienard Tristan
 * @date 2017
 */

public class Reponse505 extends Reponse {
	
	public Reponse505(){
		le_message = "HTTP VERSION NOT SUPPORTED";
		le_code = 505;
	}
}


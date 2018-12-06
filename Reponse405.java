package iut.rt.hachettp;
/**
 * Cette classe est une reponse d'erreur de la methode HTTP.
 * 
 * @author Lienard Tristan
 * @date 2017
 */

public class Reponse405 extends Reponse {
	
	public Reponse405(){
		le_message = "METHOD NOT ALLOWED";
		le_code = 405;
	}
}

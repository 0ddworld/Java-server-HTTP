package iut.rt.hachettp;
/**
 * Cette classe est une reponse d'erreur de mauvaise requete.
 * 
 * @author Lienard Tristan
 * @date 2017
 */

public class Reponse400 extends Reponse {
	
	public Reponse400(){
		le_message = "BAD REQUEST";
		le_code = 400;
	}
}

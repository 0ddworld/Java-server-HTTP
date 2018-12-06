package iut.rt.hachettp;
/**
 * Cette classe est une reponse d'erreur interne au serveur.
 * 
 * @author Lienard Tristan
 * @date 2017
 */

public class Reponse500 extends Reponse {
	
	/**
	 * Constructeur par default. initialise le_contenu avec le_message.
	 */
	public Reponse500(){
		le_message = "INTERNAL SERVER ERROR";
		le_code = 500;
		le_contenu = new byte[5000];
		le_contenu = le_message.getBytes();
	}
}

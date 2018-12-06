package iut.rt.hachettp;
/**
 * Cette classe represente une exception interne au serveur.
 * 
 * @author Lienard Tristan
 * @date 2017
 */

public class InternalServerErrorException extends Exception {
	
	/**
	 * Constructeur par default.
	 * 
	 * @param msg le message d'erreur de l'exception.
	 */
	public InternalServerErrorException(String msg){
		super(msg);
	}
}

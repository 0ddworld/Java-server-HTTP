package iut.rt.hachettp;
/**
 * Cette classe represente une exception HTTP.
 * 
 * @author Lienard Tristan
 * @date 2017
 */

public class HTTPException extends Exception {
	
	/**
	 * Constructeur par default.
	 * 
	 * @param msg le message d'erreur de l'exception.
	 */
	public HTTPException(String msg){
		super(msg);
	}
}

package iut.rt.hachettp;
/**
 * Cette classe represente une exception d'une mauvaise version d'HTTP.
 * 
 * @author Lienard Tristan
 * @date 2017
 */

public class HTTPVersionNotSupportedException extends Exception {

	/**
	 * Constructeur par default.
	 * 
	 * @param msg le message d'erreur de l'exception.
	 */
	public HTTPVersionNotSupportedException(String msg){
		super(msg);
	}
}

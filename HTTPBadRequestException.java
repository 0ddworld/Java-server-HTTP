package iut.rt.hachettp;
/**
 * Cette classe represente une exception d'une mauvaise requete HTTP.
 * 
 * @author Lienard Tristan
 * @date 2017
 */

public class HTTPBadRequestException extends Exception {

	/**
	 * Constructeur par default.
	 * 
	 * @param msg le message d'erreur de l'exception.
	 */
	public HTTPBadRequestException(String msg){
		super(msg);
	}
}

package iut.rt.hachettp;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
* Cette classe abstraite qui definis pour toute les reponses
* leurs methode et membre obligatoire.
* 
* @author Lienard Tristan
* @date 2017
*/

public abstract class Reponse {
	
	/**
	 * le message de la reponse
	 */
	protected String le_message;
	
	/**
	 * le code representant la reponse
	 */
	protected int le_code;
	
	/**
	 * le contenu de la page
	 */
	protected byte [] le_contenu;
	
	/**
	 * @return un string contenant l'entete de la reponse.
	 */
	public String toString() {
		String reponse = "HTTP/1.1 " + le_code + " " + le_message + "\n";
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
		reponse += "Date:" + sdf.format(date) + "\n";
		
		reponse += "Server: HacheTTP\n";
		reponse += "Content-Type: text/html; charset=utf-8\n";
		
		if(le_contenu != null){
			reponse += "Content-Length: " + le_contenu.length + "\n\n" + new String(le_contenu);
		}
		
		return reponse;
	}

	/**
	 * @return le contenu de la reponse.
	 */
	public byte[] getContenu() {
		return le_contenu;
	}
}

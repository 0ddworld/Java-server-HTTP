package iut.rt.hachettp;
/**
 * Cette classe est une reponse d'erreur de fichier non trouve.
 * 
 * @author Lienard Tristan
 * @date 2017
 */

public class Reponse404 extends Reponse {
	
	/**
	 * Constructeur unique qui initialise le contenu avec une phrase contenu l'url.
	 * @param url l'url du fichier non trouvee.
	 */
	public Reponse404(String url){
		le_message = "NOT FOUND";
		le_code = 404;
		le_contenu = new byte[5000];
		
		String contenu = "le fichier " + url + " n'existe pas !";
		le_contenu = contenu.getBytes();
	}
}

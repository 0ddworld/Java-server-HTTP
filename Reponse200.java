package iut.rt.hachettp;
/**
 * Cette classe est une reponse OK.
 * 
 * @author Lienard Tristan
 * @date 2017
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Reponse200 extends Reponse {
	
	/**
	 * Constructeur unique reçois un fichier en entrée et en stock le contenu.
	 * @param fichier le fichier qui va être lu et dont le contenu va être stocker.
	 * @throws InternalServerErrorException Lors d'une erreur de lecture inconue.
	 * @throws FileNotFoundException lorsque le fichier n'est pas trouvee.
	 */
	public Reponse200(File fichier) throws InternalServerErrorException, FileNotFoundException{
		le_message = "OK";
		le_code = 200;
		le_contenu = new byte[(int) fichier.length()]; //Initialise le_contenu avec la taille du fichier
		
		FileInputStream input = new FileInputStream(fichier);
		
		try { //Stocke le contenu du fichier dans le_contenu
			input.read(le_contenu);
			input.close();
		} catch (IOException e) {
			throw new InternalServerErrorException("Erreur lors de la lecture de" + fichier.getAbsolutePath());
		}
	}
}

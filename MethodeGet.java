package iut.rt.hachettp;
/**
* Cette classe applique la methode Get du protocole HTTP.
* Elle analyse et traite donc une requete Get.
* 
* @author Lienard Tristan
* @date 2017
*/

import java.util.regex.Pattern;
import java.io.File;
import java.io.FileNotFoundException;

public class MethodeGet extends Methode {
	/**
	 * l'url de la requete Get.
	 */
	private String l_url;
	
	/**
	 * l'hote de la requete Get.
	 */
	private String l_hote;
	
	/**
	 * l'agent de la requete Get.
	 */
	private String l_agent;
	
	/**
	 * Constructeur par default.
	 * @param la requete ligne par ligne.
	 * @throws HTTPVersionNotSupportedException si la version HTTP n'est pas 1.0 ou 1.1.
	 * @throws HTTPBadRequestException en cas de champ d'entete non reconnu.
	 */
	public MethodeGet(String[] requete) throws HTTPBadRequestException, HTTPVersionNotSupportedException {
		analyse(requete);
	}
	
	/**
	 * analyse la requete ligne par ligne pour en extraire l'url,l'hote et l'agent.
	 * @param c la requete ligne par ligne.
	 * @throws HTTPBadRequestException en cas de champ d'entete non reconnu.
	 * @throws HTTPVersionNotSupportedException si la version HTTP n'est pas 1.0 ou 1.1.
	 */
	private void analyse(String[] c) throws HTTPBadRequestException, HTTPVersionNotSupportedException {
		String l; //ligne
		String champ; //le type de valeur la ligne
		String valeur; //la valeur de la ligne
		
		analyse_ligne_1(c[0]); //on analyse la ligne 1 meme si il n'y a pas de champ d'entete
		
		if(c.length < 3){ //la taille minimal d'une requete GET dans notre cas
			throw new HTTPBadRequestException("Champ d'entete de la requete non reconnu !");
		}
		
		for(int i = 1; i < c.length; i++){//lecture du tableau de string a partir de la seconde ligne
			l = c[i];
			if(l != ""){
				if(Pattern.matches("^[-a-zA-Z]+: *.+ *$", l)){//l correspond au pattern choisis
					champ = l.split(":")[0];//on decoupe et recupere le champ
					valeur = l.split(": ")[1];//idem pour la valeur
					
					if(champ.equals("Host")){//si le champ correspond a l'hote alors on recupere l'hote
						 l_hote = valeur.split(":")[0];
						 System.out.println(l_hote);
					} else if(champ.equals("User-Agent")){//idem pour l'agent
						l_agent = valeur;
						System.out.println(l_agent);
					}
				}
			}
		}
		if(l_hote == null || l_agent == null){	//Cas ou aucun champ d'entete ne convient
			throw new HTTPBadRequestException("Champ d'entete de la requete non reconnu !");
		}
	}
	
	/**
	 * analyse la 1 ligne de la requete pour en extraire l'url.
	 * @param l la 1 ligne de la requete.
	 * @throws HTTPBadRequestException si la ligne n'est pas conforme a une requete GET.
	 * @throws HTTPVersionNotSupportedException si la version HTTP n'est pas 1.0 ou 1.1.
	 */
	private void analyse_ligne_1(String l) throws HTTPBadRequestException, HTTPVersionNotSupportedException {
		String mot;
		
		if(l.split(" ").length < 3){ //Erreur nombre insufisant d'argument pour une requete GET
			throw new HTTPBadRequestException("1ere ligne de la requete vide ou incomplete !");
		}
		
		//on test chaque mot de la ligne
		mot = l.split(" ")[0];
		if(mot == "" || !mot.equals("GET")){ //Erreur de type de requete
			throw new HTTPBadRequestException("Erreur requete GET attendu !");
		}
		
		mot = l.split(" ")[1];
		if(mot == "" || mot == null){ //Erreur rien apres GET
			throw new HTTPBadRequestException("Erreur rien apres le GET !");
		}
		l_url = mot;
		
		mot = l.split(" ")[2];
		if(mot == "" || mot == null){ //Erreur rien apres l'URL
			throw new HTTPBadRequestException("Erreur rien apres l'URL !");
		}
		if((mot.split("/")[1].equals("1.0")) || (mot.split("/")[1].equals("1.1"))){
			
		} else{ //Erreur si version differente de 1.0 ou 1.1
			throw new HTTPVersionNotSupportedException("Erreur de version HTTP");
		}
	}

	/**
	 * lit le dossier ou le fichier de l'url a la recherche et la page demandee.
	 * @return la reponse avec la page a afficher.
	 */
	@Override
	public Reponse traiter() {
		Reponse reponse;
		File fichier_servi;
		
		fichier_servi = new File(Serveur.RACINE_SERVIE,l_url);
		
		if(!fichier_servi.exists()){
			reponse = new Reponse404(l_url);
		} else if(fichier_servi.isDirectory()){
			fichier_servi = new File(fichier_servi,Serveur.FICHIER_PAR_DEFAULT);
		}
		if(fichier_servi.exists()){
			try {
				reponse = new Reponse200(fichier_servi);
			} catch (FileNotFoundException e) {
				reponse = new Reponse404(l_url);
			} catch (InternalServerErrorException e) {
				reponse = new Reponse500();
			}
		} else{
			reponse = new Reponse404(l_url);
		}
		return reponse;
	}

}

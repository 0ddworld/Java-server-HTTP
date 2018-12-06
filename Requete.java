package iut.rt.hachettp;
/**
* Cette classe contient le contenue d'une requete et definis
* si celle-ci est prete.
* 
* @author Lienard Tristan
* @date 2017
*/

import java.util.ArrayList;

public class Requete {
	/**
	 * Le type de la methode.
	 */
	private Methode la_methode;
	
	/**
	 * Le vecteur contenant les lignes de la requete.
	 */
	private ArrayList<String> la_requete;
	
	/**
	 * Indique si la requete est prete a etre traite.
	 */
	private boolean est_prete;
	
	/**
	 * la reponse du traitement de la requete
	 */
	private Reponse la_reponse;
	
	
	/**
	 * Constructeur par default.
	 */
	public Requete(){
		la_requete = new ArrayList<String>();
		est_prete = false;
	}
	
	/**
	 * Ajoute une ligne au vecteur la_requete.
	 * @param l la ligne a ajouter.
	 */
	public void ajouteLigne(String l){
		if(l != null){//si la ligne n'est pas vide on l'ajoute et la requete n'est pas prete.
			la_requete.add(l);
		}else{//sinon on determine la methode et on annonce que la requete est prete.
			determine_methode();
			est_prete = true;
		}
	}
	
	/**
	 * informe sur l'etat de la requete.
	 * @return vrai si la requete est prete a etre traitee.
	 */
	public boolean estPrete(){
		return est_prete;
	}
	
	/**
	 * Determine la methode de la requete en analysant la 1ere ligne de celle-ci.
	 */
	private void determine_methode() {
		
		if(!la_requete.isEmpty() && la_requete.get(0).split(" ")[0].equals("GET")){//verifie le 1er mot de la 1ere ligne de la requete
			try {
				la_methode = new MethodeGet(la_requete.toArray(new String[0]));
				la_reponse = la_methode.traiter(); //reponse apres traitement	
			} catch (HTTPBadRequestException e) {
				la_reponse = new Reponse400(); //reponse Erreur de requete
			} catch (HTTPVersionNotSupportedException e) {
				la_reponse = new Reponse505(); //reponse Erreur de version HTTP
			}
		} else{
			la_reponse = new Reponse405(); //reponse Erreur de methode, ici pas une methode GET
		}
	}
	
	/**
	 * @return la reponse du traitement de la requete
	 */
	public Reponse getReponse(){
		return la_reponse;
	}
	
	/**
	 * traite la requete en fonction de la methode et stocke la reponse.
	 */
	public void traiter(){
		if(la_methode != null){
			la_reponse = la_methode.traiter();
		}
	}
}

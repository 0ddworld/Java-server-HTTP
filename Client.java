package iut.rt.hachettp;
/**
 * Cette classe lit la requete envoye par le socket qui a ete passe au constructeur,
 * declanche son traitement et ecris la reponse (positive ou negative).
 * 
 * @author Lienard Tristan
 * @date 2017
 */
import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Client {
	/**
	 * connection au client.
	 */
	private Socket le_socket;
	
	/**
	 * la requete emise par le socket(le client)
	 */
	private Requete la_requete;
	
	/**
	 * membre permettant de lire ou d'ecrire sur le socket.
	 */
	private BufferedReader l_entree;
	private BufferedWriter la_sortie;
	
	/**
	 * Constructeur par default.
	 * @param in_s le socket a lire et traiter.
	 * @throws HTTPException si un quelquonque probleme se produit avec le Socket.
	 */
	public Client(Socket in_s) throws HTTPException{
		le_socket = in_s;
		la_requete = new Requete();
		try {
			l_entree = new BufferedReader(new InputStreamReader(le_socket.getInputStream(), "UTF-8"));
		} catch (IOException e) {
			throw new HTTPException("Acces en lecture au socket impossible");
		}
		try {
			la_sortie = new BufferedWriter(new OutputStreamWriter(le_socket.getOutputStream(),"UTF-8"));
		} catch (IOException e) {
			throw new HTTPException("Acces en ecriture au socket impossible");
		}
	}
	/**
	 * lit la requete envoye par le client et y repond si elle est valide.
	 * @throws HTTPException si un quelquonque probleme se produit avec le Socket.
	 */
	public void traiter() throws HTTPException{
		lecture_requete();
		la_requete.traiter();
		envoi(la_requete.getReponse());
		liberation();
	}
	
	/**
	 * lit et enregistre la requete ligne par ligne.
	 * @throws HTTPException si un quelquonque probleme se produit avec le Socket.
	 */
	private void lecture_requete() throws HTTPException{
		String ligne;
		while(!la_requete.estPrete()){//Tant que la requete n'est pas entierement lue on continue.
			try {
				ligne = l_entree.readLine();
				if(ligne.getBytes().length == 0){
					ligne = null;
				}
				la_requete.ajouteLigne(ligne);
			} catch (IOException e) {
				throw new HTTPException("Erreur lors de la lecture du socket\n" + e.getMessage());
			}
		}
	}
	
	/**
	 * Libere le socket en fermant la connection.
	 */
	public void liberation(){
		try {
			le_socket.close();
		} catch (IOException e) {
			
		}
	}
	
	/**
	 * Envoie une reponse sur le socket.
	 * @param in_rep la reponse a envoyer.
	 */
	private void envoi(Reponse in_rep){
		try {
			la_sortie.write(in_rep.toString());
			la_sortie.flush();
		} catch (IOException e) {
			System.out.println("erreur lors de l'écriture sur le socket");
		}
	}
}


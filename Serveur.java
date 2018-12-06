package iut.rt.hachettp;
/**
 * Cette classe contient la boucle principale du serveur.
 * Elle attend une connexion TCP sur le port qui a ete passe au constructeur,
 * la traite, puis se remet en attente d'une nouvelle connection TCP.
 * 
 * @author Lienard Tristan
 * @date 2017
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
	/**
	 * le port d'ecoute du serveur.
	 */
	private int le_port;
	
	/**
	 * represente la racine servie du serveur.
	 */
	public static final String RACINE_SERVIE = "/var/www";
	
	/**
	 * represente la page par default a charger.
	 */
	public static final String FICHIER_PAR_DEFAULT = "index.html";
	
	/**
	 * Constructeur par default.
	 * 
	 * @param in_port le port d'ecoute du serveur.
	 */
	public Serveur(int in_port){
		le_port = in_port;
	}
	
	/**
	 * Initialise le serveur et demarre la boucle principale.
	 */
	public void lance(){
		
		ServerSocket serveur; //le serveur qui ecoutera sur le_port
		Socket socket; //le socket entrant sur le serveur
		
		try { //initialisation du serveur avec le_port
			serveur = new ServerSocket(le_port);
		} catch (IOException e) {
			System.out.println("Erreur lors de la création de l'objet ServerSocket !");
			return;
		}
		while(true){ //boucle du serveur
			try { //lit les connections au serveur et si connection, on la traite par le biais de l'objet client
				socket = serveur.accept();
				if(socket.isConnected()){
					
					Client client = new Client(socket);
					client.traiter();
					socket.close();
				}
			} catch (IOException e) {
				System.out.println("Erreur lors de l'attente d'une connection entrante !");
			} catch(HTTPException e){
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * main du programme.
	 * @param args les arguments en entree (inutilise ici).
	 */
	public static void main(String[] args){
		Serveur srv = new Serveur(8080);
		srv.lance();
	}
}

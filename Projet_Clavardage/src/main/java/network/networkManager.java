package network;

public class networkManager {
	
	// Format des messages en broadcast 
	/* flag de connexion/deconnexion
	 * adresse ip de celui qui se connecte ou se déconnecte 
	 * pseudo  
	 * 
	 * <!> REPONSE EN TCP
	 * 
	 * Pour le broadcast du speudo lors de la connexion, seul l'utilisateur qui 
	 * a déjà le pseudo que l'on veut peut répondre NON -> on met en place en timer
	 * donc si on n'a pas de réponse on dit que c'est OK
	 */

	// Fonctions possibles:
	/*
	 * - Traitement des des paquets broadcasts
	 * 
	 * - Communication avec l'<interface table>
	 * 
	 * - Broadcast de connexion username (timer a part?)
	 * 
	 * - Broadcast de deconnexion
	 * 
	 * - Reponse des paquets Broadcast
	 * 
	 * - Traitement des paquets TCP aka message users
	 * */
	
}

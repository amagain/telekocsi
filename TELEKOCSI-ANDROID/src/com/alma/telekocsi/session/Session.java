/**
 * 
 */
package com.alma.telekocsi.session;

import java.util.List;

import com.alma.telekocsi.dao.itineraire.Itineraire;
import com.alma.telekocsi.dao.profil.Profil;
import com.alma.telekocsi.dao.trajet.Trajet;
import com.alma.telekocsi.dao.trajet.TrajetLigne;

/**
 * @author Rv
 * Contenir les informations relatifs a une session
 * d'utilisation et fournir un point d'entrer pour la notification.
 */
public interface Session {
	
	public final int OK = 0;
	
	/**
	 * Message d'erreur si pas de trajet activé
	 */
	public final int NO_ACTIVE_TRAJET = 1;
	
	/**
	 * Message d'erreur si pas de profil activé
	 */
	public final int NO_ACTIVE_PROFILE = 2;
	
	/**
	 * Message d'erreur inattendue
	 */
	public final int ERROR = 3;
	
	/**
	 * Acceder au profil courant.
	 * On verifie que le profil n'a pas encore ete cree, si oui on le charge
	 * @return Le profil actif et <code>null</code> si non existant
	 */
	public abstract Profil getActiveProfile();
	
	/**
	 * Remplacer ou creer le profil actif
	 * @param profile Le nouveau profil
	 */
	public abstract void saveProfile(Profil profile);

	/**
	 * 
	 * @return Le traject actif ou null si pas de trajet
	 */
	public abstract Trajet getActiveTrajet();
	
	/**
	 * Active un trajet
	 * @param trajet
	 */
	public abstract void activateTrajet(Trajet trajet);
	
	/**
	 * Désactive le trajet actif
	 * Une fois appeler la méthode <code>getActiveTrajet()</code> retourne <code>null</code>,
	 * le trajet qui était actif passe dans l'etat termine (Trajet.ETAT_FIN) .
	 */
	public abstract void deactivateTrajet();
	
	/**
	 * Activer une ligne pour le trajet actif et le passager considéré
	 * Le profil courant doit etre de type conducteur.
	 * Le trajetLigne est rechercher dans la base à partir du trajet actif et de l'Id du passager
	 * Si trouve => Modification sinon creation
	 * @param idPassenger L'Id du passager
	 * @param placesCount Nombre de places
	 * @param nombre de points negocies
	 * @return <ul>
	 * 	<li><code>Session.NO_ACTIVE_TRAJET</code>: Si pas de trajet actif</li>
	 *  <li><code>Session.NO_ACTIVE_PROFILE</code>: Si pas de profil actif</li>
	 *  <li><code>Session.OK</code>: En cas d'activation réussie</li>
	 *  <li><code>Session.ERROR</code>: En cas d'échec d'activation</li>
	 * </ul>
	 */
	public abstract int activeTrajetLineFor(String idPassenger, int placesCount, int point);
	
	/**
	 * Déactiver le trajetLigne pur le passager actif
	 * Si le profil courant est un conducteur le trajetLigne est retiré de la base
	 * Sinon le trajetLigne est simplement retiré du cache local
	 * @param idPassender
	 * @return <ul>
	 *  <li><code>Session.NO_ACTIVE_PROFILE</code>: Si pas de trajet activé</li>
	 *  <li><code>Session.OK</code>: En cas désactivation réussie</li>
	 *  <li><code>Session.ERROR</code>: En cas d'échec d'activation</li>
	 * </ul>
	 */
	public abstract int deactivateTrajetLineFor(String idPassenger);
	
	/**
	 * Retourner les profils de tous les passagers des trajetLignes actifs
	 * @return
	 */
	public abstract Profil[] getActivePassengersProfiles();
	
	/**
	 * Change le type du profil courant comme passager.
	 * A noter que le type du profil n'est pas sauvé en base.
	 */
	public abstract void switchToPassengerType();
	
	/**
	 * Rechercher une ligne active pour le passager 
	 * @param idPassenger
	 * @return
	 */
	public abstract TrajetLigne getActiveTrajetLineFor(String idPassenger);
	
	/**
	 * Change le type du profil courant comme conducteur.
	 * A noter que le type du profil n'est pas sauvé en base.
	 */
	public abstract void switchToDriverType();
	
	/**
	 * 
	 * @return <code>false</code> Si l'utilisateur c'est deconnecte
	 */
	public abstract boolean isConnected();
	
	/**
	 * Se connecter e un profil
	 * Si les identifiants sont valide, le profil devient le profil actif
	 * @return <code>true</code> Si succes
	 */
	public abstract boolean login(String name,String password);
	
	/**
	 * 
	 * @return Deconnecter le profil actif
	 */
	public abstract boolean logout();
	
	/**
	 * Ecouter les l'evenement sur la session
	 * @param listener
	 */
	public abstract void addSessionListener(SessionListener listener);
	
	/**
	 *  Arreter l'ecoute
	 * @param listener
	 */
	public abstract void removeSessionListener(SessionListener listener);
	
   
    /**
     * Correspond a l'appel de TDAO.insert(object)
     * @param <T> Avis,Itineraire, Event,Transaction,Trajet ou Localisation
     * @param object L'instance
     */
    public abstract<T> T save(T object); 
    
    /**
     * 
     * @param <T>
     * @param type
     * @param id
     * @return
     */
    public abstract<T> T find(Class<T> type,String id);
    
    /**
     * 
     * @param <T> Avis,Itineraire, Transaction,Trajet, Event ou Localisation
     * @param object L'instance
     */
    public abstract<T> T update(T object);

    /**
     * 
     * @param <T> Avis,Itineraire, Transaction,Trajet Event ou Localisation
     * @param object L'instance
     */
    public abstract<T> void delete(T object);
    
    /**
     * Effacer toutes les donnees d'un type donne
     * @param type Un des types de la DAO
     */
    public abstract<T> void clear(Class<T> type);

    /**
     * De meme que clear mais pour un profil particulier
     * @param <T>
     * @param type
     * @param profilId
     */
    public abstract<T> void clear(Class<T> type,String profilId);
    
    /**
     * 
     * @return Tous les trajets (actif + dispo) du profil actif
     */
    public abstract List<Trajet> getTrajets();
    
    /**
     * 
     * @return Les itineraires du profil actif
     */
    public abstract List<Itineraire> getItineraires();
    
    
    /**
     * Recherche les trajets actifs ou disponibles correspondants au modele transmis.
     * Les trajets selectionnes doivent avoir des places disponibles.
     * 
     * Elements devant etre renseignes dans le modele :
     * - lieuDepart
     * - lieuDestination
     * - dateTrajet
     * 
     * @param le modele permettant d'identifier les criteres de recherche
     * @return Les trajets actifs sur la base du modele transmis
     */
    public abstract List<Trajet> getTrajets(Trajet trajetModel);
	
}

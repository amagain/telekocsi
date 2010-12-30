/**
 * 
 */
package com.alma.telekocsi.session;

import com.alma.telekocsi.dao.profil.Profil;

/**
 * @author Rv
 * Contenir les informations relatifs a une session
 * d'utilisation et fournir un point d'entrer pour la notification.
 */
public interface Session {
	public int TRAJET = 0;
	public int EVENT = 1;
	public int LOCALISATION = 2;
	public int TRANSACTION = 3;
	public int AVIS = 4;
	public int PROFIL = 5;
	
	/**
	 * Acc�der au profil courant.
	 * On v�rifie que le profil n'a pas encore �t� cr�e, si oui on le charge
	 * @return Le profil actif et <code>null</code> si non existant
	 */
	public abstract Profil getActiveProfile();
	
	/**
	 * Remplacer ou cr�er le profil actif
	 * @param profile Le nouveau profil
	 */
	public abstract void saveProfile(Profil profile);

	/**
	 * 
	 * @return <code>false</code> Si l'utilisateur c'est d�connect�
	 */
	public abstract boolean isConnected();
	
	/**
	 * Se connecter � un profil
	 * Si les identifiants sont valide, le profil devient le profil actif
	 * @return <code>true</code> Si succ�s
	 */
	public abstract boolean login(String name,String password);
	
	/**
	 * 
	 * @return Deconnecter le profil actif
	 */
	public abstract boolean logout();
	
	/**
	 * Ecouter les l'�v�nement sur la session
	 * @param listener
	 */
	public abstract void addSessionListener(SessionListener listener);
	
	/**
	 *  Arr�ter l'�coute
	 * @param listener
	 */
	public abstract void removeSessionListener(SessionListener listener);
	
   
    /**
     * Correspond a l'appel de TDAO.insert(object)
     * @param <T> Avis,Itineraire, Event,Transaction,Trajet ou Localisation
     * @param object L'instance
     */
    public abstract<T> void save(T object); 
    
    /**
     * 
     * @param <T> Avis,Itineraire, Transaction,Trajet, Event ou Localisation
     * @param object L'instance
     */
    public abstract<T> void update(T object);

    /**
     * 
     * @param <T> Avis,Itineraire, Transaction,Trajet Event ou Localisation
     * @param object L'instance
     */
    public abstract<T> void delete(T object);
    
    /**
     * Effacer toutes les donn�es d'un type donn�
     * @param type Session.AVIS, Session.TRANSACTION, Session.PROFIL, Session.EVENT, Session.LOCALISATION
     */
    public abstract void clear(int type);

}

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
	 * Ecouter les l'�v�nement sur la session
	 * @param listener
	 */
	public abstract void addSessionListener(SessionListener listener);
	
	/**
	 *  Arr�ter l'�coute
	 * @param listener
	 */
	public abstract void removeSessionListener(SessionListener listener);
}

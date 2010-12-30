package com.alma.telekocsi.session;

/**
 * 
 * @author Rv
 * Base des evenements pour ceux qui veulent �tre notifi�
 *
 */
public interface SessionEvent {
	/**
	 * Type d'�venement
	 */
	public final int TRAJET_ACTIVATED = 0;
	/**
	 * Evenement emis � la deconnection
	 */
	public final int LOGOUT = 1;
	
	/**
	 * Le type d'�v�nement
	 * @return
	 */
	public abstract int getEventType();
	
	/**
	 * L' origine de l'evenement
	 * @return
	 */
	public abstract Object getSource();
	
	/**
	 *  Les donn�es
	 * @return
	 */
	public abstract Object getUserData();
	
}

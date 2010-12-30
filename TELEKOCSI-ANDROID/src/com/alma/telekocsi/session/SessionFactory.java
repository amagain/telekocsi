package com.alma.telekocsi.session;

public class SessionFactory {
	private static Session theSession;
	
	/**
	 *  Point d'acc�s � la session courante
	 *  Si elle n'existe pas encore elle est cr�e
	 * @return La session courante
	 */
	public static Session getCurrentSession(){
		if(theSession==null){
			theSession = new SessionImpl();
		}
		return theSession;
	}
	
}

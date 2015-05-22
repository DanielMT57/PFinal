package com.mycompany.sessionbeans;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class ServiceLocator {
	
	/**
	 * Metodo para ubicar interfaz remota
	 * @author Daniel Moncada Tabares <danmt57000@hotmail.com>
	 * @param nombreEAR, nombre del proyecto
	 * @param nombreModulo, nombre del proyecto EJB
	 * @param nombreEJB, nombre del session bean
	 * @param nombreInterfaz, nombre completo interfaz remota
	 * @return Objeto remoto
	 * @throws NamingException
	 * Fecha de creaci�n: 10/08/2014
	 */
	public static Object buscarEJB(String nombreEAR, String nombreModulo, String nombreEJB, String nombreInterfaz) throws NamingException{
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES,
				"org.");
		final Context context = new InitialContext(jndiProperties); //conexion servidor aplicaciones		
			return  context
					.lookup("ejb:"+nombreEAR+ "/" + nombreModulo+"/"+nombreEJB+"!"+nombreInterfaz);
	}
}

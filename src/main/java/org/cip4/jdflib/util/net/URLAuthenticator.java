/**
 * 
 */
package org.cip4.jdflib.util.net;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

class URLAuthenticator extends Authenticator
{
	/**
	 * @param user
	 * @param password
	 */
	protected URLAuthenticator(String user, String password)
	{
		super();
		this.user = user;
		this.password = password;
	}

	private final String user;
	private final String password;

	@Override
	protected PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication(user, password.toCharArray());
	}

}
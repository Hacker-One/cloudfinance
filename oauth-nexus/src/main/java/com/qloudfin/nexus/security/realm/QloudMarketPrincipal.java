package com.qloudfin.nexus.security.realm;

import java.io.Serializable;
import java.util.Set;

public final class QloudMarketPrincipal implements Serializable {
	/**
	 *
	 */
	private String username = null;
	/**
	 *
	 */
	private String userperm = null;
	/**
	 *
	 */
	private Set<String> userrole = null;
	
	/**
	 *
	 */
	public QloudMarketPrincipal() {
	}
	
	/**
	 *
	 * @param username
	 * @return QloudMarketPrincipal
	 */
	public QloudMarketPrincipal setUsername(String username) {
		this.username = username;
		return this;
	}
	
	/**
	 *
	 * @return String
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 *
	 * @param userperm
	 * @return QloudMarketPrincipal
	 */
	public QloudMarketPrincipal setUserperm(String userperm) {
		this.userperm = userperm;
		return this;
	}
	
	/**
	 *
	 * @return String
	 */
	public String getUserperm() {
		return userperm;
	}
	
	/**
	 *
	 * @param userrole
	 * @return QloudMarketPrincipal
	 */
	public QloudMarketPrincipal setUserrole(Set<String> userrole) {
		this.userrole = userrole;
		return this;
	}
	
	/**
	 *
	 * @return Set<String>
	 */
	public Set<String> getUserrole() {
		return userrole;
	}
	
	/**
	 *
	 */
	@Override
	public String toString() {
		return username;
	}
}
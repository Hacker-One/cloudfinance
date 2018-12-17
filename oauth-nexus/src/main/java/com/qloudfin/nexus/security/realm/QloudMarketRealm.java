package com.qloudfin.nexus.security.realm;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.apache.shiro.SecurityUtils;

import org.eclipse.sisu.Description;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sonatype.nexus.security.authc.HttpHeaderAuthenticationToken;

import io.advantageous.boon.json.JsonFactory;

@Named( QloudMarketRealm.ID )
@Description( QloudMarketRealm.DESCRIPTION )
@Singleton
public class QloudMarketRealm extends AuthorizingRealm {
	/**
	 *
	 */
	private static final Logger logger = LoggerFactory.getLogger( QloudMarketRealm.class );
	/**
	 *
	 */
	public static final String ID = "rutauth-qloudmarket-realm";
	/**
	 *
	 */
	public static final String DESCRIPTION = "QloudMarket Security Realm";
	/**
	 *
	 */
	private QloudMarketConfig configuration;
	
	/**
	 *
	 */
	@Inject
	public QloudMarketRealm(QloudMarketConfig configuration) {
		this.configuration = configuration;
		setName( ID );
		setCredentialsMatcher( new CredentialsMatcher() {
				@Override
				public boolean doCredentialsMatch(final AuthenticationToken token, final AuthenticationInfo info) {
					return true;
				}
			}
		);
	}
	
	/**
	 *
	 */
	@Override
	public boolean supports(final AuthenticationToken token) {
		return ( token instanceof HttpHeaderAuthenticationToken );
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken token) throws AuthenticationException {
		final String t = token.getPrincipal().toString();
		logger.debug( "Starting qloudmarket authenticate for token:{}", t );
		if ( t == null || "".equals( t ) ) {
			throw new AuthenticationException( "Current qloudmarket authenticate token is null..." );
		}
		//
		if ( Boolean.parseBoolean( configuration.getFaked() ) ) {
			return createSimpleAuthcInfo( new QloudMarketPrincipal()
				.setUsername( t )
				.setUserperm( null )
				.setUserrole( null ) , null );
		}
		//
		try {
			Map<String,String> headers = new HashMap<String,String>();
			headers.put( "Authorization" , "Bearer " + t );
			//
			final HTTP.Response response = HTTP.jsonRestCallWithHeaders( configuration.getAuthentication() , headers );
			if ( response.code() != 200 ) {
				throw new RuntimeException( "Current retrieve qloudmarket token info failed..." + response.code() );
			}
			//
			logger.debug( "Current qloudmarket authenticate for user:\n{}", response.body() );
			//
			Map<String,Object> info = JsonFactory.fromJson( response.body() , Map.class );
			if ( info.get( "error" ) != null ) {
				throw new RuntimeException( "Current retrieve qloudmarket token info error..." );
			}
			//
			Map<String,Object> data = ( Map )info.get( "data" );
			final QloudMarketPrincipal principal = new QloudMarketPrincipal()
				.setUsername( ( String )data.get( "username" ) )
				.setUserperm( null )
				.setUserrole( null );
			//
			return createSimpleAuthcInfo( principal , null );
		}
		catch (Exception e) {
			logger.error( "Current qloudmarket authenticate token failed...\n" , e );
			return null;
		}
	}
	
	/**
	 * Creates the simple authentication info.
	 *
	 * @param token
	 * @return AuthenticationInfo
	 */
	private AuthenticationInfo createSimpleAuthcInfo(Object principal, Object credentials) {
		return new SimpleAuthenticationInfo( principal , credentials , getName() );
		/**
		 * Redirect to nexus realm but still need create user/role/permission.
		 */
		//return new SimpleAuthenticationInfo( principal , credentials , configuration.getRealms() );
	}
	
	/**
	 * nexus:repository-view:docker:docker-mock:read
	 * http://localhost:8081/repository/docker-mock/v2/hello-world/manifests/latest
	 *
	 * nexus:repository-view:docker:docker-mock:browse
	 * http://localhost:8081/service/rest/v1/assets?repository=docker-mock&name=hello-world&version=latest
	 */
	private boolean doGetAuthorizationInfo(String username , Permission permission) {
		final Subject subject = SecurityUtils.getSubject();
		final HttpServletRequest request = WebUtils.getHttpRequest( subject );
		final HttpServletResponse response = WebUtils.getHttpResponse( subject );
		if ( subject == null || request == null || response == null ) {
			return false;
		}
		//
		logger.debug( "Current qloudmarket authorize context is...{}:::{}:::{}" , username , permission , request.getRequestURL().toString() );
		final String origin = request.getRequestURL().toString();
		final String[] p = permission.toString().split( ":" );
		//
		final String action = p[4];
		final String repository = p[3];
		final String type = p[2];
		//
		final String image;
		if ( "docker".equals( type ) ) {
			if ( origin.indexOf( "/v2" ) > 0 ) {
				final String str = origin.substring( origin.indexOf( "/v2" ) );
				final String[] t = str.split( "/" );
				image = t[1];
			}
			else {
				image = "";
			}
		}
		else {
			// request.getQueryString()
			image = "";
		}
		logger.debug( "Current qloudmarket authorize context is...{}:::{}:::{}" , username , action , repository + "/" + image );
		//
		if ( Boolean.parseBoolean( configuration.getFaked() ) ) {
			return true;
		}
		else {
			try {
				final StringBuilder params = new StringBuilder();
				params.append( "?userid=" ).append( username )
					.append( "&action=" ).append( action )
					.append( "&path=" ).append( repository + "/" + image );
				//
				final HTTP.Response resp = HTTP.jsonRestCall( configuration.getAuthorization() + params.toString() );
				if ( resp.code() != 200 ) {
					throw new RuntimeException( "Current retrieve qloudmarket permission info failed..." + resp.code() );
				}
				logger.debug( "Current qloudmarket authorize for permission:{}", resp.body() );
				//
				Map<String,Object> info = JsonFactory.fromJson( resp.body() , Map.class );
				if ( ! ( Boolean )info.get( "status" ) ) {
					return false;
				}
				else {
					return true;
				}
			}
			catch (Exception e) {
				logger.error( "Current qloudmarket authorize permission failed...\n" , e );
				return false;
			}
		}
	}
	
	/**
	 *
	 */
	@Override
	public boolean isPermitted(PrincipalCollection principals, Permission permission) {
		if ( principals.getPrimaryPrincipal() instanceof QloudMarketPrincipal ) {
			logger.debug( "Starting qloudmarket authorize isPermitted 1 for user:{},permission:{}", principals.getPrimaryPrincipal() , permission );
			return doGetAuthorizationInfo( principals.getPrimaryPrincipal().toString() , permission );
		}
		else {
			logger.debug( "Current qloudmarket authorize isPermitted is skipped..." );
			return false;
		}
	}
	
	/**
	 *
	 */
	@Override
	public boolean[] isPermitted(PrincipalCollection principals, List<Permission> permissions) {
		if ( principals.getPrimaryPrincipal() instanceof QloudMarketPrincipal ) {
			logger.debug( "Starting qloudmarket authorize isPermitted * for user:{},permission:{}", principals.getPrimaryPrincipal() , permissions );
			boolean[] result;
			if ( permissions != null && ! permissions.isEmpty() ) {
				result = new boolean[ permissions.size() ];
				for ( int i=0 ; i<permissions.size() ; i++ ) {
					result[i] = doGetAuthorizationInfo( principals.getPrimaryPrincipal().toString() , permissions.get(i) );
				}
			}
			else {
				result = new boolean[0];
			}
			return result;
		}
		else {
			logger.debug( "Current qloudmarket authorize isPermitted is skipped..." );
			return new boolean[0];
		}
	}
	
	/**
	 *
	 */
	@Override
	public boolean isPermittedAll(PrincipalCollection principals, Collection<Permission> permissions) {
		if ( principals.getPrimaryPrincipal() instanceof QloudMarketPrincipal ) {
			logger.debug( "Starting qloudmarket authorize isPermittedAll for user:{},permissions:{}", principals.getPrimaryPrincipal() , permissions );
			if ( permissions != null && ! permissions.isEmpty() ) {
				for ( Permission p : permissions ) {
					if ( ! doGetAuthorizationInfo( principals.getPrimaryPrincipal().toString() , p ) ) {
						return false;
					}
				}
			}
			return true;
		}
		else {
			logger.debug( "Current qloudmarket authorize isPermittedAll is skipped..." );
			return false;
		}
	}
	
	/**
	 *
	 */
	@Override
	public void checkPermission(PrincipalCollection principals, Permission permission) throws AuthorizationException {
		if ( principals.getPrimaryPrincipal() instanceof QloudMarketPrincipal ) {
			logger.debug( "Starting qloudmarket authorize checkPermission for user:{},permission:{}", principals.getPrimaryPrincipal() , permission );
			if ( ! doGetAuthorizationInfo( principals.getPrimaryPrincipal().toString() , permission ) ) {
				throw new AuthorizationException( "Current qloudmarket authorize checkPermission failed..." );
			}
		}
		else {
			throw new AuthorizationException( "Current qloudmarket authorize checkPermission is skipped..." );
		}
	}
	
	/**
	 *
	 */
	@Override
	public void checkPermissions(PrincipalCollection principals, Collection<Permission> permissions) throws AuthorizationException {
		if ( principals.getPrimaryPrincipal() instanceof QloudMarketPrincipal ) {
			logger.debug( "Starting qloudmarket authorize checkPermissions for user:{},permissions:{}", principals.getPrimaryPrincipal() , permissions );
			if ( permissions != null && ! permissions.isEmpty() ) {
				for ( Permission p : permissions ) {
					if ( ! doGetAuthorizationInfo( principals.getPrimaryPrincipal().toString() , p ) ) {
						throw new AuthorizationException( "Current qloudmarket authorize checkPermissions failed..." );
					}
				}
			}
		}
		else {
			throw new AuthorizationException( "Current qloudmarket authorize checkPermissions is skipped..." );
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {
		if ( principals.getPrimaryPrincipal() instanceof QloudMarketPrincipal ) {
			final QloudMarketPrincipal principal = ( QloudMarketPrincipal )principals.getPrimaryPrincipal();
			logger.debug( "Starting qloudmarket authorize for user:{}", principal.getUsername() );
			return createSimpleAuthzInfo( principal.getUserrole() );
		}
		else {
			logger.debug( "Current qloudmarket authorize is skipped..." );
			return null;
		}
	}
	
	/**
	 * Creates the simple authorization info.
	 *
	 * @param token
	 * @return AuthorizationInfo
	 */
	private AuthorizationInfo createSimpleAuthzInfo(Set<String> roles) {
		return new SimpleAuthorizationInfo( roles );
	}
}
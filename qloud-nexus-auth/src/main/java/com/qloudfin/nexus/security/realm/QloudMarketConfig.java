package com.qloudfin.nexus.security.realm;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Arrays;
import java.util.Properties;
import java.util.Collections;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Singleton;

@Singleton
@Named
public class QloudMarketConfig {
	/**
	 *
	 */
	private static final Logger logger = LoggerFactory.getLogger( QloudMarketConfig.class );
    /**
	 *
	 */
	private static final String DEFAULT_CONFIG_FILE = "qloudmarket.properties";
	/**
	 *
	 */
	private static final Properties config = new Properties();
	
	/**
	 *
	 */
	public QloudMarketConfig() {
	}
	
	/**
	 *
	 */
	@PostConstruct
	public void init() {
		try {
			config.load( Files.newInputStream( Paths.get( "." , "etc" , DEFAULT_CONFIG_FILE ) ) );
		}
		catch (Exception e) {
			logger.error( "Current loading qloudmarket properties failed...\n" , e );
			throw new RuntimeException( e );
		}		//
		logger.info( "Current qloudmarket realm configuration is...{}" , config );
	}
	
	/**
	 *
	 * @return Set
	 */
	public Set<String> getRoles() {
		return new HashSet<String>( Arrays.asList( config.getProperty( "roles" , "admin" ).split( "," ) ) );
	}
	
	/**
	 *
	 * @return String
	 */
	public String getFaked() {
		return config.getProperty( "faked" );
	}
	
	/**
	 *
	 * @return String
	 */
	public String getAuthentication() {
		return config.getProperty( "authUrl" );
	}
	
	/**
	 *
	 * @return String
	 */
	public String getAuthorization() {
		return config.getProperty( "permissionUrl" );
	}
	
    /**
	 *
	 */
	@Override
	public String toString() {
		return config.toString();
	}
}
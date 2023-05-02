package es.teis.oracle.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import oracle.jdbc.datasource.impl.OracleDataSource;

public class ConnectionFactory {

	private static OracleDataSource ods= null;
	private static final String RUTA_FICHERO = "src/main/resources/db.properties";
	private static final String URL_KEY = "url";
	private static final String USER_KEY = "user";
	private static final String PWD_KEY ="pwd";
	
	private ConnectionFactory() {
		
	}
	
	public OracleDataSource getDataSource() {
		
		return ods;
		
	}
}

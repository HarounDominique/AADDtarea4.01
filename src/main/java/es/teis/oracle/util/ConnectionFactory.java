package es.teis.oracle.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import oracle.jdbc.datasource.impl.OracleDataSource;

public class ConnectionFactory {

	private static OracleDataSource ods = null;
	private static final String RUTA_FICHERO = "src/main/resources/db.properties";
	private static final String URL_KEY = "url";
	private static final String USER_KEY = "user";
	private static final String PWD_KEY = "pwd";

	private ConnectionFactory() {

	}

	public static OracleDataSource getDataSource() {
		if (ods == null) {

			try {
				Properties props = new Properties();
				FileInputStream fis = new FileInputStream(RUTA_FICHERO);
				props.load(fis);
				fis.close();

				String url = props.getProperty(URL_KEY);
				String user = props.getProperty(USER_KEY);
				String pwd = props.getProperty(PWD_KEY);

				ods = new OracleDataSource();
				ods.setURL(url);
				ods.setUser(user);
				ods.setPassword(pwd);

			} catch (FileNotFoundException e) {
				System.err.println("No se encontró el archivo: " + e.getMessage());
			} catch (IOException e) {
				System.err.println("No se pudo leer el archivo: " + e.getMessage());
			} catch (Exception e) {
				System.err.println("No se pudo crear la conexión: " + e.getMessage());
			}
		}
		return ods;
	}
}

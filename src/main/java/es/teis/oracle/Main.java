package es.teis.oracle;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import es.teis.oracle.modelo.JPersona;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.datasource.impl.OracleDataSource;

public class Main {
	

	public static void main(String[] args) {
		OracleDataSource ods;
		try {
			ods = new OracleDataSource();
			
			

			String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
			ods.setURL(url);
			ods.setUser("people_user");
			ods.setPassword("abc123.");
			Connection conn = ods.getConnection();

			// Create Oracle DatabaseMetaData object
			DatabaseMetaData meta = conn.getMetaData();

			// gets driver info:
			System.out.println("JDBC driver version is " + meta.getDriverVersion());


			PreparedStatement stmt = conn.prepareStatement("SELECT value(p) FROM PERSON_OBJ_TABLE p");
			OracleResultSet rs = (OracleResultSet) stmt.executeQuery();
			while (rs.next()) {

				


				
				Object s = rs.getObject(1, JPersona.getOracleDataFactory());

				if (s != null) {
					if (s instanceof JPersona) {
						JPersona john = (JPersona)s;
						
						
						System.out.println("This is a JPerson");
						System.out.println(s);
					}

					else {
						System.out.println("Unknown type");
						System.out.println(s);
					}
				}
			}
			
		
			
			
			

		} catch (SQLException e) {
			System.err.println("Ha ocurrido una exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

}

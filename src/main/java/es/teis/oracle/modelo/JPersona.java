package es.teis.oracle.modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;

import oracle.jdbc.OracleData;
import oracle.jdbc.OracleDataFactory;
import oracle.sql.CHAR;
import oracle.sql.NUMBER;
import oracle.sql.STRUCT;
//https://docs.oracle.com/en/database/oracle/oracle-database/18/jjdbc/Oracle-object-types.html#GUID-E47B8141-3C4C-44D5-BB5C-2F8E16DF5140

public class JPersona implements OracleData, OracleDataFactory {

	

	private NUMBER idno;
	private CHAR first_name;
	private CHAR last_name;

	private CHAR email;
	private CHAR phone;

	public static OracleDataFactory getOracleDataFactory() {
		return new JPersona();
	}	

	public JPersona(NUMBER idno, CHAR first_name, CHAR last_name, CHAR email, CHAR phone) {
		this.idno = idno;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.phone = phone;
	}

	public JPersona() {
		
	}

	@Override
	public Object toJDBCObject(Connection c) throws SQLException {
		Object[] attributes = { idno, first_name, last_name, email, phone };
		Struct struct = c.createStruct("PEOPLE_USER.PERSON_OBJ_TABLE", attributes);
		return struct;
	}

	@Override
	public OracleData create(Object jdbcValue, int sqlType) throws SQLException {

		if (jdbcValue == null)
			return null;
		Object[] attributes = ((STRUCT) jdbcValue).getOracleAttributes();
		return new JPersona((NUMBER) attributes[0], (CHAR) attributes[1], (CHAR) attributes[2], (CHAR) attributes[3],
				(CHAR) attributes[4]);

	}

	@Override
	public String toString() {
		try {
			return "JPersona [idno=" + idno.intValue() + ", first_name=" + first_name
					+ ", last_name=" + last_name + ", email=" + email + ", phone=" + phone + "]";
		} catch (SQLException e) {
			e.printStackTrace();

			return "JPersona [ idno=" + ", first_name=" + first_name + ", last_name="
					+ last_name + ", email=" + email + ", phone=" + phone + "]";
		}

	}

	public NUMBER getIdno() {
		return idno;
	}

	public void setIdno(NUMBER idno) {
		this.idno = idno;
	}

	public CHAR getFirst_name() {
		return first_name;
	}

	public void setFirst_name(CHAR first_name) {
		this.first_name = first_name;
	}

	public CHAR getLast_name() {
		return last_name;
	}

	public void setLast_name(CHAR last_name) {
		this.last_name = last_name;
	}

	public CHAR getEmail() {
		return email;
	}

	public void setEmail(CHAR email) {
		this.email = email;
	}

	public CHAR getPhone() {
		return phone;
	}

	public void setPhone(CHAR phone) {
		this.phone = phone;
	}
	
	
}

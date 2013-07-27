package com.blanasco.jdbc;


import java.sql.Connection;

import com.blanasco.dao.GenericDAO;

public class JDBCTest {
	public static final String db_jndi ="jdbc:mysql://localhost:3306/test";
	public static final String user ="bert";
	public static final String password ="otot@1234";

	public static void main (String [] args){
		Connection conn = null;
		GenericDAO dao = new GenericDAO();
		conn = dao.getConnection(db_jndi, user,password);
		dao.closeConnection(conn);
	}
}

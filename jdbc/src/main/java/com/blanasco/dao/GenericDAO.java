package com.blanasco.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  class GenericDAO {
	Connection conn = null;
	

	public Connection getConnection(final String db_jndi,
			final String userName,
			final String password){

		try{
			conn = DriverManager.getConnection(db_jndi, userName, password);
		}
		catch (SQLException e){
			System.out.println(e.toString());
		}
		finally{
			try {
				if(conn != null){
					conn.close();
				}
			}
			catch(Exception e){

			}

		}
		return conn;
	}
	
	public void closeConnection(Connection conn){
			if(conn != null){
				try{
					conn.close();
				}
				catch(Exception e){
					//ignor
					System.out.println(e.toString());
				}
			}
	}
}

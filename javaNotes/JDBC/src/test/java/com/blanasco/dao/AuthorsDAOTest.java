package com.blanasco.dao;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import com.blanasco.jdbc.model.Authors;

public class AuthorsDAOTest {
	AuthorsDAO dao = null;
	public static final String db_jndi ="jdbc:mysql://localhost:3306/test";
	public static final String user ="bert";
	public static final String password ="otot@1234";
	public static final String first_name = "testFirst";
	Connection conn = null;
	
	@Before
	public void setUp(){
		dao = new AuthorsDAO();
		conn = dao.getConnection(db_jndi, user, password);
	}
	
	@After
	public void tearDown(){
		dao.closeConnection(conn);
	}
	
	@Test
	public void getAuthorsByFirstName(){
		Authors author = dao.getAuthorByFirstName(first_name);
		assertNotNull(author);
	}
}

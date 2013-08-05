package com.jpanotes.util;

import static org.junit.Assert.assertNotNull;

import org.hibernate.SessionFactory;
import org.junit.Test;

public class HibernateUtilTest {

	@Test
	public void getSessionFactory(){
		SessionFactory factory = HibernateUtil.getSessionFactory();
		assertNotNull(factory);
	}


	@Test
	public void addAnnotations(){

	}

}

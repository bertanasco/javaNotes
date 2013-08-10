package com.jpanotes.notes;

import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.jpanotes.util.HibernateUtil;

public abstract class HibernateTest {
	public static Session session = null;

	@BeforeClass
	public static void setUpDB(){
		HibernateUtil.recreateDB();
	}

	@AfterClass
	public static void tearDown(){
		if( session.isOpen()){
			HibernateUtil.closeSession(session);
		}
	}

}

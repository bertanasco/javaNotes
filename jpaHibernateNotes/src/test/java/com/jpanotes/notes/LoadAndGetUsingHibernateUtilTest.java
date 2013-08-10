package com.jpanotes.notes;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpanotes.model.Blobber;
import com.jpanotes.model.User;
import com.jpanotes.util.HibernateUtil;

public class LoadAndGetUsingHibernateUtilTest {
	private static Logger log = LoggerFactory.getLogger(LoadAndGetUsingHibernateUtilTest.class);
	private static Session session = null;


	@BeforeClass
	public static void setUpDB(){
		HibernateUtil.recreateDB();
		createRecordTest();
	}

	@Before
	public  void setUp(){
		session = HibernateUtil.beginTransaction();
	}

	public static void createRecordTest(){
		session = HibernateUtil.beginTransaction();
		User userNew = new User();
		userNew.setName("berto");
		userNew.setPassword("testpassword");
		userNew.setLastAccessTime(new java.util.Date());
		userNew.setRegistrationDate(new java.util.GregorianCalendar());
		session.saveOrUpdate(userNew);

		Blobber blob = new Blobber();
		session.saveOrUpdate(blob);

		HibernateUtil.commitTransaction(session);
	}

	@Test
	public void getTest(){
		User u = (User) session.get(User.class, new Long(1));
		assertTrue(u.getName().equals("berto"));
		HibernateUtil.commitTransaction(session);
	}

	@Test
	public void loadTest(){
		User u = (User) session.load(User.class, new Long(1));
		log.info(" Username: " + u.getName() ) ;
		HibernateUtil.commitTransaction(session);
	}
}

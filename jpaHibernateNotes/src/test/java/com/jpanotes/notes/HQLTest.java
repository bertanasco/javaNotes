package com.jpanotes.notes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpanotes.model.Blobber;
import com.jpanotes.model.User;
import com.jpanotes.util.HibernateUtil;

public class HQLTest {

	private static Logger log = LoggerFactory.getLogger(HQLTest.class);

	private static Session session = null;

	@BeforeClass
	public static void setUpDB(){
		HibernateUtil.recreateDB();
	}

	@Before
	public  void setUp(){
		session = HibernateUtil.beginTransaction();
	}

	@AfterClass
	public static void tearDown(){
		if( session.isOpen()){
			HibernateUtil.closeSession(session);
		}
	}

	@Test
	public void createRecordTest(){
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
	public void selectAll(){
		Query unique = session.createQuery(" from User ");
		List<?> users = unique.list();
		for (  int x = 0 ; x < users.size(); x++){
			User user = (User)users.get(x);
			assertNotNull(user);
			log.info(user.getPassword());
		}

	}

	@Test
	public void selectField(){
		Query unique = session.createQuery("SELECT name from User");
		List<?> users = unique.list();
		for (  int x = 0 ; x < users.size(); x++){
			final String userName = (String)users.get(x);
			assertNotNull(userName);
			log.info(userName);
		}
	}

	@Test
	public void stringLiteralCondition(){
		Query unique = session.createQuery("SELECT name from User wHeRe name ='berto'");
		List<?> users = unique.list();
		for (  int x = 0 ; x < users.size(); x++){
			final String userName = (String)users.get(x);
			assertNotNull(userName);
			log.info(userName);
		}
	}

	@Test
	public void stringParameterLiteral(){
		Query unique = session.createQuery("SELECT name from User WHERE name =:name");
		unique.setString("name", "berto");
		List<?> users = unique.list();
		for (  int x = 0 ; x < users.size(); x++){
			final String userName = (String)users.get(x);
			assertNotNull(userName);
			log.info(userName);
		}
	}

	@Test
	public void stringParameterUnique(){
		Query unique = session.createQuery("SELECT name from User WHERE name =:name");
		unique.setString("name", "berto");
		String name = (String) unique.uniqueResult();
		assertNotNull(name);
		assertEquals("berto",name);
	}


}
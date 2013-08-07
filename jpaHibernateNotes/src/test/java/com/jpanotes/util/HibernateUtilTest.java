package com.jpanotes.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpanotes.model.User;

public class HibernateUtilTest {
	private static Logger log = LoggerFactory.getLogger(HibernateUtilTest.class);

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
		session.saveOrUpdate(userNew);
		HibernateUtil.commitTransaction(session);
	}

	@Test
	public void queryTest(){
		Query results = session.createQuery("from User");
		List<?> users = results.list();
		printUserResults(users);
		assertNotNull(results);
		HibernateUtil.commitTransaction(session);
	}

	@Test
	public void uniqueQueryTest(){
		User user = getRecord();
		assertTrue(user.getName().equals("berto"));
	}

	@Test
	public void updateTest(){
		User user = getRecord();
		user.setPassword("updatedTestPassword");
		session.update(user);
		user = getRecord();
		HibernateUtil.commitTransaction(session);
		assertTrue(user.getPassword().equals("updatedTestPassword"));
	}

	private static User getRecord(){
		String query = "from User where ID = :Id";
		Query hqlQuery = session.createQuery(query);
		hqlQuery.setInteger("Id", 1);
		User user = (User) hqlQuery.uniqueResult();
		return user;
	}

	private static void printUserResults(final List<?> users){
		for(int x = 0; x < users.size(); x++){
			User u =(User) users.get(x);
			log.info("*********[USER ID:" + u.getId() + "]");
			log.info("*********[USER Name:" + u.getName() + "]");
		}
	}

}

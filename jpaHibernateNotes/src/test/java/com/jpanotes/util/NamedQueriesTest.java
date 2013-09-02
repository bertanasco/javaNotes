package com.jpanotes.util;

import static org.junit.Assert.assertTrue;

import org.hibernate.Query;
import org.junit.Before;
import org.junit.Test;
import com.jpanotes.model.User;
import com.jpanotes.notes.HibernateTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NamedQueriesTest  extends HibernateTest{
	
	private Logger log = LoggerFactory.getLogger(NamedQueriesTest.class);
	@Before
	public  void setUp(){
		session = HibernateUtil.beginTransaction();
	}
	
	@Test
	public void createRecords(){
		for(int x = 0 ; x < 10;x++){
			session = HibernateUtil.beginTransaction();
			User userNew = new User();
			userNew.setName("berto" + x);
			userNew.setPassword("testpassword");
			userNew.setLastAccessTime(new java.util.Date());
			userNew.setRegistrationDate(new java.util.GregorianCalendar());
			session.saveOrUpdate(userNew);
			HibernateUtil.commitTransaction(session);
		}
	}
	
	@Test
	public void findByNameTest(){
		Query query = session.getNamedQuery("user.findByName");
		query.setString("name", "berto1");
		User user = (User) query.uniqueResult();
		assertTrue(user.getName().equals("berto1"));
		log.info(user.toString());
		HibernateUtil.commitTransaction(session);
	}
}

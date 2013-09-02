package com.jpanotes.notes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Query;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpanotes.model.Blobber;
import com.jpanotes.model.User;
import com.jpanotes.util.HibernateUtil;

public class HQLTest extends HibernateTest {

	private static Logger log = LoggerFactory.getLogger(HQLTest.class);

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

		HibernateUtil.commitTransaction(session);

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

		HibernateUtil.commitTransaction(session);
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

		HibernateUtil.commitTransaction(session);
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

		HibernateUtil.commitTransaction(session);
	}

	@Test
	public void stringParameterUnique(){
		Query unique = session.createQuery("SELECT name from User WHERE name =:name");
		unique.setString("name", "berto");
		String name = (String) unique.uniqueResult();
		assertNotNull(name);
		assertEquals("berto",name);

		HibernateUtil.commitTransaction(session);
	}

	@Test
	public void selectUserUsingParameter(){
		Query unique = session.createQuery("from User WHERE name =:name");
		unique.setString("name", "berto");
		User name = (User) unique.uniqueResult();
		assertNotNull(name);

		HibernateUtil.commitTransaction(session);
	}

	@Test
	public void noUniqueResult(){
		Query unique =  session.createQuery("from User WHERE name =:name");
		unique.setString("name", "walangUserNaGanito");
		User name = (User) unique.uniqueResult();
		assertNull(name);

		HibernateUtil.commitTransaction(session);
	}

	@Test
	public void orderByAsc(){
		Query unique =  session.createQuery("from User  as x ORDER BY x.name ASC");
		List<?> users = unique.list();
		for (  int x = 0 ; x < users.size(); x++){
			final User userName = (User)users.get(x);
			assertNotNull(userName);
			log.info("\n " +userName.toString());
		}

		HibernateUtil.commitTransaction(session);
	}

	@Test
	public void groupByHaving(){
		Query unique =  session.createQuery("from User  as x GROUP BY x.id HAVING x.id > 7");
		List<?> users = unique.list();
		for (  int x = 0 ; x < users.size(); x++){
			final User userName = (User)users.get(x);
			assertNotNull(userName);
			log.info("\n " +userName.toString());
		}

		HibernateUtil.commitTransaction(session);
	}
	
	@Test
	public void updateBulkRecords(){
		Query unique =  session.createQuery("update User set password = 'updatedPassword' where " +
				" password = 'testpassword')");
		int rowCount = unique.executeUpdate();
		log.info("records updated : " + rowCount);
		assertTrue(rowCount > 0);
		HibernateUtil.commitTransaction(session);
	}
}
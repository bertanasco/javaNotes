package com.jpanotes.criteriaapi;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpanotes.model.User;
import com.jpanotes.notes.HibernateTest;
import com.jpanotes.util.HibernateUtil;


public class CriteriaAPITest extends HibernateTest{
	private Logger log = LoggerFactory.getLogger(CriteriaAPITest.class);
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
	public void listShouldReturnList(){
		User user = new User();
		user.setPassword("testpassword");
		Example ex =  Example.create(user);
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(ex);
		List <User>results = criteria.list();
		assertNotNull(results);
		for(User u: results){
			log.info("Retrieved data");
			log.info(u.toString());
		}
	}
	
	@Test
	public void listShouldFail(){
		User user = new User();
		user.setPassword("shouldfail");
		Example ex =  Example.create(user);
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(ex);
		@SuppressWarnings("unchecked")
		List <User>results = criteria.list();
		assertTrue(results.size() == 0);
	}
	
	
	@Test
	public void listWouldReturnAll(){
		User user = new User();
		//user.setPassword("shouldfail");
		Example ex =  Example.create(user);
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(ex);
		@SuppressWarnings("unchecked")
		List <User>results = criteria.list();
		assertTrue(results.size() == 10);
	}
	
	@Test
	public void listWithEnableLike(){
		User user = new User();
		user.setName("berto");
		Example ex =  Example.create(user);
		ex.enableLike(MatchMode.START);
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(ex);
		@SuppressWarnings("unchecked")
		List <User>results = criteria.list();
		assertTrue(results.size() > 0);
		for(User u : results){
			log.info( "Record retrived enabling like at start : " + u.getName());
		}

	}
	
	@Test
	public void listWithoutIgnorCase(){
		User user = new User();
		user.setName("BERTO");
		Example ex =  Example.create(user);
		ex.enableLike(MatchMode.ANYWHERE);
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(ex);
		@SuppressWarnings("unchecked")
		List <User>results = criteria.list();
		assertTrue(results.size() > 0);
		for(User u : results){
			log.info( "Record retrived enabling like at start : " + u.getName());
		}

	}
	
	
	@Test
	public void returnAllWithPagination(){
		Criteria criteria = session.createCriteria(User.class);
		criteria.setFirstResult(0);
		criteria.setMaxResults(5);
		@SuppressWarnings("unchecked")
		List <User>results = criteria.list();
		assertTrue(results.size() == 5);
		for(User u : results){
			log.info( "Record retrived enabling like at start : " + u.getName());
		}
	}
	
	@Test
	public void orderTest(){
		Criteria criteria = session.createCriteria(User.class);
		Order odr = Order.desc("name");
		criteria.addOrder(odr);
		criteria.setFirstResult(0);
		criteria.setMaxResults(5);
		@SuppressWarnings("unchecked")
		List <User>results = criteria.list();
		assertTrue(results.size() == 5);
		for(User u : results){
			log.info( "Record retrived enabling like at start : " + u.getName());
		}

	}
	
}

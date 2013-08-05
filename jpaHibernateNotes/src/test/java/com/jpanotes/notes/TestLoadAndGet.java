package com.jpanotes.notes;

import org.hibernate.LazyInitializationException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpanotes.model.User;

public class TestLoadAndGet {
	private static Logger log = LoggerFactory.getLogger(TestLoadAndGet.class);
	private static Configuration config = null;
	private static SchemaUpdate schemaUpdate  = null;
	private static SessionFactory factory = null;

	@BeforeClass
	public static void setUp(){
		log.info("creating a new configuration");
		config = new Configuration();
		config.addAnnotatedClass(User.class);
		config.configure();
		log.info("Done reading hbm configuration");

		//log.info("Creating schemaExport from configuration");
		schemaUpdate = new SchemaUpdate(config);
		schemaUpdate.execute(true, true);

		//create session factory
		factory = config.buildSessionFactory();
	}

	@Test
	public void getTest(){
		Session s = factory.getCurrentSession();
		Transaction t = s.beginTransaction();
		User u = (User) s.get(User.class, new Long(70));
		log.info(" Username: " + u.getName() ) ;
		t.commit();
	}

	@Test
	public void loadTest(){
		Session s = factory.getCurrentSession();
		Transaction t = s.beginTransaction();
		User u = (User) s.load(User.class, new Long(70));
		log.info(" Username: " + u.getName() ) ;
		t.commit();
	}
	//will throw an error when you try to access a property after the transaction
	//has been commited
	@Test(expected=LazyInitializationException.class)
	public void loadTestFail(){
		Session s = factory.getCurrentSession();
		Transaction t = s.beginTransaction();
		//load would only access the db when you use one of the property
		User u = (User) s.load(User.class, new Long(70));
		t.commit();
		//when we called u.getName load method called the db
		log.info(" Username: " + u.getName() ) ;
	}


	@Test
	public void getAfterTransactionCommit(){
		Session s = factory.getCurrentSession();
		Transaction t = s.beginTransaction();
		//get will always hit the database
		User u = (User) s.get(User.class, new Long(70));
		t.commit();
		log.info(" Username: " + u.getName() ) ;
	}

	@Test(expected=ObjectNotFoundException.class)
	public void loadTestFailInvalidId(){
		Session s = factory.getCurrentSession();
		Transaction t = s.beginTransaction();
		User u = (User) s.load(User.class, new Long(1));
		//would throw OBject Not found cause we used the property
		log.info(" Username: " + u.getName() ) ;
		t.commit();
	}
	@Test//(expected=ObjectNotFoundException.class)
	public void loadTestWillnotFail(){
		Session s = factory.getCurrentSession();
		Transaction t = s.beginTransaction();
		User u = (User) s.load(User.class, new Long(999));
		//log.info(" Username: " + u.getName() ) ;
		t.commit();
	}

	@Test(expected=NullPointerException.class)
	public void getTestInvalidId(){
		Session s = factory.getCurrentSession();
		Transaction t = s.beginTransaction();
		User u = (User) s.get(User.class, new Long(999));
		log.info(" Username: " + u.getName() ) ;
		t.commit();
	}

	@Test
	public void getTestNotUsingProperty(){
		Session s = factory.getCurrentSession();
		Transaction t = s.beginTransaction();
		User u = (User) s.get(User.class, new Long(999));
		//log.info(" Username: " + u.getName() ) ;
		t.commit();
	}
	@Test(expected=NullPointerException.class)
	public void getTestNotUsingPropertyAfterCommit(){
		Session s = factory.getCurrentSession();
		Transaction t = s.beginTransaction();
		User u = (User) s.get(User.class, new Long(999));
		t.commit();
		log.info(" Username: " + u.getName() ) ;
	}
}


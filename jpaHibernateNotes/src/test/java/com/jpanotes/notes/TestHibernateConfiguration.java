package com.jpanotes.notes;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
//import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpanotes.model.User;

public class TestHibernateConfiguration {
	private static Logger log = LoggerFactory.getLogger(TestHibernateConfiguration.class);
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

		//Schema Export drops tables
		//schemaExport.create(true, true);
	}

	@AfterClass
	public static  void tearDown(){
	}

	@Test
	public void testConfiguration(){
		log.info("Testing schema config");
		assertNotNull(config);
	}

	@Test
	public void testSession(){
		log.info("testing session");
		assertNotNull(factory);
		Session session = factory.getCurrentSession();
		session.beginTransaction();

		User user = new User();
		user.setName("berto");
		user.setPassword("testpassword");
		session.saveOrUpdate(user);

		session.getTransaction().commit();
		log.info("added user object to database");
	}

	@Test
	public void testSchemaExport(){
		log.info("Testing schema export");
		assertNotNull(schemaUpdate);
		//schemaExport.
	}


}

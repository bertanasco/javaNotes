package com.jpanotes.notes;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
//import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.junit.Before;
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

	/*@Test
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
	}*/

	@Test
	public void queryTest(){
		log.info("testing session");
		assertNotNull(factory);
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		//org.hibernate.hql.ast.QuerySyntaxException: table not mapped
		// use entity class names not table name LOL
		Query results = session.createQuery("from User");
		List users = results.list();
		for(int x = 0; x < users.size(); x++){
			User u =(User) users.get(x);
			log.info("*********[USER ID:" + u.getId() + "]");
			log.info("*********[USER Name:" + u.getName() + "]");
		}
		session.getTransaction().commit();
		log.info("added user object to database");
	}

	@Before
	public  void createRecord(){
		Session session = factory.getCurrentSession();
		session.beginTransaction();

		User userNew = new User();
		userNew.setName("berto");
		userNew.setPassword("testpassword");
		session.saveOrUpdate(userNew);

		session.getTransaction().commit();
	}

	@Test
	public void uniqueTest(){
		log.info("testing session");
		assertNotNull(factory);

		Session session = factory.getCurrentSession();
		session.beginTransaction();

		User userNew = new User();
		userNew.setName("berto");
		userNew.setPassword("testpassword");
		session.saveOrUpdate(userNew);

		session.getTransaction().commit();

		session = factory.getCurrentSession();
		session.beginTransaction();
		//org.hibernate.hql.ast.QuerySyntaxException: table not mapped
		// use entity class names not table name LOL
		String query = "from User where ID = :Id";
		Query hqlQuery = session.createQuery(query);
		hqlQuery.setInteger("Id", 70);

		User user = (User) hqlQuery.uniqueResult();
		log.info("*********[USER ID:" + user.getId() + "]");
		log.info("*********[USER name:" + user.getName() + "]");
		session.getTransaction().commit();
		log.info("added user object to database");
	}

	@Test
	public void update(){
		log.info("testing session");
		assertNotNull(factory);
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		//org.hibernate.hql.ast.QuerySyntaxException: table not mapped
		// use entity class names not table name LOL
		String query = "from User where id = :Id";
		Query hqlQuery = session.createQuery(query);
		hqlQuery.setInteger("Id", 70);

		User user = (User) hqlQuery.uniqueResult();
		log.info("*********[USER ID:" + user.getId() + "]");
		log.info("*********[USER name:" + user.getName() + "]");
		user.setPassword("UpdatedPassword");
		session.saveOrUpdate(user);


		user = (User) hqlQuery.uniqueResult();
		assertEquals(user.getPassword(),"UpdatedPassword");
		log.info("*********[USER password:" + user.getPassword() + "]");
		session.getTransaction().commit();
		log.info("added user object to database");
	}

	/*@Test
	public void deleteAll(){
		assertNotNull(factory);
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		//org.hibernate.hql.ast.QuerySyntaxException: table not mapped
		// use entity class names not table name LOL
		Query results = session.createQuery("from User");
		List users = results.list();
		for(int x = 0; x < users.size(); x++){
			User u =(User) users.get(x);
			log.info("*********[USER ID:" + u.getId() + "]");
			log.info("*********[USER Name:" + u.getName() + "]");
			session.delete(u);
		}
		session.getTransaction().commit();
		log.info("added user object to database");
	}*/

	/*@Test
	public void queryTwoFilterTest(){
		log.info("testing session");
		assertNotNull(factory);
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		//org.hibernate.hql.ast.QuerySyntaxException: table not mapped
		// use entity class names not table name LOL
		Query results = session.createQuery("name,password from User");
		List users = results.list();
		for(int x = 0; x < users.size(); x++){
			log.info("*********[USER ID:" +users.get(x) + "]");
		}
		session.getTransaction().commit();
		log.info("added user object to database");
	}*/

	@Test
	public void testSchemaExport(){
		log.info("Testing schema export");
		assertNotNull(schemaUpdate);
		//schemaExport.
	}


}

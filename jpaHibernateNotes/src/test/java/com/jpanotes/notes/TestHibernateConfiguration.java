package com.jpanotes.notes;

import static org.junit.Assert.*;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpanotes.model.User;

public class TestHibernateConfiguration {
	private static Logger log = LoggerFactory.getLogger(TestHibernateConfiguration.class);
	private static Configuration config = null;
	private static SchemaExport schemaExport  = null;

	@BeforeClass
	public static void setUp(){
		log.info("creating a new configuration");
		config = new Configuration();
		config.addAnnotatedClass(User.class);
		config.configure();
		log.info("Done reading hbm configuration");
		log.info("Creating schemaExport from configuration");
		schemaExport = new SchemaExport(config);
		schemaExport.create(true, true);
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
	public void testSchemaExport(){
		log.info("Testing schema export");
		assertNotNull(schemaExport);
		//schemaExport.
	}


}

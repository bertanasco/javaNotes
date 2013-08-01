package com.jpanotes.notes;

import static org.junit.Assert.*;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

import com.jpanotes.model.User;

public class TestHibernateConfiguration {
	private static Configuration config = null;
	private static SchemaExport schemaExport  = null;

	@BeforeClass
	public static void setUp(){
		config = new Configuration();
		config.addAnnotatedClass(User.class);
		config.configure();
		schemaExport = new SchemaExport(config);
		schemaExport.create(true, true);
	}

	@AfterClass
	public static  void tearDown(){
	}
	@Test
	public void testConfiguration(){
		assertNotNull(config);
	}

	@Test
	public void testSchemaExport(){
		assertNotNull(schemaExport);
	}


}

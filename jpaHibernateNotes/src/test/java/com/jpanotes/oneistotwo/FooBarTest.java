package com.jpanotes.oneistotwo;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Criteria;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpanotes.model.FooBar;
import com.jpanotes.notes.HibernateTest;
import com.jpanotes.util.HibernateUtil;

public class FooBarTest extends HibernateTest {
	private Logger log = LoggerFactory.getLogger(FooBarTest.class);
	@Before
	public  void setUp(){
		session = HibernateUtil.beginTransaction();
	}
	
	@Test
	public void createRecords(){
		for(int x = 0 ; x < 10;x++){
			session = HibernateUtil.beginTransaction();
			FooBar foo = new FooBar();
			foo.setBarField("barField");
			foo.setFooField("fooField");
			session.saveOrUpdate(foo);
			HibernateUtil.commitTransaction(session);
		}
	}
	
	
	@Test
	public void retrieveFromTwoTables(){
		Criteria c = session.createCriteria(FooBar.class);
		List <FooBar> foobar = c.list();
		assertTrue(foobar.size() == 10);
		for (FooBar f: foobar){
			log.info(" Retrieved ID " + f.getId());
			log.info(f.getBarField());
			log.info(f.getFooField());
		}
		
	}

}

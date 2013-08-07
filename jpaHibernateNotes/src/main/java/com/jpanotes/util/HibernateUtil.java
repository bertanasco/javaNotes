package com.jpanotes.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;

import com.jpanotes.model.User;

public class HibernateUtil {

	private static SessionFactory factory ;
	private static Configuration config;

	public static SessionFactory getSessionFactory(){
		if (factory == null){
			factory = getInitializedConfiguration().
					buildSessionFactory();
		}
		return factory;
	}

	public static Configuration getInitializedConfiguration(){
		if (config == null){
			config = new Configuration();
			config = addAnnotations(config);
			config.configure();
		}
		return config;
	}

	public static Configuration addAnnotations(final Configuration config){
		config.addAnnotatedClass(User.class);
		return config;
	}

	public static Session getSession(){
		if(factory == null){
			factory = getSessionFactory();
		}
		return factory.getCurrentSession();
	}

	public static void closeSession(Session session){
		session.close();
	}

	public static void recreateDB(){
		new SchemaExport(getInitializedConfiguration()).create(true, true);
	}

	public static void updateDB(){
		new SchemaUpdate(getInitializedConfiguration()).execute(true, true);
	}

	public static Session beginTransaction(){
		Session hibernateSession = getSession();
		hibernateSession.beginTransaction();
		return hibernateSession;
	}

	public static void commitTransaction(final Session session){
		session.getTransaction().commit();
	}

	public static void rollBackTransaction(final Session session){
		session.getTransaction().rollback();
	}

}

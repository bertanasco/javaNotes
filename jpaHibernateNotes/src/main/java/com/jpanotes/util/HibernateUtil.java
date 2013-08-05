package com.jpanotes.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jpanotes.model.User;

public class HibernateUtil {

	private static SessionFactory factory ;

	public static SessionFactory getSessionFactory(){
		Configuration config = new Configuration();
		config = addAnnotations(config);
		config.configure();
		factory = config.buildSessionFactory();
		return factory;
	}

	public static Configuration addAnnotations(final Configuration config){
		config.addAnnotatedClass(User.class);
		return config;
	}

}

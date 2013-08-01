package com.jpanotes.service;

import org.hibernate.cfg.Configuration;

import com.jpanotes.model.User;

public class SampleApp {
	public static void main(String [] args){
		// deprecated, moved to Configuration
		//AnnotationConfiguration config = new AnnotationConfiguration();
		Configuration config = new Configuration();
		config.addAnnotatedClass(User.class);
	}
}

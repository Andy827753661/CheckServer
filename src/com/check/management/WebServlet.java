package com.check.management;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class WebServlet implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		System.out.println("Tomcat starting...");
		
	}

	
	
}

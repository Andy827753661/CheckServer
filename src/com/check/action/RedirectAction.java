package com.check.action;



import ejw.RequestHandler;
import ejw.ServerInterface;

public class RedirectAction extends RequestHandler{
	public String toURL(ServerInterface serverInterface)throws Exception{
		String url=serverInterface.getParameter("url");
		System.out.println("Log url == "+url);
		return "/WEB-INF/"+url;
	}
	
	
	public String toEJW(ServerInterface serverInterface)throws Exception{
		String url=serverInterface.getParameter("url");
		serverInterface.forwardRequest("/"+url);
		return NO_FORWARDING;
	}
	
}

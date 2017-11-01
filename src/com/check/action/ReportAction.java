package com.check.action;

import ejw.RequestHandler;
import ejw.ServerInterface;

public class ReportAction extends RequestHandler{
	public String init(ServerInterface serverInterface)throws Exception{
		System.out.println("ReportAction   !!!!!");
		
		return "/WEB-INF/report.jsp";
	}
}

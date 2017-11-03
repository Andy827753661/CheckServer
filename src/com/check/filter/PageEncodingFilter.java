package com.check.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.check.config.WebConfig;

public class PageEncodingFilter implements Filter {

	public void init(FilterConfig arg0) throws ServletException {
		System.out.println(com.check.config.WebConfig.DBNAME + "×Ö·û¹ýÂËÆô¶¯£¡");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if (req.getHeader("X-Requested-With") != null
				&& req.getHeader("X-Requested-With").equalsIgnoreCase(
						"XMLHttpRequest")) {
			request.setCharacterEncoding(WebConfig.UTF8);
		} else {
			request.setCharacterEncoding(WebConfig.GB2312);
		}
		chain.doFilter(request, response);
	}

	public void destroy() {
		System.out.println(com.check.config.WebConfig.DBNAME + "×Ö·û¹ýÂËÍ£Ö¹£¡");
	}
}

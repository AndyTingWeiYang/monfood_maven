package com.model.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/resprofile/*", "/admin-res-reception/*"})
public class ResLoginFilter implements Filter{ 
	private static final long serialVersionUID = 1L;
	
	ServletContext context = null;

	public void init(FilterConfig fConfig) throws ServletException {
		context = fConfig.getServletContext();
		System.out.println("ResLoginFilter initialized");
	}
	
	
	public void destroy() {
		System.out.println("ResLoginFilter destroied");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		Object loginStatus = session.getAttribute("isResLogin");
		if("true".equals(loginStatus)) {
			System.out.println("run here");
			chain.doFilter(request, response);
		}
		else {
			System.out.println("not login");
			((HttpServletResponse)response).sendRedirect(context.getContextPath() + "/resLogin.html");
		}
		
	}
}

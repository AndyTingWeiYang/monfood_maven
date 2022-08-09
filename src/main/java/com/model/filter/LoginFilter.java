package com.model.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/pay.html", "/userProfile/*", "/status.html", "/searching.html"})
public class LoginFilter extends HttpFilter{
	private static final long serialVersionUID = 1L;

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("LoginFilter initialized");
	}
	
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		Object loginStatus = session.getAttribute("isuserLogin");
		if("true".equals(loginStatus)) {
			chain.doFilter(request, response);
		}
		else {
			System.out.println("not login");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/userLogin.html");
			requestDispatcher.forward(request, response);
		}
	}

	
	public void destroy() {
		System.out.println("LoginFilter destroied");
	}
}

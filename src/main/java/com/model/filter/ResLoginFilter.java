package com.model.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/resprofile/*", "/admin-res-reception/*" }, initParams = {
		@WebInitParam(name = "excludeResPhoto", value = "/resprofile/ResPhotoPreviewServlet/*"),
		@WebInitParam(name = "excludeProductPic", value = "/resprofile/ProductPicServlet/*") })
public class ResLoginFilter implements Filter {

	private static final long serialVersionUID = 1L;

	List<String> url = new ArrayList<>();

	String servletPath;

	ServletContext context = null;

	public void init(FilterConfig fConfig) throws ServletException {
		context = fConfig.getServletContext();
		Enumeration<String> initParams = fConfig.getInitParameterNames();
		while (initParams.hasMoreElements()) {
			String path = initParams.nextElement();
			url.add(fConfig.getInitParameter(path));
		}

		System.out.println("ResLoginFilter initialized");
	}

	public void destroy() {
		System.out.println("ResLoginFilter destroied");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		servletPath = req.getServletPath();

		if (!isExcluded()) {
			HttpSession session = ((HttpServletRequest) request).getSession();
			Object loginStatus = session.getAttribute("isResLogin");
			if ("true".equals(loginStatus)) {
				System.out.println("run here");
				chain.doFilter(request, response);
			} else {
				System.out.println("not login");
				((HttpServletResponse) response).sendRedirect(context.getContextPath() + "/resLogin.html");
			}
		} else {
			chain.doFilter(request, response);
		}

	}

	private boolean isExcluded() {
		boolean exclude = false;
		for (String requestUrl : url) {
			if (requestUrl.endsWith("*")) {
				// 移除最後一個 /* 字元
				requestUrl = requestUrl.substring(0, requestUrl.length() - 2);

				if (servletPath.startsWith(requestUrl)) {
					exclude = true;
					break;
				}
			} else {
				exclude = false;
				break;
			}
		}

		return exclude;
	}
}

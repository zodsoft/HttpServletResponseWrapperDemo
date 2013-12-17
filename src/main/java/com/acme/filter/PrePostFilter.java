package com.acme.filter;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class PrePostFilter implements javax.servlet.Filter {

	public void doFilter(final ServletRequest request,
			final ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		OutputStream out = response.getOutputStream();
		out.write("<HR>PRE<HR>".getBytes());
		GenericResponseWrapper wrapper = new GenericResponseWrapper(
				(HttpServletResponse) response);
		chain.doFilter(request, wrapper);
//		wrapper.getWriter().flush();//IF access jsp,it is needed;but servlet don't need
		out.write(wrapper.getData());
		out.write("<HR>POST<HR>".getBytes());
		out.close();
	}

	public void destroy() {

	}

	public void init(FilterConfig arg0) throws ServletException {}
}
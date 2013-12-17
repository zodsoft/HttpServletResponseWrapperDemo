package com.acme.filter;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;

public class GenericResponseWrapper extends HttpServletResponseWrapper {
	private ByteArrayOutputStream output;
	private int contentLength;
	private String contentType;
	private PrintWriter pw;
	public GenericResponseWrapper(HttpServletResponse response) {
		super(response);
		output = new ByteArrayOutputStream();
	}

	public byte[] getData() {
		return output.toByteArray();
	}

	public ServletOutputStream getOutputStream() {
		return new FilterServletOutputStream(output);
	}

	public PrintWriter getWriter() {
		if(pw == null){
			pw = new PrintWriter(getOutputStream(), true);
		}
		return pw;
	}

	public void setContentLength(int length) {
		this.contentLength = length;
		super.setContentLength(length);
	}

	public int getContentLength() {
		return contentLength;
	}

	public void setContentType(String type) {
		this.contentType = type;
		super.setContentType(type);
	}

	public String getContentType() {
		return contentType;
	}
}

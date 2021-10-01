package com.hk.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(
		urlPatterns = { "/*" }, 
		initParams = { 
				@WebInitParam(name = "encoding", value = "utf-8")
		})
public class EncodeFilter implements Filter {

	public FilterConfig config;
	
    public void init(FilterConfig fConfig) throws ServletException {
    	config=fConfig;
    }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		String encoding=config.getInitParameter("encoding");
		request.setCharacterEncoding(encoding);
		response.setContentType("text/html;charset="+encoding);
		//��û���� �ڵ�
		chain.doFilter(request, response);
		//������� �ڵ�
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

}

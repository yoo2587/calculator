package spms.filters;

import java.io.IOException;
import java.sql.DriverManager;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {

	FilterConfig config;
	
	public CharacterEncodingFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config=config;		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain nextFilter)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding(config.getInitParameter("encoding"));
		
		nextFilter.doFilter(request,  response);
		
	}
	
	@Override
	public void destroy() {
		
		
	}

}

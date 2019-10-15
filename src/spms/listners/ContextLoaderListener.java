package spms.listners;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
//import org.apache.tomcat.jdbc.pool.DataSource;
//import javax.sql.DataSource;

import spms.dao.MySqlMemberDao;
//import spms.util.DBConnectionPool;
import spms.context.ApplicationContext;
import spms.controls.*;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
	static ApplicationContext applicationContext;
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	public ContextLoaderListener() {
		System.out.println("ContextLoaderListener()");
	}
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("contextInitialized()");
		
		try {
			applicationContext=new ApplicationContext();
			String resource="spms/dao/mybatis-config.xml";
			InputStream inputStream=Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
			applicationContext.addBean("sqlSessionFactory", sqlSessionFactory);
			System.out.println("sqlSessionFactory Add Complete");
			
			ServletContext sc=event.getServletContext();
			String propertiesPath=sc.getRealPath(sc.getInitParameter("contextConfigLocation"));
			applicationContext.prepareObjectsByProperties(propertiesPath);
			applicationContext.prepareObjectsByAnnotation("");
			applicationContext.injectDependency();
			
		} catch (Exception e) {
			System.out.println("ContextLoaderListener.contextInitialized() Error : " + e.getMessage());
			e.printStackTrace();
		} 		
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}
	

}

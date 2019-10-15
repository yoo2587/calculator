package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.vo.Member;

/**
 * Servlet implementation class AppInitServlet
 */
@WebServlet("/AppInitServlet")
public class AppInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppInitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("AppInitServlet 준비");
		super.init(config);
		
		
		 
		try {
			ServletContext sc=this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			
			Connection conn=DriverManager.getConnection(sc.getInitParameter("url") + "?serverTimezone=UTC&useSSL=false", 
					sc.getInitParameter("username"), 
					sc.getInitParameter("password"));
			
			sc.setAttribute("conn", conn);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		} 
		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub		
		
		System.out.println("AppInitServlet 마무리");		
		
		Connection conn= (Connection)this.getServletContext().getAttribute("conn");
		
		try {
			if(conn!=null && conn.isClosed()==false) {
				conn.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}	
				
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MySqlMemberDao;
import spms.vo.Member;

/**
 * Servlet implementation class MemberAddServlet
 */
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//ShowAddMember(request,response);
		
		request.setAttribute("viewUrl", "/member/MemberForm.jsp");
	}
	
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//AddMember(request,response);
		
		try {
			ServletContext sc=this.getServletContext();
			//DAO를 통해 데이터 처리만 한다.
			//프론트컨트롤러에서 include 방식으로 호출 받았으므로 처리할 작업만 하면 끝...
			MySqlMemberDao memberDao=(MySqlMemberDao)sc.getAttribute("memberDao");
			Member member=(Member)request.getAttribute("member");
			memberDao.insert(member);
			
			request.setAttribute("viewUrl", "redirect:list.do");
			
		} catch (Exception e) {
			//에러 처리또한 프론트컨트롤러에서 처리하므로 throw만 한다.
			System.out.println("MemberListServlet doGet error : " + e.getMessage());
			throw new ServletException(e);
		} 
		
	}

//	private void AddMember(HttpServletRequest request, HttpServletResponse response) throws ServletException {
//		Connection conn=null;
//		PreparedStatement stmt =null;		
//		
//		try {
//			
//			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//			conn=DriverManager.getConnection("jdbc:mysql://localhost/studydb?serverTimezone=UTC&useSSL=false", "study", "study");
//			stmt=conn.prepareStatement("INSERT INTO MEMBERS(EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE) VALUES(?,?,?,NOW(),NOW())");
//			//request.setCharacterEncoding("UTF-8");
//			
//			stmt.setString(1, request.getParameter("email"));
//			stmt.setString(2, request.getParameter("password"));
//			stmt.setString(3, request.getParameter("name"));
//			stmt.executeUpdate();
//			
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out=response.getWriter();
//			out.println("<html><head><title>회원등록결과</title></head>");
//			out.println("<body>");
//			
//			out.println("<p>email=" + request.getParameter("email") + "</p>");
//			out.println("<p>password=" + request.getParameter("password") + "</p>");
//			out.println("<p>name=" + request.getParameter("name") + "</p>");
//			
//			out.println("<p>등록 성공입니다.</p>");
//			out.println("</body></html>");
//			
//			response.addHeader("Refresh", "1;url=list");
//			
//			
//		} catch (Exception e) {
//			throw new ServletException();
//		}finally {
//			try {if(stmt!=null) stmt.close(); }catch(Exception e) {}
//			try {if(conn!=null) conn.close(); }catch(Exception e) {}
//		}
//	}
//	
//private void ShowAddMember(HttpServletRequest request, HttpServletResponse response) {
//		
//		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out=null;
//		try {
//			out = response.getWriter();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		out.println("<html><head><title>회원등록</title></head></html>");
//		out.println("<body><h1>회원등록</h1>");
//		out.println("<form action='add' method='post'>");
//		out.println("이름:<input type='text' name='name'><br>");
//		out.println("이메일:<input type='text' name='email'><br>");
//		out.println("암호:<input type='password' name='password'><br>");
//		out.println("<input type='submit' value='추가'>");
//		out.println("<input type='reset' value='취소'>");
//		out.println("</form>");
//		out.println("</body></html>");
//		out.println("");
//		out.println("");
//		
//	}

}

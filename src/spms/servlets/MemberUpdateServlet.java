package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MySqlMemberDao;
import spms.vo.Member;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//GetMember(request, response);
		
	    try {
	        ServletContext sc = this.getServletContext();
	        MySqlMemberDao memberDao = (MySqlMemberDao)sc.getAttribute("memberDao");

	        Member member = memberDao.selectOne(
	            Integer.parseInt(request.getParameter("no")));

	        request.setAttribute("member", member);
	        request.setAttribute("viewUrl", "/member/MemberUpdateForm.jsp");
	        
	      } catch (Exception e) {
	        throw new ServletException(e);
	      }
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//UpdateMember(request, response);
		try {
			ServletContext sc=this.getServletContext();
			//DAO를 통해 데이터 처리만 한다.
			//프론트컨트롤러에서 include 방식으로 호출 받았으므로 처리할 작업만 하면 끝...
			MySqlMemberDao memberDao=(MySqlMemberDao)sc.getAttribute("memberDao");
			Member member=(Member)request.getAttribute("member");
			memberDao.update(member);
			
			request.setAttribute("viewUrl", "redirect:list.do");
			
		} catch (Exception e) {
			//에러 처리또한 프론트컨트롤러에서 처리하므로 throw만 한다.
			System.out.println("MemberUpdateServlet doGet error : " + e.getMessage());
			throw new ServletException(e);
		} 		
		
	}
	
	
	
	
//	private void GetMember(HttpServletRequest request, HttpServletResponse response) throws ServletException {
//		
//		Connection conn=null;
//		Statement stmt=null;
//		ResultSet rs=null;
//		
//		try {
//			ServletContext sc=this.getServletContext();
//			
//			Class.forName(sc.getInitParameter("driver"));
//			
//			conn=DriverManager.getConnection(sc.getInitParameter("url") + "?serverTimezone=UTC&useSSL=false", 
//					sc.getInitParameter("username"), 
//					sc.getInitParameter("password"));
//			
//			stmt=conn.createStatement();
//			rs=stmt.executeQuery(
//					"select mno, email, mname, cre_date from members where mno="
//					+ request.getParameter("no")
//					);
//			rs.next();
//			
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out=response.getWriter();
//			out.println("<html><head><title>회원정보</title></head>");
//			out.println("<body><h1>회원정보</h1>");
//			out.println("<form action='update' method='post'>");
//			out.println("번호:<input type='text' name='no' value='" + request.getParameter("no") + "' readonly ><br>");
//			out.println("이름:<input type='text' name='name' value='" + rs.getString("MNAME") + "'  ><br>");
//			out.println("이메일:<input type='text' name='email' value='" + rs.getString("EMAIL") + "'  ><br>");
//			out.println("가입일:" + rs.getString("CRE_DATE") + "<br>");
//			
//			out.println("<input type='submit' value='저장'>");
//			out.println("<input type='button' value='취소' onclick='location.href='list' >");
//			
//			out.println("</form>");
//			out.println("</body></html>");
//			
//			out.println("");
//			out.println("");
//			out.println("");
//			out.println("");
//			out.println("");
//			out.println("");
//			out.println("");
//			out.println("");
//			
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			throw new ServletException(e);
//		}finally {
//			try {if(rs!=null) rs.close();}catch(Exception e) {}
//			try {if (stmt!=null) stmt.close();}catch(Exception e) {}
//			try {if(conn!=null) conn.close();}catch(Exception e) {}
//			
//		}
//		
//	}
//	
//	
//private void UpdateMember(HttpServletRequest request, HttpServletResponse response) throws ServletException {
//		
//		
//		Connection conn=null;
//		PreparedStatement stmt=null;
//		
//		try {
//			ServletContext sc=this.getServletContext();
//			//request.setCharacterEncoding("UTF-8");
//			
//			Class.forName(sc.getInitParameter("driver"));
//			
//			conn=DriverManager.getConnection(sc.getInitParameter("url") + "?serverTimezone=UTC&useSSL=false", 
//					sc.getInitParameter("username"), 
//					sc.getInitParameter("password"));
//			
//			stmt=conn.prepareStatement("update members set email=?, mname=?, mod_date=now() where mno=?");
//			stmt.setString(1, request.getParameter("email") );
//			stmt.setString(2, request.getParameter("name") );
//			stmt.setInt(3, Integer.parseInt(request.getParameter("no")) );
//			stmt.executeUpdate();
//			response.sendRedirect("list");
//			
//			
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			throw new ServletException(e);
//		}finally {
//			try {if (stmt!=null) stmt.close();}catch(Exception e) {}
//			try {if(conn!=null) conn.close();}catch(Exception e) {}
//			
//		}
//		
//	}

}

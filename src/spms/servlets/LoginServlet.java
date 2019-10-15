package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spms.dao.MySqlMemberDao;
import spms.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		
		//RequestDispatcher rd=request.getRequestDispatcher("/auth/LogInForm.jsp");
		//rd.forward(request, response);
		
		//페이지컨트롤러이므로 Get 요청에 대해서는 로그인 페이지 url을 담아서 프론트컨트롤러로 넘기면 임무 끝
		request.setAttribute("viewUrl", "/auth/LogInForm.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		try {
			ServletContext sc=this.getServletContext();
			//DAO를 통해 데이터 처리만 한다.
			//프론트컨트롤러에서 include 방식으로 호출 받았으므로 처리할 작업만 하면 끝...
			MySqlMemberDao memberDao=(MySqlMemberDao)sc.getAttribute("memberDao");
			Member member=memberDao.exist(request.getParameter("email"), request.getParameter("password"));
			
			if(member==null) {
				//로그인 실패
				//request.setAttribute("viewUrl", "redirect:list.do");
				request.setAttribute("viewUrl", "/auth/LogInFail.jsp");
				
			}else {
				//로그인 성공
				//HttpSession session = request.getSession();
				request.getSession().setAttribute("member", member);
				request.setAttribute("viewUrl", "redirect:../member/list.do");
			}
			
			//request.setAttribute("viewUrl", "redirect:list.do");
			
		} catch (Exception e) {
			//에러 처리또한 프론트컨트롤러에서 처리하므로 throw만 한다.
			System.out.println("MemberUpdateServlet doGet error : " + e.getMessage());
			throw new ServletException(e);
		} 		
		
		
		
	}

}

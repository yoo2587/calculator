package spms.servlets;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MySqlMemberDao;

/**
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		try {
//			ServletContext sc=this.getServletContext();
//			//DAO를 통해 데이터 처리만 한다.
//			//프론트컨트롤러에서 include 방식으로 호출 받았으므로 처리할 작업만 하면 끝...
//			MySqlMemberDao memberDao=(MySqlMemberDao)sc.getAttribute("memberDao");
//			request.setAttribute("members",memberDao.selectList());
//			//servlet입장에서 MyWeb02는 ContextPath에 해당한다.
//			//ServletPath는 ContextPath를 제외한다.
//			request.setAttribute("viewUrl", "/member/MemberList.jsp");
//			
//		} catch (Exception e) {
//			//에러 처리또한 프론트컨트롤러에서 처리하므로 throw만 한다.
//			System.out.println("MemberListServlet doGet error : " + e.getMessage());
//			throw new ServletException(e);
//		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

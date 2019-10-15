package spms.servlets;

import java.io.IOException;
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
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
        System.out.println("MemberDeleteServlet()");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("MemberDeleteServlet init");
		super.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	    try {
	        ServletContext sc = this.getServletContext();
	        MySqlMemberDao memberDao = (MySqlMemberDao)sc.getAttribute("memberDao");

//	        Member member = memberDao.selectOne(
//	            Integer.parseInt(request.getParameter("no")));

	        System.out.println("MemberDeleteServlet doGet no = " + request.getParameter("no") );
	        memberDao.delete(Integer.parseInt(request.getParameter("no")));
	        request.setAttribute("viewUrl", "redirect:list.do");
	        
//	        request.setAttribute("member", member);
//	        request.setAttribute("viewUrl", "/member/MemberUpdateForm.jsp");
	        
	        
	      } catch (Exception e) {
	    	  System.out.println("MemberDeleteServlet doGet error : " + e.getMessage());
	    	  throw new ServletException(e);
	      }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

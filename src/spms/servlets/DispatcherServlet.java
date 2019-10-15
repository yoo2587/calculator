package spms.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.controls.Controller;
import spms.dao.MySqlMemberDao;
import spms.listners.ContextLoaderListener;
import spms.bind.DataBinding;
import spms.bind.ServletRequestDataBinder;
import spms.context.ApplicationContext;
//import spms.controls.MemberAddController;
//import spms.controls.MemberListController;
import spms.controls.*;
import spms.vo.Member;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
    	super();
        // TODO Auto-generated constructor stub
    }
    
    private void prepareRequestData(HttpServletRequest request,
    		HashMap<String, Object> model, DataBinding dataBinding) throws Exception {
    	//배열에 변수명, 타입이 순서대로 들어 있다.
    	Object[] dataBinders=dataBinding.getDataBinders();
    	String dataName=null;
    	Class<?> dataType=null;
    	Object dataObj=null;
    	
    	for(int i=0;i<dataBinders.length;i+=2) {
    		//dataBinder에 변수명, 타입이 순서대로 들어 있다.
    		//변수명은 String이고 java에서는 Ref이므로 형변환해서 꺼낸다.
    		dataName=(String)dataBinders[i];
    		//dataType은 짝수번째에 들어 있고 class이므로 형변환한다. class로
    		dataType=(Class<?>)dataBinders[i+1];
    		
    		dataObj=ServletRequestDataBinder.bind(request, dataType, dataName);
    		model.put(dataName, dataObj);
    	}   	
    	
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String servletPath=request.getServletPath();
		
		try {
			System.out.println("servletPath=" + servletPath);
			
			//ServletContext sc = this.getServletContext();
			System.out.println("ContextLoaderListener.getApplicationContext();");
			ApplicationContext ctx=ContextLoaderListener.getApplicationContext();
			HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("session", request.getSession());
			
			//Controller pageController=(Controller) sc.getAttribute(servletPath);
			System.out.println("ctx.getBean(servletPath);");
			Controller pageController=(Controller) ctx.getBean(servletPath);
			if(pageController==null) {
				throw new Exception("요청한 서비스를 찾을 수 없습니다. : " + servletPath);
				
			}
			
			if(pageController instanceof DataBinding) {
				prepareRequestData(request, model, (DataBinding) pageController);
			}
			
			//pagecontroller를 execute한다. 즉 map객체(model)을 pagecontroller에게 넘겨서 데이터 작업을 의뢰한다. 그리고 view 주소도 반환 받는다. 
			String viewUrl=pageController.execute(model);
			for(String key:model.keySet()) {
				request.setAttribute(key, model.get(key));				
			}
			
			if(viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
				return;
			} else {
				RequestDispatcher rd=request.getRequestDispatcher(viewUrl);
				rd.include(request, response);				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd=request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);			
		}
	
		
		
//		response.setContentType("text/html; charset=UTF-8");
//		String servletPath=request.getServletPath();
//		
//		try {
//			//사용자로 부터 받은 요청 주소를 기준으로 필요한 정보 가공해서 페이지컨트롤를 통해 필요작업(데이터 작업) 후 뷰페이지에 이동 
//			String pageControllerPath=null;
//			if("/member/list.do".equals(servletPath)) {
//				pageControllerPath="/member/list";
//				
//			}else if("/member/add.do".equals(servletPath)) {
//				pageControllerPath="/member/add";
//				
//				if(request.getParameter("email")!=null) {
//					request.setAttribute("member", new Member()
//								.setEmail(request.getParameter("email"))
//								.setPassword(request.getParameter("password"))
//								.setName(request.getParameter("name")) );
//					
//				}
//				
//			}else if("/member/update.do".equals(servletPath)) {
//				pageControllerPath="/member/update";
//				
//				if(request.getParameter("email")!=null) {
//					request.setAttribute("member", new Member()
//								.setNo(Integer.parseInt(request.getParameter("no")))
//								.setEmail(request.getParameter("email"))
//								.setName(request.getParameter("name")) );
//					
//				}
//				
//			}else if("/member/delete.do".equals(servletPath)) {
//				
//				pageControllerPath="/member/delete";
//				
//			}else if("/auth/login.do".equals(servletPath)) {
//				pageControllerPath="/auth/login";
//				
//			}else if("/auth/logout.do".equals(servletPath)) {
//				pageControllerPath="/auth/logout";
//				
//			}
//			
//			//페이지 컨트롤러로 처리 이동
//			RequestDispatcher rd = request.getRequestDispatcher( pageControllerPath);
//			rd.include(request, response);	
//			
//			//페이지 컨트롤러에서 받아온 view 주소로 이동(데이터는 페이지 컨트롤러에서 DAO를 통해 받아온 상태)
//			String viewUrl=(String)request.getAttribute("viewUrl");
//			if(viewUrl.startsWith("redirect:")) {
//				response.sendRedirect(viewUrl.substring(9));
//				return;
//			}else {
//				rd=request.getRequestDispatcher(viewUrl);
//				rd.include(request, response);				
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			request.setAttribute("error", e);
//			RequestDispatcher rd=request.getRequestDispatcher("/Error.jsp");
//			rd.forward(request, response);
//		}
		
		
	}

}

package spms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MySqlMemberDao;
import spms.vo.Member;

@Component("/auth/login.do")
public class LogInController implements Controller, DataBinding {
	MySqlMemberDao memberDao;
	
	
	public LogInController() {
		// TODO Auto-generated constructor stub
	}

	public LogInController setMemberDao(MySqlMemberDao memberDao) {
		this.memberDao=memberDao;
		return this;		
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		//System.out.println("LoginController.execute");
		Member member=(Member)model.get("loginInfo");
		
		if(member.getEmail()==null) {
			return "/auth/LogInForm.jsp";
			
		}else {
			
			Member selMember = memberDao.exist(
					member.getEmail(), 
					member.getPassword());
		        
		        if (selMember != null) {
		          HttpSession session = (HttpSession)model.get("session");
		          session.setAttribute("member", selMember);
		          return "redirect:../member/list.do";
		        } else {
		          return "/auth/LogInFail.jsp";
		        }
		        
		}
		
//	    if (model.get("loginInfo") == null) { // 입력폼을 요청할 때
//	    	System.out.println("loginInfo is null");
//	    	
//	        return "/auth/LogInForm.jsp";
//	        
//	      } else { // 회원 등록을 요청할 때
//	    	  System.out.println("loginInfo is not null");
//	    	  
//	        //MemberDao memberDao = (MemberDao)model.get("memberDao"); 
//	        Member loginInfo = (Member)model.get("loginInfo");
//	        
//	        Member member = memberDao.exist(
//	            loginInfo.getEmail(), 
//	            loginInfo.getPassword());
//	        
//	        if (member != null) {
//	          HttpSession session = (HttpSession)model.get("session");
//	          session.setAttribute("member", member);
//	          return "redirect:../member/list.do";
//	        } else {
//	          return "/auth/LogInFail.jsp";
//	        }
//	      }		
		
	}

	@Override
	public Object[] getDataBinders() {
		// TODO Auto-generated method stub
		return new Object[] {"loginInfo", spms.vo.Member.class};
	}

}

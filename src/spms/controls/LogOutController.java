package spms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MySqlMemberDao;

@Component("/auth/logout.do")
public class LogOutController implements Controller{

	public LogOutController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=(HttpSession) model.get("session");
		session.invalidate();
		return "redirect:../member/list.do";
	}

//	@Override
//	public Object[] getDataBinders() {
//		// TODO Auto-generated method stub
//		return new Object[] {"session",HttpSession.class};
//	}

}

package spms.controls;

import java.util.HashMap;
import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MySqlMemberDao;

@Component("/member/list.do")
public class MemberListController implements Controller, DataBinding {

	MySqlMemberDao memberDao;
	
	public MemberListController() {
		// TODO Auto-generated constructor stub
	}

	public MemberListController setMemberDao(MySqlMemberDao memberDao) {
		//memberDao를 외부에서 주입 받는 방식으로 변경
		//IoC혹은 DI 방식
		this.memberDao=memberDao;
		return this;		
	}
	
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		HashMap<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("orderCond",model.get("orderCond"));
		
		model.put("members", this.memberDao.selectList(paramMap));
		return "/member/MemberList.jsp";
	}
	
	@Override
	public Object[] getDataBinders() {
		// TODO Auto-generated method stub
		return new Object[] {"orderCond", String.class};
	}
}

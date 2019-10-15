package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MySqlMemberDao;
import spms.vo.Member;

@Component("/member/delete.do")
public class MemberDeleteController implements Controller, DataBinding {
	MySqlMemberDao memberDao;
	
	public MemberDeleteController() {
		// TODO Auto-generated constructor stub
	}
	
	public MemberDeleteController setMemberDao(MySqlMemberDao memberDao) {
		this.memberDao=memberDao;
		return this;		
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub
		//MemberDao memberDao=(MemberDao)model.get("memberDao");
		memberDao.delete(Integer.parseInt(model.get("no").toString()));
		return "redirect:list.do";
	}

	@Override
	public Object[] getDataBinders() {
		// TODO Auto-generated method stub
		return new Object[] {"no", Integer.class};
	}

}

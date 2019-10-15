package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MySqlMemberDao;
import spms.vo.Member;

@Component("/member/add.do")
public class MemberAddController implements Controller, DataBinding {
	MySqlMemberDao memberDao;
	
	public MemberAddController() {
		// TODO Auto-generated constructor stub
	}
	
	public MemberAddController setMemberDao(MySqlMemberDao memberDao) {
		this.memberDao=memberDao;
		return this;		
	}
	

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub
		Member member=(Member)model.get("member");
		
		if(member.getEmail()==null) {
			return "/member/MemberForm.jsp";
		}else {
			memberDao.insert(member);
			return "redirect:list.do";			
		}
	}

	@Override
	public Object[] getDataBinders() {
		//MemberAddController의 경우 spms.vo.Member이라는 type에 member이라는 이름의 데이터를 map에 담아 줘야 한다.
		return new Object[] {"member", spms.vo.Member.class};
	}

}

package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MySqlMemberDao;
import spms.vo.Member;

@Component("/member/update.do")
public class MemberUpdateController implements Controller, DataBinding {
	MySqlMemberDao memberDao;
	
	public MemberUpdateController() {
		// TODO Auto-generated constructor stub
	}
	
	public MemberUpdateController setMemberDao(MySqlMemberDao memberDao) {
		this.memberDao=memberDao;
		return this;		
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		//getDataBinders 를 거치므로 member은 null은 아니다.
		Member member=(Member)model.get("member");
		
		if(member.getEmail()==null) {
	        Integer no = (Integer)model.get("no");
	        Member selMember = memberDao.selectOne(no);
	        model.put("member", selMember);			
			return "/member/MemberUpdateForm.jsp";

		}else {
			memberDao.update(member);
			return "redirect:list.do";			
		}
	}

	@Override
	public Object[] getDataBinders() {
		// TODO Auto-generated method stub
		//member은 항상 들어온다.
		return new Object[] {"no", Integer.class, "member", spms.vo.Member.class};
	}

}

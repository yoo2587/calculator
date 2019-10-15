package spms.controls;

import java.util.HashMap;
import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MySqlMemberDao;
import spms.dao.MySqlProjectDao;


@Component("/project/list.do")
public class ProjectListController implements Controller, DataBinding {
	
	MySqlProjectDao projectDao;
	
	public ProjectListController() {
		// TODO Auto-generated constructor stub
	}
	
	public ProjectListController setProjectDao(MySqlProjectDao projectDao) {
		this.projectDao=projectDao;
		return this;
	}
	
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		HashMap<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("orderCond",model.get("orderCond"));
		
		//projects에 projectList를 담는다.
		model.put("projects", this.projectDao.selectList(paramMap));
		//view page를 리턴한다.
		return "/project/ProjectList.jsp";

	}

	@Override
	public Object[] getDataBinders() {
		// TODO Auto-generated method stub
		return new Object[] {"orderCond", String.class};
	}

}

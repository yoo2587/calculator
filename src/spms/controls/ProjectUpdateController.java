package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MySqlMemberDao;
import spms.dao.MySqlProjectDao;
import spms.vo.Project;

@Component("/project/update.do")
public class ProjectUpdateController implements Controller, DataBinding {
	MySqlProjectDao projectDao;
	
	
	public ProjectUpdateController() {
	}
	
	public ProjectUpdateController setProjectDao(MySqlProjectDao projectDao) {
		this.projectDao=projectDao;
		return this;		
	}


	@Override
	public Object[] getDataBinders() {
		//나중에 model에 넣고 꺼낼 변수들 사전에 frontcontroller에게 알려주기 위한 용도
		return new Object[] {"no", Integer.class,  "project",spms.vo.Project.class};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		
		Project project =(Project) model.get("project");
		
		if(project.getTitle()==null) {
	        Integer no = (Integer)model.get("no");
	        Project selProject = projectDao.selectOne(no);
	        model.put("project", selProject);			
			return "/project/ProjectUpdateForm.jsp";
		}else {
			//project정보 update후 list화면으로 이동
			projectDao.update(project);
			return "redirect:list.do";
		}
		
	}

}

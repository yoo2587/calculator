package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MySqlProjectDao;

@Component("/project/delete.do")
public class ProjectDeleteController implements Controller, DataBinding {
	MySqlProjectDao projectDao;
	
	public ProjectDeleteController() {
		// TODO Auto-generated constructor stub
		System.out.println("ProjectDeleteController()");
	}

	public ProjectDeleteController setProjectDao(MySqlProjectDao projectDao) {
		
		System.out.println("ProjectDeleteController.setProjectDao");
		
		this.projectDao=projectDao;
		return this;		
	}
	
	
	@Override
	public Object[] getDataBinders() {
		
		System.out.println("ProjectDeleteController.getDataBinders");
		
		// TODO Auto-generated method stub
		return new Object[] {"no", Integer.class};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		System.out.println("ProjectDeleteController.execute");
		
		// TODO Auto-generated method stub
		Integer no = (Integer)model.get("no");
		projectDao.delete(no);
		
		return "redirect:list.do";
	}

}

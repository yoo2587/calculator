package spms.dao;

import java.util.HashMap;
import java.util.Hashtable;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import javax.sql.DataSource;


//mybatis 적용
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import spms.annotation.Component;
import spms.vo.Project;

@Component("projectDao")
public class MySqlProjectDao implements ProjectDao {
	SqlSessionFactory sqlSessionFactory;
	
	public MySqlProjectDao() {
		// TODO Auto-generated constructor stub
	}
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory=sqlSessionFactory;
	}
	
	@Override
	public List<Project> selectList(HashMap<String, Object> paramMap) throws Exception {
		
		SqlSession sqlSession=this.sqlSessionFactory.openSession();
		
		try {
			//여기서 parameter로 넘기는 spms.dao.ProjectDao.selectList는 sql mapper id
			return sqlSession.selectList("spms.dao.ProjectDao.selectList", paramMap);
		}finally {
			sqlSession.close();			
		}
	
	}

	@Override
	public int insert(Project project) throws Exception {
		
		SqlSession sqlSession =this.sqlSessionFactory.openSession();
		try {
			int count=sqlSession.insert("spms.dao.ProjectDao.insert", project);
			sqlSession.commit();
			return count;			
		}finally {
			sqlSession.close();
		}
	}

	@Override
	public int delete(int no) throws Exception {
		SqlSession sqlSession=this.sqlSessionFactory.openSession();
		
		try {
			int count=sqlSession.delete("spms.dao.ProjectDao.delete", no);
			sqlSession.commit();
			return count;			
		}finally {
			sqlSession.close();
			
		}
	}

	@Override
	public Project selectOne(int no) throws Exception {
		SqlSession sqlSession=this.sqlSessionFactory.openSession();		
		
		try {
			return sqlSession.selectOne("spms.dao.ProjectDao.selectOne", no);
		}finally {
			sqlSession.close();
		}
	}

	@Override
	public int update(Project project) throws Exception {
		SqlSession sqlSession=this.sqlSessionFactory.openSession();
		
		try {
			Project orgProject=sqlSession.selectOne("spms.dao.ProjectDao.selectOne", project.getNo());
			Hashtable<String, Object> paraMap=new Hashtable<String, Object>();
			if(!orgProject.getTitle().equals(project.getTitle())) {
				paraMap.put("title", project.getTitle());
			}
			if(!orgProject.getContent().equals(project.getContent())) {
				paraMap.put("content", project.getContent());
			}
			if(orgProject.getStartDate().compareTo(project.getStartDate()) !=0) {
				paraMap.put("startDate", project.getStartDate());
			}
			if(orgProject.getEndDate().compareTo(project.getEndDate()) !=0) {
				paraMap.put("endDate", project.getEndDate());
			}
			if(orgProject.getState() != project.getState()) {
				
				paraMap.put("state", project.getState());
			}
			if(!orgProject.getTags().equals(project.getTags())) {
				paraMap.put("tags", project.getTags());
			}
			
			if(paraMap.size()!=0) {
				paraMap.put("no", project.getNo());
				int count=sqlSession.update("spms.dao.ProjectDao.update", paraMap);
				sqlSession.commit();
				return count;
			}
			else
			{
				return 0;
			}			
		}finally {
			sqlSession.close();
		}
	}

}

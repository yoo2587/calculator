package spms.dao;


import java.util.HashMap;
import java.util.Hashtable;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import spms.annotation.Component;
//import spms.util.DBConnectionPool;
import spms.vo.Member;
import spms.vo.Project;

@Component("memberDao")
public class MySqlMemberDao implements MemberDao {
	SqlSessionFactory sqlSessionFactory;
	
	public MySqlMemberDao() {
		// TODO Auto-generated constructor stub
	}
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory=sqlSessionFactory;
	}
	
	@Override
	public List<Member> selectList(HashMap<String, Object> paramMap) throws Exception{
		SqlSession sqlSession=this.sqlSessionFactory.openSession();
		
		try {
			//여기서 parameter로 넘기는 spms.dao.ProjectDao.selectList는 sql mapper id
			System.out.println("selectList, paramMap.size()=" + paramMap.size());
			System.out.println("selectList, paramMap.orderCond=" + paramMap.get("orderCond"));
			
			return sqlSession.selectList("spms.dao.MemberDao.selectList", paramMap);
		}finally {
			sqlSession.close();			
		}		
	}
	
	@Override
	public int insert(Member member) throws Exception  {
		
		SqlSession sqlSession =this.sqlSessionFactory.openSession();
		try {
			int count=sqlSession.insert("spms.dao.MemberDao.insert", member);
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
				int count=sqlSession.delete("spms.dao.MemberDao.delete", no);
				sqlSession.commit();
				return count;			
			}finally {
				sqlSession.close();
				
			}
	  }

	  @Override
	  public Member selectOne(int no) throws Exception { 
			SqlSession sqlSession=this.sqlSessionFactory.openSession();		
			
			try {
				return sqlSession.selectOne("spms.dao.MemberDao.selectOne", no);
			}finally {
				sqlSession.close();
			}
	  }
	  
	  @Override
	  public int update(Member member) throws Exception { 
		  SqlSession sqlSession=this.sqlSessionFactory.openSession();
			
			try {
				
				System.out.println("sqlSession.selectOne");
				
				Member orgMember=sqlSession.selectOne("spms.dao.MemberDao.selectOne", member.getNo());
				Hashtable<String, Object> paraMap=new Hashtable<String, Object>();
				if(!orgMember.getEmail().equals(member.getEmail())) {
					System.out.println("put email");
					
					paraMap.put("email", member.getEmail());
				}
//				if(!orgMember.getPassword().equals(member.getPassword())) {
//					paraMap.put("password", member.getPassword());
//				}
				if(!orgMember.getName().equals(member.getName())) {
					System.out.println("put name");
					
					paraMap.put("name", member.getName());
				}
				
				if(paraMap.size()!=0) {
					System.out.println("update");
					
					
					paraMap.put("no", member.getNo());
					int count=sqlSession.update("spms.dao.MemberDao.update", paraMap);
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
	  
	  @Override
	  public Member exist(String email, String password) throws Exception {
			SqlSession sqlSession=this.sqlSessionFactory.openSession();		
			
			try {
				Hashtable<String, Object> paraMap=new Hashtable<String, Object>();
				paraMap.put("email",  email);
				paraMap.put("password",  password);
				
				return sqlSession.selectOne("spms.dao.MemberDao.exist", paraMap);
			}finally {
				sqlSession.close();
			}	    
	  }
	
}

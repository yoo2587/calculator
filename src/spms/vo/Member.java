package spms.vo;

import java.util.Date;


public class Member {

	protected int no;
	protected String name, email, password;
	protected Date createdDate, modifiedDate;
	
	public int getNo() {
		return no;
	}
	
	public Member setNo(int no) {
		this.no=no;
		return this;		
	}
	
	public String getName() {
		return name;
	}
	
	public Member setName(String name) {
		this.name=name;
		return this;
	}

	public String getEmail() {
		return email;
	}
	
	public Member setEmail(String email) {
		this.email=email;
		return this;
	}

	
	public String getPassword() {
		return password;
	}
	
	public Member setPassword(String password) {
		this.password=password;
		return this;
	}

	
	public Date getCreatedDate() {
		return this.createdDate;
	}
	
	public Member setCreatedDate(Date createdDate) {
		this.createdDate=createdDate;
		return this;
		
	}
	
	
	public Date getModifiedDate() {
		return this.modifiedDate;
	}
	
	public Member setModifiedDate(Date modifiedDate) {
		this.modifiedDate=modifiedDate;
		return this;
		
	}
	
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

}

package edu.login.Action;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements ServletRequestAware {
	private String userName;
	private String password;
	private HttpServletRequest request;
	private ResultSet rs=null;
	private String message=ERROR;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request=arg0;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	@Override
	public void validate() {
		if(null==this.getUserName() || 0==this.getUserName().length() ){
			addFieldError("username","请输入登录名字!");
		}
		else{
			
		}
	}
	
}

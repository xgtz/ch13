package edu.login.Action;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import DBJavaBean.DB;

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
		DB mysql=new DB();
		//调用DB类中的方法，实现登录有关操作
		String add=mysql.addList(request, getUserName());
		if(add.equals("ok")){
			message=SUCCESS;
		}
		return message;
	}
	@Override
	public void validate() {
		if(null==this.getUserName() || 0==this.getUserName().length() ){
			addFieldError("userName","请输入登录名字!");
		}
		else{
			DB mysql=new DB();
			rs=mysql.selectMess(request, this.getUserName());
			try {
				if(!rs.next()){
					addFieldError("userName","此用户尚未注册!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(null==getPassword() ||0==getPassword().length()){
			addFieldError("password","请输入登录密码!");
		}
		else{
			DB mysql=new DB();
			rs=mysql.selectMess(request, getUserName());
			try {
				if(rs.next()){
					rs=mysql.selectLogin(request, getUserName(), getPassword());
					if(!rs.next())
					{
						addFieldError("password","登录密码错误!");
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
}

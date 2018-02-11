package edu.login.Action;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import DBJavaBean.DB;

import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport implements ServletRequestAware {
	private String userName;
	private String password1;
	private String password2;
	private String name;
	private String sex;
	private String birth;
	private String nation;
	private String edu;
	private String work;
	private String phone;
	private String place;
	private String email;
	private ResultSet rs=null;
	private String message=ERROR;
	private HttpServletRequest request;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getEdu() {
		return edu;
	}
	public void setEdu(String edu) {
		this.edu = edu;
	}
	
	
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request=arg0;
		
	}
	@Override
	public String execute() throws Exception {
		DB mysql =new DB();
		String mess=mysql.insertMess(request, userName, password1, name, sex, birth,
				nation, edu, work, phone, place, email);
		if(mess.equals("ok")){
			message=SUCCESS;
		}
		else if(mess.equals("one")){
			message=INPUT;
		}
		return message;
	}
	@Override
	public void validate() {
		if(getUserName()==null || getUserName().length()==0)
		{
			addFieldError("userName","登录名字不允许为空!");
		}
		else{
				DB mysql=new DB();
				rs=mysql.selectMess(request, getUserName());
				try {
					if(rs.next())
					{
						addFieldError("userName","此登录名已经存在!");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		if(getPassword1()==null || getPassword1().length()==0){
			addFieldError("password1","登录密码不允许为空!");
		}
		if(getPassword2()==null || getPassword2().length()==0){
			addFieldError("password2","重复密码不能为空!");
		}
		if(!(getPassword1().equals(getPassword2()))){
			addFieldError("password2","两次密码不一致");
		}
		if(getName()==null || getName().length()==0){
			addFieldError("name","用户姓名不允许为空");
		}
		if(getBirth()==null || getBirth().length()==0 || getBirth().equals("yyyy-mm-dd")){
			addFieldError("birth","用户生日不允许为空!");
		}
		else{
			if(getBirth().length()!=10){
				addFieldError("birth","用户生日格式为'yyyy-mm-dd'!");
			}else{
				String an=getBirth().substring(4,5);
				String bn=getBirth().substring(7,8);
				if(!(an.equals("-")) || !(bn.equals("-"))){
					addFieldError("birth","用户生日格式为'yyyy-mm-dd'!");
				}
			}
		}
		if(getNation()==null || getNation().length()==0){
			addFieldError("nation","用户名族不允许为空!");
		}
		if(getEdu().equals("1")){
			addFieldError("edu","情选择用户学历!");
		}
		if(getWork().equals("1")){
			addFieldError("work","请选择用户工作!");
		}
		if(getPhone()==null || getPhone().length()==0){
			addFieldError("phone","用户电话不允许为空!");
		}
		if(getPlace()==null || getPlace().length()==0){
			addFieldError("place","用户地址不允许为空!");
		}
		if(getEmail()==null || getEmail().length()==0){
			addFieldError("email","用户email不允许为空!");
		}
		
	}
	
	
	
}

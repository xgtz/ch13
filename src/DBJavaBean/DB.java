package DBJavaBean;

import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import org.apache.struts2.interceptor.ServletRequestAware;

// 以IOC方式直接访问Servlet,通过request获取session
public class DB implements ServletRequestAware {
	private String driverName="com.mysql.jdbc.Driver";
	/*url后面的"?useUnicode=true&characterEncoding=gbk",可以处理在想数据中添加数据时出现的乱码问题*/
	private String url="jdbc:mysql://localhost:3306/personmessage?useUnicode=true&characterEncoding=gbk";
	private String user="root";
	private String password="password";
	private Connection con=null;
	private Statement st=null;
	private ResultSet rs=null;
	private HttpServletRequest request;
	
	public DB() {
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request=arg0;
	}
	
	// 完成连接数据库操作，生成容器并返回
	public Statement getStatement(){
		
		try {
			Class.forName(this.getDriverName());
			con=DriverManager.getConnection(getUrl(),getUser(),getPassword());
			return con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	// 完成注冊，把用戶信息录入到数据库中
	public String insertMess(HttpServletRequest request,String userName,String password,
			String name,String sex,String birth,String nation,String edu,String work,
			String phone,String place,String email){
		String sure=null;
		
		rs=selectMess(request,userName);
		try {
			if(rs.next())
			{
				sure="one";
			}
			else{
				String sql="insert into user" +
				"(userName,password,name,sex,birth,nation,edu,work,phone,place,email)" + 
				" values " +
				"('"+userName+"','"+password+"','"+name+"','"+sex+"','"+birth+"',"
						+ "'"+nation+"','"+edu+"','"+work+"','"+phone+"','"+place+"','"+email+"')";
				st=getStatement();
				int row=st.executeUpdate(sql);
				if(row==1)
				{
					String mess=myMessage(request,userName);
					if(mess.equals("ok")){
						sure="ok";
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sure;
	}
	
	// 更新个人信息
	public String updateMess(HttpServletRequest request,String userName,String password,
			String name,String sex,String birth,String nation,String edu,String work,
			String phone,String place,String email)
	{
		return null;

	}
	
	//　查询个人信息，并返回结果集rs
	public ResultSet selectMess(HttpServletRequest request,String userName){
		String sql="select * from user where userName='"+userName+"'";
		st=getStatement();
		try {
			return st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//把个人信息通过myMessBean保存到session对象中
	public String myMessage(HttpServletRequest request, String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	// 添加联系人
	public String inserFri(HttpServletRequest request,String userName,String name,String phone,String email,
			String workplace,String place,String QQ)
	{
		
		return null;
	}
	
	// 删除联系人
	public String deleteFri(HttpServletRequest request,String userName,String name){
		return null;
	}
	// 修改联系人
	public String updateFri(HttpServletRequest request,String userName,String name,String phone,String email,
			String workplace,String place,String QQ){
		return null;
	}
	// 查询联系人
	public ResultSet selectFri(HttpServletRequest request,String userName,String name){
		return null;
	}
	// 获取通讯录中所有联系人信息
	public ResultSet selectFriAll(HttpServletRequest request,String userName){
		return null;
	}
	// 获取通讯录中所有联系人并把它们保存到session中
	public String myFriends(HttpServletRequest request,String userName){
		return null;
	}
	// 添加日程
	public String insertDay(HttpServletRequest request,String userName,String date,String thing){
		return null;
	}
	// 删除日程
	public String deleteDay(HttpServletRequest request,String username,String date){
		return null;
	}
	// 修改日程
	public String updateDay(HttpServletRequest request,String userName,String day,String date,String thing){
		return null;
	}
	// 查询日程
	public String selectDay(HttpServletRequest request,String userName,String date){
		return null;
	}
	// 查询所有日程信息
	public ResultSet selectDayAll(HttpServletRequest request,String userName){
		return null;
	}
	// 查询所有日程信息，并把它们保存到session中
	public String myDayTime(HttpServletRequest request,String userName){
		return null;
	}
	// 保存上传的文件信息
	public String insertFile(HttpServletRequest request,String userName,String title,String name,
			String contentType,String size,String filePath){
		return null;
	}
	// 删除文件信息
	public String deleteFile(HttpServletRequest request,String userName,String title){
		return null;
	}
	// 修改文件
	public String updateFile(HttpServletRequest request,String userName,String title,String name,
			String contentType,String size,String filePath){
		return null;
	}
	// 查询文件
	public ResultSet selectFile(HttpServletRequest request,String userName,String type,String name){
		return null;
	}
	// 查询所有文件
	public ResultSet selectFileAll(HttpServletRequest request,String userName){
		return null;
	}
	// 查询所有的文件信息，并把它们保存到session中
	public String myFile(HttpServletRequest request,String userName){
		return null;
	}
	// 查询登录用户名和密码是否正确
	public ResultSet selectLogin(HttpServletRequest request,String userName,String password){
		return null;
	}
	// 把登陆的用户信息保存到session中
	public String myLogin(HttpServletRequest request,String userName){
		return null;
	}
	// 返回登录用户的用户名
	public String returnLogin(HttpServletRequest request){
		return null;
	}
	
	/*调用myLogin(),myMessage(),myFriends(),myDateTime(),myFile()方法，把所有和用户
	有关的信息全部保存到session对象中，该方法登录成功后调用*/
	public String addList(HttpServletRequest request,String userName){
		return null;
	}
	
	// 修改用户密码
	public String updatePass(HttpServletRequest request,String userName,String password){
		return null;
	}
	//查找联系人，并将其信息保存到session中
	public String findFri(HttpServletRequest request,String userName,String name){
		return null;
	}
	// 从查找到的联系人session中获取联系人姓名并返回
	public String returnFri(HttpServletRequest request){
		return null;
	}
	// 查找日程，并把日程信息保存到session中
	public String findDay(HttpServletRequest request,String userName,String date){
		return null;
	}
	// 从查找到的session中获取日程信息,并返回
	public String returnDay(HttpServletRequest request){
		return null;
	}
	// 查找文件信息，并把文件的信息保存到session中
	public String findFile(HttpServletRequest request,String userName,String title){
		return null;
	}
	//根据不同的条件，从查找的文件session中获取相应的文件信息
	public String returnFile(HttpServletRequest request,String face){
		return null;
	}
	// 一个带参数的信息提示框，供调试使用
	public void message(String msg){
		int type=JOptionPane.YES_NO_OPTION;
		String title="信息提示";
		JOptionPane.showMessageDialog(null, msg,title,type);
	}
	
	

}

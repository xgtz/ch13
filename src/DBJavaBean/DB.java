package DBJavaBean;

import java.sql.*;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.apache.struts2.interceptor.ServletRequestAware;

import JavaBean.MyDayBean;
import JavaBean.MyFileBean;
import JavaBean.MyFriBean;
import JavaBean.MyMessBean;

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
	
	// 更新注册的个人信息
	public String updateMess(HttpServletRequest request,String userName,String password,
			String name,String sex,String birth,String nation,String edu,String work,
			String phone,String place,String email)
	{
		String sure=null;
		String sql="update user set name='"+name+"',sex='"+sex+"',birth='"+birth+"',"
				+ "nation='"+nation+"',edu='"+edu+"',work='"+work+"',phone='"+phone+"',"
				+ "place='"+place+"',email='"+email+"' where userName='"+userName+"'";
		st=getStatement();
		int row;
		try {
			row = st.executeUpdate(sql);
			if(row==1)
			{
				String mess=myMessage(request,userName);
				if(mess.equals("ok")){
					sure="ok";
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sure;

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
		ArrayList listName=null;
		HttpSession session=request.getSession();
		listName=new ArrayList();
		rs=selectMess(request,userName);
		try {
			while(rs.next()){
				MyMessBean mess=new MyMessBean();
				mess.setName(rs.getString("name"));
				mess.setSex(rs.getString("sex"));
				mess.setBirth(rs.getString("birth"));
				mess.setNation(rs.getString("nation"));
				mess.setEdu(rs.getString("edu"));
				mess.setWork(rs.getString("work"));
				mess.setPhone(rs.getString("phone"));
				mess.setPlace(rs.getString("place"));
				mess.setEmail(rs.getString("email"));
				listName.add(mess);
				session.setAttribute("MyMess", listName);
			}
			return "ok";
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 添加联系人
	public String insertFri(HttpServletRequest request,String userName,String name,String phone,String email,
			String workplace,String place,String QQ)
	{
		String sure=null;
		rs=selectFri(request,userName,name);
		try {
			if(rs.next()){
				sure="one";
			}
			else{
				String sql="insert into friends(userName,name,phone,email,workplace,place,QQ) values "
						+ "('"+userName+"','"+name+"','"+phone+"','"+email+"','"+workplace+"','"+place+"','"+QQ+"')";
				st=getStatement();
				int row=st.executeUpdate(sql);
				if(row==1){
					String fri=myFriends(request,userName);
					if(fri.equals("ok")){
						sure="ok";
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sure;
	}
	
	// 删除联系人
	public String deleteFri(HttpServletRequest request,String userName,String name){
		String sure=null;
		String sql="delete from friends where userName='"+userName+"' and name='"+name+"'";
		st=getStatement();
		int row;
		try {
			row = st.executeUpdate(sql);
			if(row==1){
				String fri=myFriends(request,userName);
				if(fri.equals("ok")){
					sure="ok";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	// 修改联系人
	public String updateFri(HttpServletRequest request,String userName,
			String friendName,String name,String phone,String email,
			String workplace,String place,String QQ){
		String sure=null;
		//先删除该联系人的信息
		String del=deleteFri(request,userName,friendName);
		if(del.equals("ok"))
		{
			String in=insertFri(request,userName,name,phone,email,workplace,place,QQ);
			if(in.equals("ok")){
				String fri=myFriends(request,userName);
				if(fri.equals("ok"))
				{
					sure="ok";
				}
			}
		}
		return sure;
	}
	// 查询联系人
	public ResultSet selectFri(HttpServletRequest request,String userName,String name){
		String sql="select * from friends where userName='"+userName+"' and name='"+name+"' ";
		st=getStatement();
		try {
			return st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	// 获取通讯录中所有联系人信息
	public ResultSet selectFriAll(HttpServletRequest request,String userName){
		String sql="select * from friends where userName='"+userName+"'";
		st=getStatement();
		try {
			return st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	// 获取通讯录中所有联系人并把它们保存到session中
	public String myFriends(HttpServletRequest request,String userName){
		ArrayList listName=null;
		HttpSession session=request.getSession();
		listName=new ArrayList();
		rs=selectFriAll(request,userName);
		try {
			if(rs.next()){
				rs=selectFriAll(request,userName);
				while(rs.next()){
					MyFriBean mess=new MyFriBean();
					mess.setName(rs.getString("name"));
					mess.setPhone(rs.getString("phone"));
					mess.setEmail(rs.getString("email"));
					mess.setWorkplace(rs.getString("workplace"));
					mess.setPlace(rs.getString("place"));
					mess.setQQ(rs.getString("QQ"));
					listName.add(mess);
					session.setAttribute("friends", listName);
				}
			}
			else{
				session.setAttribute("friends", listName);
			}
			return "ok";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	// 添加日程
	public String insertDay(HttpServletRequest request,String userName,String date,String thing){
		String sure=null;
		rs=selectDay(request,userName,date);
		try {
			if(rs.next()){
				sure="one";
			}
			else{
				String sql="insert into date(userName,date,thing) values('"+userName+"','"+date+"','"+thing+"')";
				st=getStatement();
				int row=st.executeUpdate(sql);
				if(row==1){
					String day=myDayTime(request,userName);
					if(day.equals("ok")){
						sure="ok";
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sure;
	}
	// 删除日程
	public String deleteDay(HttpServletRequest request,String userName,String date){
		String sure=null;
		String sql="delete from date where userName='"+userName+"' and date='"+date+"'";
		st=getStatement();
		int row;
		try {
			row = st.executeUpdate(sql);
			if(row==1)
			{
				String day=myDayTime(request,userName);
				if(day.equals("ok")){
					sure="ok";
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sure;
	}
	// 修改日程
	public String updateDay(HttpServletRequest request,String userName,String day,String date,String thing){
		String sure=null;
		String del=deleteDay(request,userName,day);
		if(del.equals("ok")){
			String in = insertDay(request,userName,date,thing);
			if(in.equals("ok")){
				String result=myDayTime(request,userName);
				if(result.equals("ok")){
					sure="ok";
				}
			}
		}
		return sure;
	}
	// 查询日程
	public ResultSet selectDay(HttpServletRequest request,String userName,String date){
		String sql="select * from date where userName='"+userName+"' and date='"+date+"'";
		st=getStatement();
		try {
			return st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	// 查询所有日程信息
	public ResultSet selectDayAll(HttpServletRequest request,String userName){
		String sql="select * from date where userName='"+userName+"'";
		st=getStatement();
		try {
			return st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	// 查询所有日程信息，并把它们保存到session中
	public String myDayTime(HttpServletRequest request,String userName){
		ArrayList listName=null;
		HttpSession session=request.getSession();
		listName=new ArrayList();
		rs=selectDayAll(request,userName);
		try {
			if(rs.next())
			{
				rs=selectDayAll(request,userName);
				while(rs.next()){
					MyDayBean bean=new MyDayBean(); 
					bean.setDay(rs.getString("date"));
					bean.setThing(rs.getString("thing"));
					listName.add(bean);
					session.setAttribute("day", listName);
				}
			}
			else{
				session.setAttribute("day", listName);
			}
			return "ok";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	// 保存上传的文件信息
	public String insertFile(HttpServletRequest request,String userName,String title,String name,
			String contentType,String size,String filePath){
		String sure=null;
		rs=selectFile(request,userName,"title",title);
		try {
			if(rs.next()){
				sure="title";
			}
			else{
				rs=selectFile(request,userName,"name",name);
				if(rs.next()){
					
					sure="name";
				}
				else{
					String sql="insert into file (userName,title,name,contentType,size,filePath) values "
							+ "('"+userName+"','"+title+"', '"+name+"','"+contentType+"',"
									+ "'"+size+"','"+filePath+"')";
					st=getStatement();
					int row=st.executeUpdate(sql);
					if(row==1){
						String file=myFile(request,userName);
						if(file.equals("ok")){
							sure="ok";
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sure;
	}
	// 删除文件信息
	public String deleteFile(HttpServletRequest request,String userName,String title){
		String sure=null;
		String sql="delete from file where userName='"+userName+"' and title='"+title+"' ";
		st=getStatement();
		int row;
		try {
			row = st.executeUpdate(sql);
			if(row==1){
				String file=myFile(request,userName);
				if(file.equals("ok")){
					sure="ok";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sure;
	}
	// 修改文件
	public String updateFile(HttpServletRequest request,String userName,String title,String name,
			String contentType,String size,String filePath){
		String sure=null;
		String del=deleteFile(request,userName,title);
		if(del.equals("ok")){
			String in=insertFile(request,userName,title,name,contentType,size,filePath);
			if(in.equals("ok")){
				String file=myFile(request,userName);
				if(file.endsWith("ok")){
					sure="ok";
				}
			}
		}
		return sure;
	}
	// 查询文件
	public ResultSet selectFile(HttpServletRequest request,String userName,String type,String name){
		String sql="select * from file where userName='"+userName+"' "
				+ " and contentType='"+type+"' and name='"+name+"' ";
		st=getStatement();
		try {
			return st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	// 查询所有文件
	public ResultSet selectFileAll(HttpServletRequest request,String userName){
		String sql="select * from file where userName='"+userName+"' ";
		st=getStatement();
		try {
			return st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	// 查询所有的文件信息，并把它们保存到session中
	public String myFile(HttpServletRequest request,String userName){
		ArrayList listName=null;
		HttpSession session=request.getSession();
		listName=new ArrayList();
		rs=selectFileAll(request,userName);
		try {
			if(rs.next())
			{
				rs=selectFileAll(request,userName);
				while(rs.next())
				{
					MyFileBean bean=new MyFileBean();
					bean.setTitle(rs.getString("title"));
					bean.setName(rs.getString("name"));
					bean.setContentType(rs.getString("contentType"));
					bean.setSize(rs.getString("size"));
					bean.setFilePath(rs.getString("filePath"));
					listName.add(bean);
					session.setAttribute("file", listName);
				}
			}
			else{
				session.setAttribute("file", listName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "ok";
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

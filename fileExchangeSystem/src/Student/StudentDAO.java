package Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	final private static String dbname = "student";   // データベース名
	final private static String user = "wspuser";      // studentにアクセスできるユーザ
	final private static String password = "hogehoge"; // userのパスワード
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;
    public Student student = new Student();

    Connection connection;
    String sql_checkID = "select * from member where mailaddress=?";
 	String sql_checkid ="select * from member where studentid=?";
 	String sql_checkname ="select * from member where username=?";
 	String sql_MIDList = "select * from member where masterflag='1' order by studentid asc";
 	String sql_SIDList = "select * from member where masterflag='0' order by studentid asc";
 	String sql_changeName ="update member set username=? where mailaddress=?";
 	String sql_changeCC ="update member set creditcard=? where mailaddress=?";
 	String sql_changePass ="update member set pass=? where mailaddress=?";
	 PreparedStatement prepStmt; // SELECT name 用 (リスト表示)
	    PreparedStatement prepStmt_S; // SELECT用
	    PreparedStatement prepStmt_I; // INSERT用
	    PreparedStatement prepStmt_U; // UPDATE用
	    PreparedStatement prepStmt_D; // DELETE用

	    String selectStr = "SELECT * FROM maember";
	    String strPrepSQL_SM = "SELECT * FROM member WHERE  masterflag='1' and studentid like '%?%'";
	    String strPrepSQL_SS = "SELECT * FROM member WHERE  masterflag='0' and studentid like ?";
	    String strPrepSQL_I = "INSERT INTO member VALUES(?, ?, ? ,? ,? ,'名無し',?,?,?)";
	    String strPrepSQL_U =
	       "UPDATE member SET mailaddress = ?, password = ?, studentid = ? WHERE studentid = ?";
	    String strPrepSQL_D = "DELETE FROM member WHERE studentid = ?";

	public boolean check(Student student) throws SQLException {

		// studentがDBにあるかどうかを調べる
		boolean result = false;
		Connection connection;
		String sql = "select * from member where mailaddress=? and pass=?";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, student.getMailAddress());
			pstmt.setString(2, student.getPassword());

			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				result = true;
				student.setName(resultSet.getString("name"));
				student.setGarade(resultSet.getInt("grade"));
				student.setSubject(resultSet.getString("subject"));
				student.setCreditCard(resultSet.getString("creditcard"));
				student.setUserName(resultSet.getString("username"));
				student.setStudentID(resultSet.getString("studentid"));
				student.setMasterflag(resultSet.getInt("masterflag"));
				this.student=student;

			}

			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public List<Student>getMList(){
		List<Student>list=new ArrayList<>();
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			prepStmt = connection.prepareStatement(sql_MIDList);

			ResultSet resultSet = prepStmt.executeQuery();
			while(resultSet.next()) {
				student=new Student();
				student.setName(resultSet.getString("name"));
				student.setGarade(resultSet.getInt("grade"));
				student.setMailAddress(resultSet.getString("mailaddress"));
				student.setCreditCard(resultSet.getString("creditcard"));
				student.setUserName(resultSet.getString("username"));
				student.setPassword(resultSet.getString("pass"));
				student.setStudentID(resultSet.getString("studentid"));
				student.setMasterflag(resultSet.getInt("masterflag"));
				list.add(student);
			}
			resultSet.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;

	}

	public List<Student>getSList(){
		List<Student>list=new ArrayList<>();
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			prepStmt = connection.prepareStatement(sql_SIDList);

			ResultSet resultSet = prepStmt.executeQuery();
			while(resultSet.next()) {
				student=new Student();
				student.setName(resultSet.getString("name"));
				student.setGarade(resultSet.getInt("grade"));
				student.setMailAddress(resultSet.getString("mailaddress"));
				student.setCreditCard(resultSet.getString("creditcard"));
				student.setUserName(resultSet.getString("username"));
				student.setPassword(resultSet.getString("pass"));
				student.setStudentID(resultSet.getString("studentid"));
				student.setMasterflag(resultSet.getInt("masterflag"));
				list.add(student);
			}
			resultSet.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;

	}

	public boolean addID(Student student){
		boolean result = false;
		if(checkIDbyMail(student)==false&&checkIDbyid(student)==false){
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			prepStmt_I = connection.prepareStatement(strPrepSQL_I);

			prepStmt_I.setString(1, student.getName());
			prepStmt_I.setInt(2, student.getGrade());
			prepStmt_I.setString(3,student.getSubject());
			prepStmt_I.setString(4,student.getMailAddress());
			prepStmt_I.setString(5,student.getCreditCard());
			prepStmt_I.setString(6,student.getPassword());
			prepStmt_I.setString(7,student.getStudentID());
			prepStmt_I.setInt(8,student.getMasterflag());
            prepStmt_I.executeUpdate();
            prepStmt_I.close();
            result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		}
		return result;
	}

	public boolean checkIDbyMail(Student student){
		boolean result=false;
		try{
   		 Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			PreparedStatement pstmt = connection.prepareStatement(sql_checkID);

			pstmt.setString(1, student.getMailAddress());
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				result = true;
			}
   		 resultSet.close();
			connection.close();
   	 }catch(Exception e){
   		 e.printStackTrace();
   	 }
		return result;
	}

	public boolean checkIDbyid(Student student){
		boolean result=false;
		try{
   		 Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			PreparedStatement pstmt = connection.prepareStatement(sql_checkid);

			pstmt.setString(1, student.getStudentID());
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				result = true;
			}
   		 resultSet.close();
			connection.close();
   	 }catch(Exception e){
   		 e.printStackTrace();
   	 }
		return result;
	}

	public boolean deleteID(Student student){
		boolean result = false;
		try{

			  Class.forName(driverClassName);
				connection = DriverManager.getConnection(url, user,password);
				PreparedStatement prepStmt_D = connection.prepareStatement(strPrepSQL_D);

				prepStmt_D.setString(1, student.getStudentID());
				prepStmt_D.executeUpdate();
				prepStmt_D.close();

		  }catch(Exception e){
			  e.printStackTrace();
		  }

		return result;
	}


	public boolean updateUserName(Student student){
		boolean result=false;
		try{
   		 Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			PreparedStatement pstmt = connection.prepareStatement(sql_changeName);

			pstmt.setString(1, student.getUserName());
			pstmt.setString(2, student.getMailAddress());
			pstmt.executeUpdate();
			pstmt.close();

			connection.close();
   	 }catch(Exception e){
   		 e.printStackTrace();
   	 }
		return result;
	}

	public boolean updateCC(Student student){
		boolean result=false;
		try{
   		 Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			PreparedStatement pstmt = connection.prepareStatement(sql_changeCC);

			pstmt.setString(1, student.getCreditCard());
			pstmt.setString(2, student.getMailAddress());
			pstmt.executeUpdate();
			pstmt.close();

			connection.close();
   	 }catch(Exception e){
   		 e.printStackTrace();
   	 }
		return result;
	}

	public boolean updatePass(Student student){
		boolean result=false;
		try{
   		 Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			PreparedStatement pstmt = connection.prepareStatement(sql_changePass);

			pstmt.setString(1, student.getPassword());
			pstmt.setString(2, student.getMailAddress());
			pstmt.executeUpdate();
			pstmt.close();

			connection.close();
   	 }catch(Exception e){
   		 e.printStackTrace();
   	 }
		return result;
	}

	public static void main(String[] args){
		
		StudentDAO dao = new StudentDAO(); 
		List<Student> s  = new ArrayList<>();
		s = dao.getMList();
		for(Student m : s){
			System.out.println(m.getName());
		}
	}





	public Student getStudent(){
		return student;
	}
}
package Inquiry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class InquiryDAO {
	final private static String dbname = "student";   // データベース名
	final private static String user = "wspuser";      // inquiryにアクセスできるユーザ
	final private static String password = "hogehoge"; // userのパスワード
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;
	public Inquiry inquiry = new Inquiry();
	Connection connection;
	String sql_subject ="select * from inquiry order by inquiryid desc";
	String sql_content ="select * from inquiry where inquiryid =?";
	String sql_send ="insert into inquiry values(?,?,?,?,?)";
	String sql_delete="delete from inquiry where inquiryid =?";
	String sql_checkID ="select * from inquiry where inquiryid=?";
	String sql_deleteID_1 ="delete from inquiry where inquiryid =?";
	String sql_deleteID_2 ="select * from inquiry where inquiryid::integer>=?";
	String sql_deleteID_3 ="update inquiry set inquiryid=? where inquiryid=?";
	 PreparedStatement prepStmt; // SELECT name 用 (リスト表示)
	 PreparedStatement prepStmt_I; // INSERT用
	 PreparedStatement prepStmt_S; // SELECT用
	 PreparedStatement prepStmt_D; // DELETE用
	public List<Inquiry>getList(){
		List<Inquiry>list=new ArrayList<>();
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			prepStmt = connection.prepareStatement(sql_subject);

			ResultSet resultSet = prepStmt.executeQuery();
			while(resultSet.next()) {
				inquiry=new Inquiry();
				inquiry.setSubjectOfQuestion(resultSet.getString("subject"));
				inquiry.setDate(resultSet.getString("date"));
				inquiry.setStudent(resultSet.getString("studentid"));
				inquiry.setInquiryID(resultSet.getInt("inquiryid"));
				inquiry.setQuestionContent(resultSet.getString("question"));

				list.add(inquiry);
			}
			resultSet.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;

	}
	public List<Inquiry> getQList(Inquiry inquiry){
		List<Inquiry>list=new ArrayList<>();
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			prepStmt = connection.prepareStatement(sql_content);
            prepStmt.setInt(1, inquiry.getInquiryID());
			ResultSet resultSet = prepStmt.executeQuery();
			while(resultSet.next()) {
				inquiry=new Inquiry();
				inquiry.setSubjectOfQuestion(resultSet.getString("subject"));
				inquiry.setDate(resultSet.getString("date"));
				inquiry.setStudent(resultSet.getString("studentid"));
				//inquiry.setInquiryID(resultSet.getInt("inquiryid"));
				inquiry.setQuestionContent(resultSet.getString("question"));

				list.add(inquiry);
			}
			resultSet.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
		
	}
	
	public void addQ(Inquiry inquiry){
		if(checkQ(inquiry)==false){
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			prepStmt_I = connection.prepareStatement(sql_send);
			prepStmt_I.setInt(1, inquiry.getInquiryID());
			prepStmt_I.setString(2, inquiry.getSubjectOfQuestion());
			prepStmt_I.setString(3,inquiry.getQuestionContent());
			prepStmt_I.setString(4, inquiry.getStudent());
			prepStmt_I.setString(5, inquiry.getDate());
			prepStmt_I.executeUpdate();
            prepStmt_I.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		}

		}
	public boolean checkQ(Inquiry inquiry){
		boolean result = false;
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			prepStmt_S = connection.prepareStatement(sql_checkID);
			prepStmt_S.setInt(1, inquiry.getInquiryID());
			ResultSet resultSet = prepStmt_S.executeQuery();
			if(resultSet.next()){

				result = true;
			}
			resultSet.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public void deleteID(Inquiry inquiry){
		if(checkQ(inquiry)==true){
		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			prepStmt_D = connection.prepareStatement(sql_deleteID_1);
			prepStmt_D.setInt(1, inquiry.getInquiryID());
			prepStmt_D.executeUpdate();
			prepStmt_D.close();
			PreparedStatement a = connection.prepareStatement(sql_deleteID_2);
			
			a.setInt(1, inquiry.getInquiryID());
			ResultSet resultSet = a.executeQuery();
			while(resultSet.next()){
				PreparedStatement s = connection.prepareStatement(sql_deleteID_3);
				s.setInt(1,resultSet.getInt("inquiryid")-1);
				s.setInt(2,resultSet.getInt("inquiryid"));
				s.executeUpdate();
			}
			resultSet.close();
			connection.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		}

	}




}

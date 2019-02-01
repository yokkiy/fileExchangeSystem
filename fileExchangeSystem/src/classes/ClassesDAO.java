package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClassesDAO {
	final private static String dbname = "student";   // データベース名
	final private static String user = "wspuser";      // studentにアクセスできるユーザ
	final private static String password = "hogehoge"; // userのパスワード
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;
	private Classes classes;

	public List<Classes> getList(){
		List<Classes>list=new ArrayList<>();
		Connection connection;
		String sql="select * from classes";
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet resultset=pstmt.executeQuery();
			while(resultset.next()){
				classes=new Classes();
				classes.setName(resultset.getString("classname"));
				classes.setId(resultset.getString("classid"));
				list.add(classes);
			}
			resultset.close();
			connection.close();

		}catch(Exception e){
			e.printStackTrace();
		}


		return list;
	}

	public boolean exisClass(String id){
		boolean result=false;
		Connection connection;
		String sql="select * from classes where classid=?";
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet resultset=pstmt.executeQuery();
			if(resultset.next()){
				result=true;
				classes=new Classes();
				classes.setId(resultset.getString("classid"));
				classes.setName(resultset.getString("classname"));
			}
			resultset.close();
			connection.close();

		}catch(Exception e){
			e.printStackTrace();
		}

		return result;
	}

	public boolean exisClassbyName(String name){
		boolean result=false;
		Connection connection;
		String sql="select * from classes where classname=?";
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet resultset=pstmt.executeQuery();
			if(resultset.next()){
				result=true;
				classes=new Classes();
				classes.setId(resultset.getString("classid"));
				classes.setName(resultset.getString("classname"));
			}
			resultset.close();
			connection.close();

		}catch(Exception e){
			e.printStackTrace();
		}

		return result;
	}

	public void addClass(Classes classes){
		Connection connection;
		String sql = "insert into classes values(?,?)";

		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, classes.getName());
			pstmt.setString(2, classes.getId());
			pstmt.executeUpdate();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public Classes getClass(String id){
		classes=new Classes();
		Connection connection;
		String sql = "select * from classes where classid=?";
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet resultset=pstmt.executeQuery();
			if(resultset.next()){
				classes.setId(id);
				classes.setName(resultset.getString("classname"));
			}
			resultset.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}

		return classes;

	}

	public Classes getClassByname(String name){
		classes=new Classes();
		Connection connection;
		String sql = "select * from classes where classname=?";
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet resultset=pstmt.executeQuery();
			if(resultset.next()){
				classes.setId(resultset.getString("classid"));
				classes.setName(name);
			}
			resultset.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}

		return classes;

	}

	public void deleteClass(String id){
		Connection connection;
		String sql="delete from classes where classid=?";
		String sql2="select * from classes where classid::integer>=?";
		String update="update classes set classid=? where classid=?";
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			//ファイル削除
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			PreparedStatement pstmt2 = connection.prepareStatement(sql2);
			pstmt2.setInt(1, Integer.parseInt(id));
			ResultSet resultSet=pstmt2.executeQuery();
			while(resultSet.next()){
				PreparedStatement upd = connection.prepareStatement(update);
				upd.setString(1, String.format("%03d", Integer.parseInt(resultSet.getString("classid"))-1));
				upd.setString(2, resultSet.getString("classid"));
				upd.executeUpdate();
			}
			resultSet.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}


}

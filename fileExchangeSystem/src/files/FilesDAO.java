package files;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilesDAO {
	final private static String dbname = "student";   // データベース名
	final private static String user = "wspuser";      // studentにアクセスできるユーザ
	final private static String password = "hogehoge"; // userのパスワード
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;
	private Files files;


	public void addFile(Files file)throws SQLException{
		this.files=file;

		Connection connection;
		String sql = "insert into files values(?,current_date,?,?,?)";
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, files.getId());
			pstmt.setString(2, files.getName());
			pstmt.setString(3, files.getStudentid());
			pstmt.setString(4, files.getClassid());
			pstmt.executeUpdate();

			connection.close();

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public List<Files> getList(){
		List<Files>lists=new ArrayList<>();
		Connection connection;
		String sql = "select * from files order by fileid asc";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				files=new Files();
				files.setId(resultSet.getString("fileid"));
				files.setDate(resultSet.getDate("uploaddate"));
				files.setName(resultSet.getString("filename"));
				files.setStudentid(resultSet.getString("studentid"));
				files.setClassid(resultSet.getString("classid"));
				lists.add(files);
			}

			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lists;

	}

	public List<Files> getListbyClass(String classid){
		List<Files>lists=new ArrayList<>();
		Connection connection;
		String sql = "select * from files where classid=? order by fileid asc";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, classid);
			ResultSet resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				files=new Files();
				files.setId(resultSet.getString("fileid"));
				files.setDate(resultSet.getDate("uploaddate"));
				files.setName(resultSet.getString("filename"));
				files.setStudentid(resultSet.getString("studentid"));
				files.setClassid(resultSet.getString("classid"));
				lists.add(files);
			}

			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lists;

	}


	public boolean existFiles(Files newfile){
		boolean result=false;
		Connection connection;
		String sql = "select * from files where filename=? and classid=?";
		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, newfile.getName());
			pstmt.setString(2, newfile.getClassid());
			ResultSet resultSet=pstmt.executeQuery();
			if(resultSet.next()) {
				result=true;
			}
			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Files getFiles(String id){
		Files files=new Files();
		Connection connection;
		String sql = "select * from files where fileid=?";
		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet resultSet=pstmt.executeQuery();
			if(resultSet.next()) {
				files.setId(resultSet.getString("fileid"));
				files.setDate(resultSet.getDate("uploaddate"));
				files.setName(resultSet.getString("filename"));
				files.setStudentid(resultSet.getString("studentid"));
				files.setClassid(resultSet.getString("classid"));
			}
			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return files;
	}


	public boolean deleteFiles(String id){
		boolean result=false;
		Connection connection;
		String sql = "delete from files where fileid=?";
		String sql2="select * from files where fileid::integer>=?";
		String updatef="update files set fileid=? where fileid=?";
		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			//ファイルのDB削除
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			PreparedStatement pstmt2 = connection.prepareStatement(sql2);
			pstmt2.setInt(1, Integer.parseInt(id));
			ResultSet resultSet=pstmt2.executeQuery();
			while(resultSet.next()){
				PreparedStatement upf = connection.prepareStatement(updatef);
				upf.setString(1, String.format("%04d",Integer.parseInt(resultSet.getString("fileid"))-1));
				upf.setString(2, resultSet.getString("fileid"));
				upf.executeUpdate();

			}
			resultSet.close();
			connection.close();
			result=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return result;

	}

}

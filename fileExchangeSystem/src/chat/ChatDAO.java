package chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatDAO {
	final private static String dbname = "student";   // データベース名
	final private static String user = "wspuser";      // studentにアクセスできるユーザ
	final private static String password = "hogehoge"; // userのパスワード
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;
	private Chat chat;

	public List<Chat>getList(String roomid) throws SQLException{
		List<Chat>list=new ArrayList<>();
		Connection connection;
		String sql = "select * from chat where roomid=? order by chatid asc";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, roomid);

			ResultSet resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				chat=new Chat();
				chat.setChatid(resultSet.getInt("chatid"));
				chat.setStudentid(resultSet.getString("studentid"));
				chat.setRoomid(resultSet.getString("roomid"));
				chat.setChatdate(resultSet.getDate("chatdate"));
				chat.setComment(resultSet.getString("comment"));
				chat.setCname(resultSet.getString("chattername"));
				list.add(chat);
			}

			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}



		return list;
	}



	public boolean addChat(Chat chat,int addid)throws SQLException{

		boolean result=false;
		Connection connection;
		String sql = "insert into chat values(?,?,?,current_date,?,?)";
		String select="select * from chat where chatid=? and roomid=?";
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1,addid);
			pstmt.setString(2, chat.getStudentid());
			pstmt.setString(3, chat.getRoomid());
			pstmt.setString(4, chat.getComment());
			pstmt.setString(5, chat.getCname());
			pstmt.executeUpdate();

			PreparedStatement pstmtS = connection.prepareStatement(select);
			pstmtS.setInt(1, addid);
			pstmtS.setString(2, chat.getRoomid());
			ResultSet resultSet = pstmtS.executeQuery();
			if(resultSet.next()){
				result=true;
			}
			resultSet.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public boolean delchat(int id,String rid){
		boolean result=false;
		Connection connection;
		String sql = "delete from chat where chatid=? and roomid=?";
		String sql2="select * from chat where roomid=? and chatid>=? order by chatid asc";
		String upsql="update chat set chatid=? where roomid=? and chatid=?";
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			//チャット削除
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, rid);
			pstmt.executeUpdate();
			result=true;
			//id番号以上のチャット取得
			PreparedStatement pstmt2=connection.prepareStatement(sql2);
			pstmt2.setString(1, rid);
			pstmt2.setInt(2, id);
			ResultSet resultset=pstmt2.executeQuery();
			int i=id;
			while(resultset.next()){
				//もし削除チャットid番目以降に存在するなら
				try{
				PreparedStatement pstmt3=connection.prepareStatement(upsql);
				pstmt3.setInt(1, i);
				pstmt3.setString(2, rid);
				pstmt3.setInt(3, i+1);
				pstmt3.executeQuery();

				}catch(Exception e){

				}
				i++;

			}
		}catch(Exception e){
			e.printStackTrace();
		}


		return result;
	}

	public boolean alldelchat(String rid){
		boolean result=false;
		Connection connection;
		String sql = "delete from chat where roomid=?";
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, rid);
			pstmt.executeUpdate();
			result=true;
		}catch(Exception e){
			e.printStackTrace();
		}

		return result;
	}


}

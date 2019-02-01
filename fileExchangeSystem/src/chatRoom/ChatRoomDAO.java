package chatRoom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatRoomDAO {
	final private static String dbname = "student";   // データベース名
	final private static String user = "wspuser";      // studentにアクセスできるユーザ
	final private static String password = "hogehoge"; // userのパスワード
	final private static String driverClassName = "org.postgresql.Driver";
	final private static String url = "jdbc:postgresql://localhost/" + dbname;
    public ChatRoom room;

    public ChatRoom getroom(String classid) throws SQLException{

    	Connection connection;
		String sql = "select * from chatroom where roomid=?";

    	try{
    		Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, classid);
			ResultSet resultSet = pstmt.executeQuery();
			if(resultSet.next()){
				room=new ChatRoom();
				room.setRoomId(resultSet.getString("roomid"));
				room.setSubject(resultSet.getString("classid"));
				room.setNumberOfchats(resultSet.getInt("chatnum"));
				room.setRoomName(resultSet.getString("roomname"));
			}
			resultSet.close();
			connection.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return room;
    }


	public List<ChatRoom> getList() throws SQLException {

		// chatroomをDBから取得
		List<ChatRoom>list=new ArrayList<>();
		Connection connection;
		String sql = "select * from chatroom";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				room=new ChatRoom();
				room.setRoomId(resultSet.getString("roomid"));
				room.setSubject(resultSet.getString("classid"));
				room.setNumberOfchats(resultSet.getInt("chatnum"));
				room.setRoomName(resultSet.getString("roomname"));
				list.add(room);
			}

			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<ChatRoom> getListbyclass(String classid) throws SQLException {

		// chatroomをDBから取得
		List<ChatRoom>list=new ArrayList<>();
		Connection connection;
		String sql = "select * from chatroom where classid=?";

		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, classid);
			ResultSet resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				room=new ChatRoom();
				room.setRoomId(resultSet.getString("roomid"));
				room.setSubject(resultSet.getString("classid"));
				room.setNumberOfchats(resultSet.getInt("chatnum"));
				room.setRoomName(resultSet.getString("roomname"));
				list.add(room);
			}

			resultSet.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void insertRoom(ChatRoom room){
		Connection connection;
		String sql="insert into chatroom values(?,?,0,?)";
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			room.setRoomId(String.format("%03d", getList().size()+1));
			pstmt.setString(1, room.getRoomId());
			pstmt.setString(2, room.getSubject());
			pstmt.setString(3, room.getRoomName());
			pstmt.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void deleteRoom(String roomid){
		Connection connection;
		String sql="delete from chatroom where roomid=?";
		String sql2="select * from chatroom where roomid::integer>=?";
		String update="update chatroom set roomid=? where roomid=?";
		try{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, user,password);
			//ファイル削除
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, roomid);
			pstmt.executeUpdate();
			PreparedStatement pstmt2 = connection.prepareStatement(sql2);
			pstmt2.setInt(1, Integer.parseInt(roomid));
			ResultSet resultSet=pstmt2.executeQuery();
			while(resultSet.next()){
				PreparedStatement upd = connection.prepareStatement(update);
				upd.setString(1, String.format("%03d", Integer.parseInt(resultSet.getString("roomid"))-1));
				upd.setString(2, resultSet.getString("roomid"));
				upd.executeUpdate();
			}
			resultSet.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}



}

package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Student.Student;
import chat.Chat;
import chat.ChatDAO;
import chatRoom.ChatRoom;
import chatRoom.ChatRoomDAO;

/**
 * Servlet implementation class ChatManager
 */
@WebServlet("/ChatManager")
public class ChatManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChatRoomDAO chatroomdao;
	private ChatDAO chatdao;


	public ChatManager() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#HttpServlet()
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		if(request.getParameter("check").equals("studentChat")){//学生チャット画面遷移
			String roomid = null;
			roomid=request.getParameter("allroom");
			if(roomid!=null){
			chatroomdao=new ChatRoomDAO();
			ChatRoom nowroom=new ChatRoom();
			try {
				nowroom=chatroomdao.getroom(roomid);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			chatdao=new ChatDAO();
			List<Chat>list=this.getList(chatdao, roomid);
			session.setAttribute("nowRoom", nowroom);
			session.setAttribute("chatList", list);
			getServletContext().getRequestDispatcher("/studentsChat.jsp").forward(request, response);
			}else{
				getServletContext().getRequestDispatcher("/studentsChatroom.jsp").forward(request, response);
			}



		} else if (request.getParameter("check").equals("studentchatpost")) {// チャット投稿
			// チャット投稿に必要な情報を取得
			String comments = request.getParameter("chatcomment");
			Student roginU = (Student) session.getAttribute("student");

			ChatRoom room = (ChatRoom) session.getAttribute("nowRoom");

			List<Chat> list = (List<Chat>) session.getAttribute("chatList");
			boolean suc = false;
			suc = this.makeChat(roginU.getStudentID(), room.getRoomId(), comments, roginU.getUserName(), list.size());
			list = this.getList(chatdao, room.getRoomId());

			if (suc) {
				session.setAttribute("chatList", list);
				// 返却(投稿)
				getServletContext().getRequestDispatcher("/studentsChat.jsp").forward(request, response);
			} else {
				session.setAttribute("addchatError", 1);
				getServletContext().getRequestDispatcher("/studentsChat.jsp").forward(request, response);
			}

		}else{

		}



	}




    public List<Chat>getList(ChatDAO dao,String roomid){
    	this.chatdao=dao;
    	List<Chat>list=new ArrayList<>();
    	try{
    		list=chatdao.getList(roomid);
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    	return list;
    }

    public boolean makeChat(String stid,String rid,String comm,String usern,int listSize){
    	boolean result=false;
    	Chat newChat=new Chat();
    	newChat.setStudentid(stid);
    	newChat.setRoomid(rid);
    	newChat.setComment(comm);
    	newChat.setCname(usern);
    	chatdao=new ChatDAO();
    	try{
    		result=chatdao.addChat(newChat, listSize+1);
    	}catch(SQLException e){
    		e.printStackTrace();
    	}

    	return result;

    }

    public boolean deleteChat(int chatid,String roomid){
    	boolean result=false;
    	chatdao=new ChatDAO();
    	result=chatdao.delchat(chatid, roomid);

    	return result;
    }

    public boolean alldeleteChat(String roomid){
    	boolean result=false;
    	chatdao=new ChatDAO();
    	result=chatdao.alldelchat(roomid);

    	return result;
    }



}

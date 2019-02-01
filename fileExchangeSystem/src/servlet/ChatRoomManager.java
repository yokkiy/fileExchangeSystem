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

import chatRoom.ChatRoom;
import chatRoom.ChatRoomDAO;
import classes.Classes;
import classes.ClassesDAO;

/**
 * Servlet implementation class ChatRoomManager
 */
@WebServlet("/ChatRoomManager")
public class ChatRoomManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  ChatRoom room;
	private ChatRoomDAO chatroomdao=new ChatRoomDAO();
	private ChatManager chatmanager;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatRoomManager() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	if(request.getParameter("check").equals("deletechatroom")){

    		String roomid=request.getParameter("allroom");
    		if(roomid!=null){
    			chatmanager=new ChatManager();
    			chatmanager.alldeleteChat(roomid);
    			this.deleteroom(chatroomdao, roomid);
    		}
    		List<ChatRoom>list=this.getList(chatroomdao);
    		session.setAttribute("roomList", list);
    		getServletContext().getRequestDispatcher("/chatManager.jsp").forward(request, response);

    	}else if(request.getParameter("check").equals("studentChatroom")){//学生チャットルーム選択画面遷移
			String selectClass=request.getParameter("classes");
			ClassesDAO classesdao=new ClassesDAO();
			Classes nowclass=classesdao.getClass(selectClass);
			session.setAttribute("nowclass", nowclass.getName());

			List<ChatRoom> list=new ArrayList<>();;
			try {
				list = chatroomdao.getListbyclass(selectClass);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			session.setAttribute("roomList", list);
			getServletContext().getRequestDispatcher("/studentsChatroom.jsp").forward(request, response);
    	}else{

    	}

    }
    public List<ChatRoom>getList(ChatRoomDAO dao){
    	this.chatroomdao=dao;
    	List<ChatRoom>lists=new ArrayList<>();
    	try{
    		lists=chatroomdao.getList();
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    	return lists;
    }

    public void addroom(ChatRoomDAO dao,String cid,String name){
    	this.chatroomdao=dao;
    	room=new ChatRoom();
    	room.setSubject(cid);
    	room.setRoomName(name);
    	room.setNumberOfchats(0);
    	chatroomdao.insertRoom(room);
    }

    public void deleteroom(ChatRoomDAO dao,String roomid){
    	this.chatroomdao=dao;
    	chatroomdao.deleteRoom(roomid);
    }



}

package servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Inquiry.Inquiry;
import Inquiry.InquiryDAO;
import Student.Student;
import Student.StudentDAO;
import chat.Chat;
import chat.ChatDAO;
import chatRoom.ChatRoom;
import chatRoom.ChatRoomDAO;
import classes.Classes;
import classes.ClassesDAO;
import files.Files;
import files.FilesDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
@MultipartConfig(location = "/tmp", maxFileSize = 1048576)
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentManager studentmanager;
	StudentDAO studentdao = new StudentDAO();
	Student student = new Student();
	private ChatRoomManager chatroommanager;
	ChatRoomDAO chatroomdao = new ChatRoomDAO();
	private ChatManager chatmanager;
	ChatDAO chatdao = new ChatDAO();
	private ClassesManager classmanager;
	ClassesDAO classesdao = new ClassesDAO();
	private Inquiry inquiry;
    private InquiryDAO inquirydao;
    private FilesDAO filedao=new FilesDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// パラメーターチェック
		if (request.getParameter("check").equals("login")) {// ログイン判別
			studentmanager = new StudentManager();
			boolean result = false;
			student.setMailAddress(request.getParameter("mail"));
			student.setPassword(request.getParameter("pass"));
			result = studentmanager.login(student, studentdao);

			HttpSession session = request.getSession();
			session.setAttribute("login", result);
			if (result) {
				// ログインに成功している場合はTop.jspへ
				student = studentdao.getStudent();
				session.setAttribute("student", student);
				if (student.getMasterflag() == 1) {
					getServletContext().getRequestDispatcher("/managerTop.jsp").forward(request, response);
				} else {
					getServletContext().getRequestDispatcher("/studentTop.jsp").forward(request, response);
				}

			} else {
				// ログインに失敗している場合はlogin.jspへ
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}


		} else if (request.getParameter("check").equals("bb")) {// ID追加処理(学生）
			getServletContext().getRequestDispatcher("/StudentManager").forward(request, response);

		} else if (request.getParameter("check").equals("t")) {// ID追加画面へ遷移
			getServletContext().getRequestDispatcher("/addID.jsp").forward(request, response);
		} else if (request.getParameter("check").equals("b")) {// ID追加処理
			getServletContext().getRequestDispatcher("/StudentManager").forward(request, response);
		} else if (request.getParameter("check").equals("s")) {// ID削除画面へ遷移
			getServletContext().getRequestDispatcher("/StudentManager").forward(request, response);
		} else if (request.getParameter("check").equals("deleteID")) {// ID削除処理
			if (request.getParameter("d") != null || request.getParameter("r") != null) {// 管理者or学生
				Student master = new Student();
				String masterList[] = request.getParameterValues("d");
				if (masterList != null) {
					for (int i = 0; i < masterList.length; i++) {
						master.setStudentID(masterList[i]);
						studentdao.deleteID(master);
					}
				}
				StudentDAO masterdao = new StudentDAO();
				List<Student> masterlist = new ArrayList<>();
				masterlist = masterdao.getMList();
				request.setAttribute("masterList", masterlist);

				Student student = new Student();
				String studentList[] = request.getParameterValues("r");
				if (studentList != null) {
					for (int i = 0; i < studentList.length; i++) {
						student.setStudentID(studentList[i]);
						studentdao.deleteID(student);
					}
				}
				StudentDAO studentdao = new StudentDAO();
				List<Student> studentlist = new ArrayList<>();
				studentlist = studentdao.getSList();
				request.setAttribute("studentList", studentlist);

				getServletContext().getRequestDispatcher("/deleteID.jsp").forward(request, response);

			} else {
				StudentDAO masterdao = new StudentDAO();
				List<Student> masterlist = new ArrayList<>();
				masterlist = masterdao.getMList();
				request.setAttribute("masterList", masterlist);

				StudentDAO studentdao = new StudentDAO();
				List<Student> studentlist = new ArrayList<>();
				studentlist = studentdao.getSList();
				request.setAttribute("studentList", studentlist);
				getServletContext().getRequestDispatcher("/deleteID.jsp").forward(request, response);
			}

		} else if (request.getParameter("check").equals("keyword")) {//IDキーワード検索
			studentmanager=new StudentManager();
			String keyword=request.getParameter("key");
			List<Student>masterlist=new ArrayList<>();
			masterlist=studentmanager.serchMID(keyword);
			request.setAttribute("masterList", masterlist);
			List<Student>studentlist=new ArrayList<>();
			studentlist=studentmanager.serchSID(keyword);
			request.setAttribute("studentList", studentlist);
			getServletContext().getRequestDispatcher("/deleteID.jsp").forward(request, response);


		} else if (request.getParameter("check").equals("roomList")) {// チャット管理画面へ遷移
			chatroommanager = new ChatRoomManager();
			List<ChatRoom> list = new ArrayList<>();
			list = chatroommanager.getList(chatroomdao);
			HttpSession session = request.getSession();
			session.setAttribute("roomList", list);
			getServletContext().getRequestDispatcher("/chatManager.jsp").forward(request, response);
		} else if (request.getParameter("check").equals("chat")) {// チャットログ削除画面へ遷移
			chatmanager = new ChatManager();
			List<Chat> list = new ArrayList<>();
			String roomid = null;
			roomid=request.getParameter("allroom");
			if(roomid!=null){
			list = chatmanager.getList(chatdao, roomid);
			HttpSession session = request.getSession();
			ChatRoom nroom=new ChatRoom();
			try {
				nroom=chatroomdao.getroom(roomid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			session.setAttribute("nowRoom", nroom.getRoomName());
			session.setAttribute("chatList", list);
			getServletContext().getRequestDispatcher("/deleteChat.jsp").forward(request, response);
			}else{
				getServletContext().getRequestDispatcher("/chatManager.jsp").forward(request, response);
			}
		} else if (request.getParameter("check").equals("chatpost")) {// チャット投稿
			// チャット投稿に必要な情報を取得
			HttpSession session = request.getSession();
			String comments = request.getParameter("chatcomment");
			Student roginU = (Student) session.getAttribute("student");

			String roomid = (String) session.getAttribute("nowRoom");

			List<Chat> list = (List<Chat>) session.getAttribute("chatList");
			chatmanager = new ChatManager();
			boolean suc = false;
			suc = chatmanager.makeChat(roginU.getStudentID(), roomid, comments, roginU.getUserName(), list.size());
			list = chatmanager.getList(chatdao, roomid);

			if (suc) {
				session.setAttribute("chatList", list);
				// 返却(投稿)
				getServletContext().getRequestDispatcher("/deleteChat.jsp").forward(request, response);
			} else {
				session.setAttribute("addchatError", 1);
				getServletContext().getRequestDispatcher("/deleteChat.jsp").forward(request, response);
			}
		} else if (request.getParameter("check").equals("alldelete")) {// チャット全削除
			HttpSession session = request.getSession();
			String roomid = (String) session.getAttribute("nowRoom");
			chatmanager = new ChatManager();
			boolean alldel = false;
			alldel = chatmanager.alldeleteChat(roomid);
			if (alldel) {
				List<Chat> list = chatmanager.getList(chatdao, roomid);
				session.setAttribute("chatList", list);
				getServletContext().getRequestDispatcher("/deleteChat.jsp").forward(request, response);
			}

		} else if (request.getParameter("check").equals("delete")) {// チャット削除
			HttpSession session = request.getSession();
			if (request.getParameter("chats") != null) {
				// int chatid=Integer.parseInt(request.getParameter("chats"));
				String chatid2[] = request.getParameterValues("chats");
				String roomid = (String) session.getAttribute("nowRoom");
				// chatmanager=new ChatManager();
				boolean del = false;
				for (int i = 0; i < chatid2.length; i++) {
					chatmanager = new ChatManager();
					del = chatmanager.deleteChat(Integer.parseInt(chatid2[i]) - i, roomid);

				}

				if (del) {
					List<Chat> list = chatmanager.getList(chatdao, roomid);
					session.setAttribute("chatList", list);
					getServletContext().getRequestDispatcher("/deleteChat.jsp").forward(request, response);
				} else {
					getServletContext().getRequestDispatcher("/deleteChat.jsp").forward(request, response);
				}
			} else {
				getServletContext().getRequestDispatcher("/deleteChat.jsp").forward(request, response);
			}

		} else if (request.getParameter("check").equals("chatroomcreator")) {// チャットルーム作成画面へ遷移
			getServletContext().getRequestDispatcher("/ClassesManager").forward(request, response);

		} else if (request.getParameter("check").equals("roomcreate")) {// チャットルーム作成処理
			HttpSession session = request.getSession();
			String roomcreate = request.getParameter("classname");
			String roomname = request.getParameter("chatroomname");
			if (!roomcreate.equals("")) {
				boolean result = false;
				classmanager = new ClassesManager();
				result = classmanager.existantByname(classesdao,roomcreate);
				if (result) {
					// 対応する授業に追加
				    Classes nowclass=classesdao.getClassByname(roomcreate);
					chatroommanager = new ChatRoomManager();
					chatroommanager.addroom(chatroomdao, nowclass.getId(), roomname);
					getServletContext().getRequestDispatcher("/addChatRoom.jsp").forward(request, response);
				} else {
					// 授業作成後、追加
					classmanager = new ClassesManager();
					Classes classes = classmanager.addclass(classesdao, roomcreate);
					chatroommanager = new ChatRoomManager();
					chatroommanager.addroom(chatroomdao, classes.getId(), roomname);

					// ファイル保存フォルダ作成
					File file = new File(getServletContext().getRealPath("/uploadFiles") + "/" + roomcreate);
					file.mkdir();
					getServletContext().getRequestDispatcher("/ClassesManager").forward(request, response);
				}
			} else {
				session.setAttribute("chatroomName", roomname);
				getServletContext().getRequestDispatcher("/addChatRoom.jsp").forward(request, response);
			}
		} else if (request.getParameter("check").equals("deletechatroom")) {// チャットルーム削除
			getServletContext().getRequestDispatcher("/ChatRoomManager").forward(request, response);
		} else if (request.getParameter("check").equals("addfile")) {// ファイル追加
			getServletContext().getRequestDispatcher("/FileManager").forward(request, response);
		} else if (request.getParameter("check").equals("addFiles")) {// ファイル追加画面へ遷移
			getServletContext().getRequestDispatcher("/ClassesManager").forward(request, response);

		} else if (request.getParameter("check").equals("deleteFiles")) {// ファイル削除画面に遷移
			getServletContext().getRequestDispatcher("/FileManager").forward(request, response);

		} else if (request.getParameter("check").equals("deletefile")) {// ファイル削除処理
			getServletContext().getRequestDispatcher("/FileManager").forward(request, response);

		} else if (request.getParameter("check").equals("classManager")) {// 授業管理画面に遷移
			getServletContext().getRequestDispatcher("/ClassesManager").forward(request, response);

		} else if (request.getParameter("check").equals("addClass")) {// 授業追加処理
			classmanager = new ClassesManager();
			String classname = request.getParameter("newclassname");
			if(!classname.equals("")){
			classmanager.addclass(classesdao, classname);
			File file = new File(getServletContext().getRealPath("/uploadFiles") + "/" + classname);
			file.mkdir();
			}
			getServletContext().getRequestDispatcher("/ClassesManager").forward(request, response);


		} else if (request.getParameter("check").equals("deleteClass")) {// 授業削除処理

			String classes[] = null;
			classes=request.getParameterValues("classlist");
			if(classes!=null){
			int clnum=0;
			for(int i=0;i<classes.length;i++){
				//ファイルDB削除
				List<Files>filelist=filedao.getListbyClass(classes[i]);
				for(Files files:filelist){
					filedao.deleteFiles(files.getId());
				}
				//フォルダ削除
				Classes delclass=classesdao.getClass(classes[i]);
				File file = new File(getServletContext().getRealPath("/uploadFiles") + "/" + delclass.getName());
				file.delete();
				//ルーム取得
				try {
					List<ChatRoom>roomlist=chatroomdao.getListbyclass(classes[i]);
					int j=0;
					for(ChatRoom room:roomlist){
						List<Chat>chatlist=chatdao.getList(room.getRoomId());
						int k=0;
						for(Chat chat:chatlist){
							//チャット削除
							chatmanager.deleteChat(chat.getChatid() - k, room.getRoomId());
							k++;
						}
						//ルーム削除
						chatroomdao.deleteRoom(String.format("%03d",Integer.parseInt(room.getRoomId())-j));
						j++;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				classesdao.deleteClass(String.format("%03d",Integer.parseInt(classes[i])-clnum));
				clnum++;
			}
			}
			getServletContext().getRequestDispatcher("/ClassesManager").forward(request, response);





		}else if(request.getParameter("check").equals("chooseclass")){//学生授業選択画面
			getServletContext().getRequestDispatcher("/ClassesManager").forward(request, response);

		}else if(request.getParameter("check").equals("uploadFiles")){//学生アップロード画面

			getServletContext().getRequestDispatcher("/FileManager").forward(request, response);


		}else if(request.getParameter("check").equals("downloadFiles")){//学生ダウンロード画面
			getServletContext().getRequestDispatcher("/FileManager").forward(request, response);

		}else if(request.getParameter("check").equals("studentinfo")){//学生情報画面
			HttpSession session = request.getSession();
			session.getAttribute("student");
			getServletContext().getRequestDispatcher("/studentsInfo.jsp").forward(request, response);

		}else if(request.getParameter("check").equals("send")){
			HttpSession session = request.getSession();
			session.getAttribute("student");

			getServletContext().getRequestDispatcher("/studentsInfoChange.jsp").forward(request, response);
		}else if(request.getParameter("check").equals("send2")){//学生情報変更

		 if(!request.getParameter("user").equals("")){//ユーザ名変更
		 student.setUserName(request.getParameter("user"));
		 student.setMailAddress(student.getMailAddress());
		studentdao.updateUserName(student);
		 }else{
			 request.setAttribute("message1", "※未入力です※");
		 }

		if(!request.getParameter("credit").equals("")){//クレジットカード情報変更
			 student.setCreditCard(request.getParameter("credit"));
			 student.setMailAddress(student.getMailAddress());
			 studentdao.updateCC(student);
		 }else{
			 request.setAttribute("message2", "※未入力です※");
		 }

		 if(!request.getParameter("pass").equals("") &&
				 !request.getParameter("newpass").equals("")){//パスワード変更
			 if(request.getParameter("pass").equals(request.getParameter("newpass"))){
				 student.setPassword(request.getParameter("newpass"));
				 student.setMailAddress(student.getMailAddress());
				 studentdao.updatePass(student);
			 }else{
				 request.setAttribute("message3", "※パスワードが一致していません※");
			 }
		 }else{
			 request.setAttribute("message4", "※未入力です※");
		 }

			HttpSession session = request.getSession();
			session.getAttribute("student");
			getServletContext().getRequestDispatcher("/studentsInfoChange.jsp").forward(request, response);



		}else if(request.getParameter("check").equals("q")){//問い合わせ画面遷移
			HttpSession session = request.getSession();
			session.getAttribute("student");
			getServletContext().getRequestDispatcher("/InquiryManager").forward(request, response);
		}else if(request.getParameter("check").equals("send1")){
		Date date = new Date();
		if(!request.getParameter("text").equals("")
				&& !request.getParameter("question").equals("")){
	    String str = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
	    inquiry = new Inquiry();
		inquirydao = new InquiryDAO();
		List<Inquiry> list = new ArrayList<>();
		list = inquirydao.getList();
		inquiry.setSubjectOfQuestion(request.getParameter("text"));
		inquiry.setQuestionContent(request.getParameter("question"));
		inquiry.setInquiryID(list.size()+1);
		inquiry.setStudent(student.getStudentID());
		inquiry.setDate(str);
		inquirydao.addQ(inquiry);
		request.setAttribute("m", "送信できました");
		HttpSession session = request.getSession();
		session.getAttribute("student");
		getServletContext().getRequestDispatcher("/InquiryManager").forward(request, response);
		}else if(request.getParameter("text").equals("") || request.getParameter("question").equals("")){
			request.setAttribute("m", "入力されていません");
			HttpSession session = request.getSession();
			session.getAttribute("student");
			getServletContext().getRequestDispatcher("/InquiryManager").forward(request, response);
		}

	}else if(request.getParameter("check").equals("inquiry")){//問い合わせ画面遷移
		getServletContext().getRequestDispatcher("/InquiryManager").forward(request, response);
	}else if(request.getParameter("check").equals("sss")){
		getServletContext().getRequestDispatcher("/InquiryManager").forward(request, response);
	}else if(request.getParameter("check").equals("ddd")){
		getServletContext().getRequestDispatcher("/InquiryManager").forward(request, response);



		}else if(request.getParameter("check").equals("studentChatroom")){//学生チャットルーム選択画面遷移
			getServletContext().getRequestDispatcher("/ChatRoomManager").forward(request, response);


		}else if(request.getParameter("check").equals("studentChat")){//学生チャット画面遷移
			getServletContext().getRequestDispatcher("/ChatManager").forward(request, response);
		} else {// 例外
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);

		}
	}

}

package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.Classes;
import classes.ClassesDAO;

/**
 * Servlet implementation class ClassesManager
 */
@WebServlet("/ClassesManager")
@MultipartConfig(location="/tmp", maxFileSize=1048576)
public class ClassesManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClassesDAO classdao;
	private Classes classes;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassesManager() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//授業一覧取得post
    		request.setCharacterEncoding("UTF-8");
    		HttpSession session = request.getSession();
    		classdao=new ClassesDAO();
    		session.setAttribute("classList", getList(classdao));
    		if(request.getParameter("check").equals("chatroomcreator")){
    		getServletContext().getRequestDispatcher("/addChatRoom.jsp").forward(request, response);
    		}else
    		if(request.getParameter("check").equals("addFiles")){
    			getServletContext().getRequestDispatcher("/addFiles.jsp").forward(request, response);
    		}else
    		if(request.getParameter("check").equals("classManager")){
    			getServletContext().getRequestDispatcher("/ClassManager.jsp").forward(request, response);
    		}else
    		if(request.getParameter("check").equals("roomcreate")){
    			getServletContext().getRequestDispatcher("/addChatRoom.jsp").forward(request, response);
    		}else
    		if(request.getParameter("check").equals("addClass")){
    			getServletContext().getRequestDispatcher("/ClassManager.jsp").forward(request, response);

    		}else
        		if(request.getParameter("check").equals("deleteClass")){
        			getServletContext().getRequestDispatcher("/ClassManager.jsp").forward(request, response);
    		}else
    		if(request.getParameter("check").equals("chooseclass")){//学生授業選択画面
    			getServletContext().getRequestDispatcher("/chooseClass.jsp").forward(request, response);
    		}else{
    		}

    }


    public List<Classes>getList(ClassesDAO dao){
    	List<Classes>list=new ArrayList<>();
    	this.classdao=dao;
    	list=classdao.getList();

    	return list;
    }

    public boolean existant(ClassesDAO dao,String id){
    	boolean result=false;
    	this.classdao=dao;
    	result=classdao.exisClass(id);

    	return result;
    }

    public boolean existantByname(ClassesDAO dao,String name){
    	boolean result=false;
    	this.classdao=dao;
    	result=classdao.exisClassbyName(name);

    	return result;
    }

    public Classes addclass(ClassesDAO dao,String name){
    	this.classdao=dao;
    	classes=new Classes();
    	classes.setName(name);
    	classes.setId(String.format("%03d", getList(classdao).size()+1));
    	classdao.addClass(classes);

    	return classes;
    }

}

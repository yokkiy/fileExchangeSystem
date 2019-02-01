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

import Student.Student;
import Student.StudentDAO;

/**
 * Servlet implementation class studentManager
 */
@WebServlet("/StudentManager")
public class StudentManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Student student;
	private StudentDAO studentdao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		if (request.getParameter("check").equals("b")) {
			boolean flag[] = {false,false,false,false,false,false};
			Student regist = new Student();
			if(!request.getParameter("name").equals("")){
			regist.setName(request.getParameter("name"));

			}else{
				flag[0] = true;
			}
			if(!request.getParameter("grade").equals("")){
			regist.setGarade(Integer.parseInt(request.getParameter("grade")));

			}else{
				flag[1] = true;
			}
			if(!request.getParameter("subject").equals("")){
			regist.setSubject(request.getParameter("subject"));

			}else{
				flag[2] = true;
			}
			if(!request.getParameter("mailaddress").equals("")){
			regist.setMailAddress(request.getParameter("mailaddress"));

			}else{
				flag[3] = true;
			}
			regist.setCreditCard(null);

			if(!request.getParameter("password").equals("")){
			regist.setPassword(request.getParameter("password"));

			}else{
				flag[4] = true;
			}
			if(!request.getParameter("id").equals("")){
			regist.setStudentID(request.getParameter("id"));

			}else{
				flag[5] = true;
			}

			if (request.getParameter("a").equals("Manager")) {// 管理人登録
				regist.setMasterflag(1);

			} else if (request.getParameter("a").equals("Student")) {// 学生登録
				regist.setMasterflag(0);
			}
			studentdao = new StudentDAO();
			boolean addresult = false;

			if(flag[0] == false && flag[1] == false && flag[2] == false && flag[3] == false
					&& flag[4] == false && flag[5] == false ){
				request.setAttribute("m", "登録が完了しました");
				addresult = studentdao.addID(regist);
			}

			if (addresult) {
				getServletContext().getRequestDispatcher("/addID.jsp").forward(request, response);
			} else {
				for(int i =0;i<flag.length;i++){
					if(flag[i] == true){
						request.setAttribute("m", "未入力の項目があります");
					}else{
						request.setAttribute("m", "IDまたはメールアドレスがすでに登録されています");
					}
				}

				getServletContext().getRequestDispatcher("/addID.jsp").forward(request, response);
			}
		} else
		if (request.getParameter("check").equals("s")) {
			studentdao = new StudentDAO();
			List<Student> masterList = new ArrayList<>();
			masterList = studentdao.getMList();
			List<Student> studentList = new ArrayList<>();
			studentList = studentdao.getSList();
			request.setAttribute("masterList", masterList);
            request.setAttribute("studentList",studentList);
			getServletContext().getRequestDispatcher("/deleteID.jsp").forward(request, response);

		}else if (request.getParameter("check").equals("bb")) {
				boolean flag[] = {false,false,false,false,false,false};
				Student regist = new Student();
				if(!request.getParameter("name").equals("")){
				regist.setName(request.getParameter("name"));

				}else{
					flag[0] = true;
				}
				if(!request.getParameter("grade").equals("")){
				regist.setGarade(Integer.parseInt(request.getParameter("grade")));

				}else{
					flag[1] = true;
				}
				if(!request.getParameter("subject").equals("")){
				regist.setSubject(request.getParameter("subject"));

				}else{
					flag[2] = true;
				}
				if(!request.getParameter("mailaddress").equals("")){
				regist.setMailAddress(request.getParameter("mailaddress"));

				}else{
					flag[3] = true;
				}
				regist.setCreditCard(null);

				if(!request.getParameter("password").equals("")){
				regist.setPassword(request.getParameter("password"));

				}else{
					flag[4] = true;
				}
				if(!request.getParameter("id").equals("")){
				regist.setStudentID(request.getParameter("id"));

				}else{
					flag[5] = true;
				}


				studentdao = new StudentDAO();
				boolean addresult = false;

				if(flag[0] == false && flag[1] == false && flag[2] == false && flag[3] == false
						&& flag[4] == false && flag[5] == false ){
					request.setAttribute("m", "登録が完了しました");
					addresult = studentdao.addID(regist);
				}

				if (addresult) {
					getServletContext().getRequestDispatcher("/mailChecker.jsp").forward(request, response);
				} else {
					for(int i =0;i<flag.length;i++){
						if(flag[i] == true){
							request.setAttribute("m", "未入力の項目があります");
						}else{
							request.setAttribute("m", "IDまたはメールアドレスがすでに登録されています");
						}
					}

					getServletContext().getRequestDispatcher("/mailChecker.jsp").forward(request, response);
				}
		} else

		{
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	public boolean login(Student stu, StudentDAO stDAO) {

		this.student = stu;
		this.studentdao = stDAO;

		boolean result = false;
		try {
			result = studentdao.check(student);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;

	}

	public List<Student>serchMID(String key){
		List<Student>keylist=new ArrayList<>();
		List<Student>list=new ArrayList<>();
		studentdao=new StudentDAO();
		list=studentdao.getMList();
		for(Student ms:list){
			if(ms.getStudentID().contains(key)){
				keylist.add(ms);
			}
		}
		return keylist;
	}

	public List<Student>serchSID(String key){
		List<Student>keylist=new ArrayList<>();
		List<Student>list=new ArrayList<>();
		studentdao=new StudentDAO();
		list=studentdao.getSList();
		for(Student st:list){
			if(st.getStudentID().contains(key)){
				keylist.add(st);
			}
		}
		return keylist;
	}

}

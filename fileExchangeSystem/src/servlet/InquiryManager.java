package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Inquiry.Inquiry;
import Inquiry.InquiryDAO;
import Student.Student;
import Student.StudentDAO;


/**
 * Servlet implementation class InquiryManager
 */
@WebServlet("/InquiryManager")
public class InquiryManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private Inquiry inquiry;
       private InquiryDAO inquirydao;
       private Student student = new Student();
       private StudentDAO studentdao = new StudentDAO();
       static public final String DATE_PATTERN ="yyyy/MM/dd HH:mm:ss";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryManager() {
        super();
        // TODO Auto-generated constructor stub
    }

/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		if(request.getParameter("check").equals("q")){

			getServletContext().getRequestDispatcher("/inquiry.jsp").forward(request, response);

		}else if(request.getParameter("check").equals("send1")){
			getServletContext().getRequestDispatcher("/inquiry.jsp").forward(request, response);
			}else if(request.getParameter("check").equals("inquiry")){
				inquirydao = new InquiryDAO();
				List<Inquiry> inquiryList = new ArrayList<>();
				inquiryList = inquirydao.getList();
				request.setAttribute("inquiryList", inquiryList);
				getServletContext().getRequestDispatcher("/masterInquiry.jsp").forward(request, response);

			}else if(request.getParameter("check").equals("sss")){
				if(request.getParameter("iii")!=null){
				inquirydao = new InquiryDAO();
				inquiry = new Inquiry();
				String req = request.getParameter("iii");
				
				inquiry.setInquiryID(Integer.valueOf(req));
			     List<Inquiry> contentsList = new ArrayList<>();
				contentsList = inquirydao.getQList(inquiry);
				
				request.setAttribute("contentsList", contentsList);
				getServletContext().getRequestDispatcher("/question.jsp").forward(request, response);
				}else{
					inquirydao = new InquiryDAO();
					List<Inquiry> inquiryList = new ArrayList<>();
					inquiryList = inquirydao.getList();
					request.setAttribute("inquiryList", inquiryList);
					getServletContext().getRequestDispatcher("/masterInquiry.jsp").forward(request, response);
				}
				
			}else if(request.getParameter("check").equals("ddd")){
				if(request.getParameter("iii")!=null){
					inquirydao = new InquiryDAO();
					inquiry = new Inquiry();
					String req = request.getParameter("iii");
					
					inquiry.setInquiryID(Integer.valueOf(req));
					inquirydao.deleteID(inquiry);
					List<Inquiry> inquiryList = new ArrayList<>();
					inquiryList = inquirydao.getList();
					request.setAttribute("inquiryList", inquiryList);
					getServletContext().getRequestDispatcher("/masterInquiry.jsp").forward(request, response);
				}else{
					inquirydao = new InquiryDAO();
					List<Inquiry> inquiryList = new ArrayList<>();
					inquiryList = inquirydao.getList();
					request.setAttribute("inquiryList", inquiryList);
					getServletContext().getRequestDispatcher("/masterInquiry.jsp").forward(request, response);
				}
				
			}
			else{

			}

		}
	}



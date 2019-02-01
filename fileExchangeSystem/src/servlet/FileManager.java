package servlet;

import java.io.File;
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
import javax.servlet.http.Part;

import Student.Student;
import classes.Classes;
import classes.ClassesDAO;
import files.Files;
import files.FilesDAO;

/**
 * Servlet implementation class FileManager
 */
@WebServlet("/FileManager")
@MultipartConfig( maxFileSize = 1048576)
public class FileManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FilesDAO filesdao;
	private ClassesDAO classdao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("check").equals("addfile")) {// ファイルの追加
			HttpSession session = request.getSession();
			filesdao = new FilesDAO();
			// if (!request.getPart("file").equals(null)) {
			Part part = request.getPart("file");
			if (!this.getFileName(part).equals("")) {
				String name = this.getFileName(part);

				String classesid = request.getParameter("classselect");
				session.setAttribute("clselect", classesid);
				List<Files> fileList = new ArrayList<>();
				fileList = filesdao.getList();

				Files files = new Files();
				files.setId(String.format("%04d", fileList.size() + 1));
				files.setName(name);
				Student user = (Student) session.getAttribute("student");
				files.setStudentid(user.getStudentID());
				files.setClassid(classesid);

				boolean filexist = false;
				filexist = filesdao.existFiles(files);
				if (!filexist) {
					// 同名ファイルが存在するか
					try {
						filesdao.addFile(files);
					} catch (Exception e) {
						e.printStackTrace();
					}
					classdao = new ClassesDAO();
					Classes classname = (Classes) classdao.getClass(classesid);

					part.write(
							getServletContext().getRealPath("/uploadFiles") + "/" + classname.getName() + "/" + name);
					session.setAttribute("finame", name);
					request.setAttribute("e", null);
					getServletContext().getRequestDispatcher("/addFiles.jsp").forward(request, response);
				} else {
					// 存在する場合エラー返却
					session.setAttribute("fileAddError", filexist);
					getServletContext().getRequestDispatcher("/addFiles.jsp").forward(request, response);
				}
			} else {
				// 選択されていない場合
				request.setAttribute("e", "!ファイルを選択してください!");
				getServletContext().getRequestDispatcher("/addFiles.jsp").forward(request, response);
			}
		} else if (request.getParameter("check").equals("deleteFiles")) {// ファイル削除画面への遷移処理
			HttpSession session = request.getSession();
			filesdao = new FilesDAO();
			List<Files> filelist = new ArrayList<>();
			filelist = filesdao.getList();
			session.setAttribute("fileList", filelist);

			classdao = new ClassesDAO();
			List<Classes> classlist = new ArrayList<>();
			classlist = classdao.getList();
			session.setAttribute("classList", classlist);

			getServletContext().getRequestDispatcher("/deleteFiles.jsp").forward(request, response);

		} else if (request.getParameter("check").equals("fileserch")) {// ファイル削除画面での検索
			HttpSession session = request.getSession();
			String classid = request.getParameter("classselect");
			String keyname = request.getParameter("keyword");
			filesdao = new FilesDAO();
			List<Files> filelist = filesdao.getList();
			List<Files> serchl = new ArrayList<>();
			List<Files> serchn = new ArrayList<>();
			if (!classid.equals("")) {
				for (Files file : filelist) {
					if (file.getClassid().equals(classid)) {
						serchl.add(file);
					}
				}
			} else {
				serchl = filelist;
			}
			if (!keyname.equals("")) {
				for (Files file : serchl) {
					if (file.getName().contains(keyname)) {
						serchn.add(file);
					}
				}

			} else {
				serchn = serchl;
			}
			session.setAttribute("fileList", serchn);

			getServletContext().getRequestDispatcher("/deleteFiles.jsp").forward(request, response);

		} else if (request.getParameter("check").equals("uploadFiles")) {// 学生アップロード画面
			HttpSession session = request.getSession();
			String classid = request.getParameter("classes");
			classdao = new ClassesDAO();
			Classes nowclass = classdao.getClass(classid);
			session.setAttribute("nclasses", nowclass);
			session.setAttribute("classes", nowclass.getId());

			getServletContext().getRequestDispatcher("/uploadFiles.jsp").forward(request, response);

		} else if (request.getParameter("check").equals("uploadbyst")) {// 学生でのファイルの追加
			HttpSession session = request.getSession();
			filesdao = new FilesDAO();
			Part part = request.getPart("filename");
			if (!this.getFileName(part).equals("")) {
			String name = this.getFileName(part);

			String classid = (String) session.getAttribute("classes");
			classdao = new ClassesDAO();
			Classes nowclass = classdao.getClass(classid);

			List<Files> fileList = new ArrayList<>();
			fileList = filesdao.getList();

			Files files = new Files();
			files.setId(String.format("%04d", fileList.size() + 1));
			files.setName(name);
			Student user = (Student) session.getAttribute("student");
			files.setStudentid(user.getStudentID());
			files.setClassid(nowclass.getId());

			boolean filexist = false;
			filexist = filesdao.existFiles(files);
			if (!filexist) {
				// 同名ファイルが存在するか
				try {
					filesdao.addFile(files);
				} catch (Exception e) {
					e.printStackTrace();
				}

				part.write(getServletContext().getRealPath("/uploadFiles") + "/" + nowclass.getName() + "/" + name);
				session.setAttribute("finame", name);
				getServletContext().getRequestDispatcher("/uploadFiles.jsp").forward(request, response);
			} else {
				// 存在する場合エラー返却
				request.setAttribute("fileAddError", "！同名ファイルが存在しています！");
				getServletContext().getRequestDispatcher("/uploadFiles.jsp").forward(request, response);
			}
			}else{
				request.setAttribute("fileAddError", "!ファイルを選択してください!");
				getServletContext().getRequestDispatcher("/uploadFiles.jsp").forward(request, response);
			}

		} else if (request.getParameter("check").equals("downloadFiles")) {// 学生ダウンロード画面
			HttpSession session = request.getSession();
			String classid = request.getParameter("classes");
			classdao = new ClassesDAO();
			Classes nowclass = classdao.getClass(classid);
			session.setAttribute("classes", nowclass);
			filesdao = new FilesDAO();
			List<Files> filelist = new ArrayList<>();
			filelist = filesdao.getListbyClass(classid);
			session.setAttribute("fileList", filelist);
			getServletContext().getRequestDispatcher("/downloadFiles.jsp").forward(request, response);

		} else if (request.getParameter("check").equals("deletefile")) {// ファイル削除
			HttpSession session = request.getSession();
			String delfiles[] = null;
			delfiles=request.getParameterValues("alfiles");
			//if(delfiles.length>0){sizeok=true;}
				filesdao = new FilesDAO();
				classdao = new ClassesDAO();
				// delete:boolean
				boolean delok = false;
				if(delfiles!=null){
				for (int i = 0; i < delfiles.length; i++) {
					Files delfile = filesdao.getFiles(delfiles[i]);

					Classes delfilesclass = classdao.getClass(delfile.getClassid());
					// ファイル削除
					String filesPath = getServletContext().getRealPath("/uploadFiles") + "/";
					filesPath += delfilesclass.getName() + "/";
					filesPath += delfile.getName();
					session.setAttribute("path", filesPath);
					File del = new File(filesPath);
					del.delete();

					// DB削除
					delok = filesdao.deleteFiles(delfile.getId());

				}
				}
				if (delok) {
					List<Files> filelist = new ArrayList<>();
					filelist = filesdao.getList();
					session.setAttribute("fileList", filelist);
					session.setAttribute("error", delfiles.length);
					getServletContext().getRequestDispatcher("/deleteFiles.jsp").forward(request, response);
				} else {
					getServletContext().getRequestDispatcher("/deleteFiles.jsp").forward(request, response);
				}



		} else {//例外処理

		}
	}

	private String getFileName(Part part) {
		String name = null;
		for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
			if (dispotion.trim().startsWith("filename")) {
				name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
				name = name.substring(name.lastIndexOf("\\") + 1);
				break;
			}
		}
		return name;
	}

}

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.RiskAgendaListBean;
import factory.DaoFactory;
import model.Project;
import model.User;

/**
 * Servlet implementation class AddProjectServlet
 */
@WebServlet("/AddProjectServlet")
public class AddProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		ServletContext context = getServletContext();
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter pw=response.getWriter();
		
		int userId=(Integer)session.getAttribute("LoginId");
		String projectName=request.getParameter("projectName");
		String projectContent=request.getParameter("projectContent");
		String[] trackersId=request.getParameterValues("usersOfProject");
		
		ArrayList<User> usersOfProject=new ArrayList<User>();
		
		if(trackersId.length>0){
			for(int i=0;i<trackersId.length;i++){
				int trackerId=Integer.parseInt(trackersId[i]);
				User u=new User();
				u.setUserId(trackerId);
				usersOfProject.add(u);
			}			
		}
		Project project=new Project();
		project.setProjectName(projectName);
		project.setProjectContent(projectContent);
		project.setUsers(usersOfProject);
		project.setManagerId(userId);
		boolean isSuccess=DaoFactory.getProjectDao().addProject(project);
		
		if(!isSuccess){
			pw.print("<script>location.href='./checkRisk/projectList.jsp'</script>"); 
		}else{
			
			
			session.setAttribute("projectsOfManager",DaoFactory.getProjectDao().getProjectByManager(userId));
			
			
			pw.print("<script>location.href='./checkRisk/projectList.jsp'</script>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

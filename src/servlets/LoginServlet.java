package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import factory.DaoFactory;
import model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		PrintWriter pw=response.getWriter();
		//HttpSession session = request.getSession(true);
		
		int loginId=Integer.parseInt(request.getParameter("loginid").trim());
		String loginPassword=request.getParameter("loginpassword").trim();
		
		User user=DaoFactory.getUserDao().findUser(loginId, loginPassword);
		
		//µÇÂ¼Ê§°Ü
		if(user==null){
			pw.print("<script>alert('ÓÃ»§Ãû»òÃÜÂë´íÎó£¡');location.href='./login/login.jsp'</script>");
		}else{
			System.out.println("µÇÂ¼");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

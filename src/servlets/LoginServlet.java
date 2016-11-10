package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
		HttpSession session = request.getSession(false);
		ServletContext context = getServletContext();
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter pw=response.getWriter();

		//查找cookie是否存在
		boolean cookieFound = false;
		Cookie cookie = null;
		Cookie[] cookies = request.getCookies();
		
		if(cookies!=null){
			for(int i=0;i<cookies.length;i++){
				cookie=cookies[i];
				if(cookie.getName().trim().equals("LoginCookie")){
					cookieFound=true;
					break;
				}
			}
		}
		
		String userId=request.getParameter("inputEmail").trim();
		String password = request.getParameter("inputPassword").trim();
			
		User user=DaoFactory.getUserDao().findUser(userId,password);
			
		if(user==null){
			pw.print("<script>alert('用户名或密码错误，请重新登录！');location.href='./login/login.jsp'</script>");
		}else{
			context.getRequestDispatcher("/register/register.jsp").forward(request, response);
			
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

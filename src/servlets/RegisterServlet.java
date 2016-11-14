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
import model.UserType;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(false);
		ServletContext context = getServletContext();
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter pw=response.getWriter();
		
		String nickname=request.getParameter("inputEmail").trim();
		String password=request.getParameter("inputPassword").trim();
		String trueName=request.getParameter("inputName").trim();
		String identity=request.getParameter("1").trim();
		String userType=null;
		
		if(identity.equals("developer")){
			userType="DEVELOPER";
		}else if(identity.equals("manager")){
			userType="MANAGER";
		}
		
		boolean isSuccess=DaoFactory.getUserDao().addUser(new User(0,trueName,nickname,password,userType));
		if(!isSuccess){
			pw.print("<script>alert('用户名已存在！注册失败！');location.href='./register/register.jsp'</script>"); 
			return;
		}
		
		//修改cookie
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
		
		if(cookieFound){
			cookie.setValue(nickname);
			response.addCookie(cookie);	
		}else{
			cookie = new Cookie("LoginCookie",nickname);
			cookie.setMaxAge(Integer.MAX_VALUE);
			response.addCookie(cookie);
		}
		
		try {
			context.getRequestDispatcher("/login/login.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

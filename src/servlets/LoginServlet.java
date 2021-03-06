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

import bean.RiskItemListBean;
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
		//response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter pw=response.getWriter();

		//
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
			pw.print("<script>alert('Nickname or password is incorrect!');location.href='./login/login.jsp'</script>"); 
		}else{
			
			if(cookieFound){//cookie闁诲孩绋掗敋婵狅拷閿燂拷
				if(!cookie.getValue().trim().equals(userId.trim())){//cookie
					cookie.setValue(userId.trim());
					response.addCookie(cookie);								
				}	
			}else{//cookie婵炴垶鎸哥粔鎾偤閵娾晛鎹堕柨鐕傛嫹
				cookie = new Cookie("LoginCookie",userId.trim());
				cookie.setMaxAge(Integer.MAX_VALUE);
				response.addCookie(cookie);
			}
					
			session.setAttribute("LoginId",user.getUserId()); 
			session.setAttribute("LoginUser", user);
			request.setAttribute("login", user.getUserId());
			RiskItemListBean riskItemList=new RiskItemListBean();
			riskItemList.setRiskItemList(DaoFactory.getRiskItemDao().findAllRiskItem());
			session.setAttribute("riskItemList",riskItemList);
			
			try {
				context.getRequestDispatcher("/checkRisk/checkRiskList.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				 e.printStackTrace();
			}
			
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

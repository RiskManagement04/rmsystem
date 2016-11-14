package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.RiskItemListBean;
import factory.DaoFactory;

/**
 * Servlet implementation class DeleteRiskItemServlet
 */
@WebServlet("/DeleteRiskItemServlet")
public class DeleteRiskItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteRiskItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		ServletContext context = getServletContext();
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter pw=response.getWriter();
		
		int deleteRiskItemId=Integer.parseInt(request.getParameter("deleteRiskItemId"));
		DaoFactory.getRiskItemDao().deleteRiskItem(deleteRiskItemId);
		
		int userId=(Integer)session.getAttribute("LoginId");
		RiskItemListBean riskItemList=new RiskItemListBean();
		riskItemList.setRiskItemList(DaoFactory.getRiskItemDao().findAllRiskItem(userId));
		session.setAttribute("riskItemList",riskItemList);
		
		pw.print("<script>location.href='./checkRisk/checkRiskList.jsp'</script>"); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

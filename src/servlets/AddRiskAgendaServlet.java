package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.RiskAgendaListBean;
import bean.RiskItemListBean;
import factory.DaoFactory;
import model.RiskAgenda;

/**
 * Servlet implementation class AddRiskAgenda
 */
@WebServlet("/AddRiskAgenda")
public class AddRiskAgendaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRiskAgendaServlet() {
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
		
		int agendaId=0;
		String agendaName=request.getParameter("agendaName").trim();
		java.sql.Date createDate=new java.sql.Date(System.currentTimeMillis());
		int submitterId=(Integer)session.getAttribute("LoginId");
		String submitterName="";
		RiskAgenda agenda=new RiskAgenda(agendaName, createDate, submitterId, submitterName);
		
		boolean isSuccess=false;
		try {
			isSuccess = DaoFactory.getRiskAgendaDao().addRiskAgenda(agenda);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!isSuccess){
			pw.print("<script>location.href='./checkRisk/checkRiskAgendaList.jsp'</script>"); 
		}else{
			RiskAgendaListBean riskAgendaList=new RiskAgendaListBean();
			try {
				riskAgendaList.setRiskAgendaList(DaoFactory.getRiskAgendaDao().findRiskAgendaByUser(submitterId));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.setAttribute("riskAgendaList",riskAgendaList);
			pw.print("<script>location.href='./checkRisk/checkRiskAgendaList.jsp'</script>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

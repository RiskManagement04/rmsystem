package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.RiskItemListBean;
import model.RiskAgenda;
import model.RiskItem;
import factory.DaoFactory;

/**
 * Servlet implementation class CheckRiskAgendaServlet
 */
@WebServlet("/CheckRiskAgendaServlet")
public class CheckRiskAgendaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckRiskAgendaServlet() {
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
		
		int agendaId=Integer.parseInt(request.getParameter("agendaId").trim());
		
		int userId=(Integer)session.getAttribute("LoginId");
		List riskAgendaList= DaoFactory.getRiskAgendaDao().findRiskAgendaByUser(userId);
		
		ArrayList<RiskItem> riskItemList=new ArrayList<RiskItem>();
		for(int i=0;i<riskAgendaList.size();i++){
			RiskAgenda riskAgenda=(RiskAgenda)riskAgendaList.get(i);
			if(riskAgenda.getAgendaId()==agendaId){
				riskItemList=riskAgenda.getRisks();
				break;
			}
		}
		RiskItemListBean riskItemListBean=new RiskItemListBean();
		riskItemListBean.setRiskItemList(riskItemList);
		session.setAttribute("RiskItemList", riskItemListBean);
		
		/**
		 * 跳转到查看计划下风险的jsp页面
		 */
		try {
			context.getRequestDispatcher("/checkRisk/checkRiskList.jsp").forward(request, response);
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
	}

}

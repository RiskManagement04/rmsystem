package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.RiskTrackingItemListBean;
import factory.DaoFactory;

/**
 * Servlet implementation class CheckRiskTrackingServlet
 */
@WebServlet("/CheckRiskTrackingServlet")
public class CheckRiskTrackingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckRiskTrackingServlet() {
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
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter pw=response.getWriter();
	
		String id=request.getParameter("riskItemId").trim();
		int riskItemId=Integer.parseInt(id);
		
		List riskTrackingList=DaoFactory.getRiskTrackingItemDao().findRiskTrackingItem(riskItemId);
		RiskTrackingItemListBean riskTrackingItemList=new RiskTrackingItemListBean();
		riskTrackingItemList.setRiskTrackingItemList(riskTrackingList);
		
		session.setAttribute("riskTrackingList",riskTrackingItemList);
		session.setAttribute("riskItemIdOfDetails", riskItemId);
		
		try {
			context.getRequestDispatcher("/checkRisk/followRisk.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO  Auto-generated catch block
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

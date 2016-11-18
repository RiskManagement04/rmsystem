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
import bean.RiskTrackingItemListBean;
import factory.DaoFactory;
import model.RiskStatus;
import model.RiskTrackingItem;

/**
 * Servlet implementation class AddRiskTrackingServlet
 */
@WebServlet("/AddRiskTrackingServlet")
public class AddRiskTrackingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRiskTrackingServlet() {
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
		
		int riskTrackingItemId=0;
		int riskItemId=(Integer)session.getAttribute("riskItemIdOfDetails");
		String riskItemName="";
		int trackerId=(Integer)session.getAttribute("LoginId");
		String trackerName="";
		java.sql.Date createDate=new java.sql.Date(System.currentTimeMillis());
		
		RiskStatus riskStatus=null;
		if(request.getParameter("riskStatus").trim().equals("predicted")){
			riskStatus=RiskStatus.PREDICTED;
		}else if(request.getParameter("riskStatus").trim().equals("happened")){
			riskStatus=RiskStatus.HAPPENED;
		}else if(request.getParameter("riskStatus").trim().equals("solved")){
			riskStatus=RiskStatus.SOLVED;
		}
		
		String riskContent=request.getParameter("riskContent").trim();
		String measures=request.getParameter("measures").trim();
		/**
		 * 新增风险跟踪时，增加风险可能性
		 */
		int possibility=0;
		String strPossibility=request.getParameter("possibility").trim();
		if(strPossibility.equals("high")){
			possibility=3;
		}else if(strPossibility.equals("middle")){
			possibility=2;
		}else{
			possibility=1;
		}
		/**
		 * 新增风险跟踪时，增加风险影响
		 */
		int impact=0;
		String strImpact=request.getParameter("impact").trim();
		if(strImpact.equals("high")){
			impact=3;
		}else if(strImpact.equals("middle")){
			impact=2;
		}else{
			impact=1;
		}
		RiskTrackingItem item=new RiskTrackingItem(riskTrackingItemId,riskItemId,trackerId,createDate,riskStatus,riskContent,measures,riskItemName, possibility, impact);
		boolean isSuccess=DaoFactory.getRiskTrackingItemDao().addRiskTrackingItem(item);
		
		if(!isSuccess){
			pw.print("<script>location.href='./checkRisk/followRisk.jsp'</script>"); 
		}else{
			RiskTrackingItemListBean list=new RiskTrackingItemListBean();
			list.setRiskTrackingItemList(DaoFactory.getRiskTrackingItemDao().findRiskTrackingItem(riskItemId));
			session.setAttribute("riskTrackingList",list);
			pw.print("<script>location.href='./checkRisk/followRisk.jsp'</script>");
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

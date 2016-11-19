package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.RiskItemListBean;
import factory.DaoFactory;
import model.RiskItem;
import model.RiskStatus;
import model.User;

/**
 * Servlet implementation class AddRiskItemServlet
 */
@WebServlet("/AddRiskItemServlet")
public class AddRiskItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRiskItemServlet() {
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
			
		int riskItemId=0;
		int projectId=Integer.parseInt(request.getParameter("projectName"));
		int submitterId=(Integer)session.getAttribute("LoginId");
		String submitterName=null;
		java.sql.Date createDate=new java.sql.Date(System.currentTimeMillis());
		/*
		 * 新增风险类别*/
		String riskType=request.getParameter("riskType").trim();
		
		String riskName=request.getParameter("riskName").trim();
		String riskContent=request.getParameter("riskContent").trim();
		String trigger=request.getParameter("trigger").trim();
		int possibility=0;
		if(request.getParameter("possibility").trim().equals("high")){
			possibility=3;
		}else if(request.getParameter("possibility").trim().equals("middle")){
			possibility=2;
		}else if(request.getParameter("possibility").trim().equals("low")){
			possibility=1;
		}
		
		int impact=0;
		if(request.getParameter("impact").trim().equals("high")){
			impact=3;
		}else if(request.getParameter("impact").trim().equals("middle")){
			impact=2;
		}else if(request.getParameter("impact").trim().equals("low")){
			impact=1;
		}		
		
//		RiskStatus riskStatus=null;
//		if(request.getParameter("riskStatus").trim().equals("predicted")){
//			riskStatus=RiskStatus.PREDICTED;
//		}else if(request.getParameter("riskStatus").trim().equals("happened")){
//			riskStatus=RiskStatus.HAPPENED;
//		}else if(request.getParameter("riskStatus").trim().equals("solved")){
//			riskStatus=RiskStatus.SOLVED;
//		}
		String  riskStatus=request.getParameter("riskStatus").trim();
		
		String projectName="";
		/*
		 * 风险措施*/
		String measures="";
		ArrayList<User> trackers=new ArrayList<User>();
		RiskItem item=new RiskItem(riskItemId,projectId,submitterId,createDate,riskName,riskContent,trigger,
				possibility,impact,riskStatus,projectName, measures, riskType, null);
		
		/**
		 * 获取当前的计划id
		 */
		int riskAgendaId=Integer.parseInt(request.getParameter("agendaId").trim());
		String result="";
		try {
			result = DaoFactory.getRiskAgendaDao().addRiskItem(riskAgendaId, item);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RiskItemListBean riskItemList=new RiskItemListBean();
		riskItemList.setRiskItemList(DaoFactory.getRiskItemDao().findAllRiskItem());
		session.setAttribute("riskItemList",riskItemList);
		pw.print("<script>alert(result);location.href='./checkRisk/checkRiskList.jsp'</script>"); 
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

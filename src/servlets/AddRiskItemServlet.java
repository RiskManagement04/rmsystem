package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

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
		
		RiskStatus riskStatus=null;
		if(request.getParameter("riskStatus").trim().equals("predicted")){
			riskStatus=RiskStatus.PREDICTED;
		}else if(request.getParameter("riskStatus").trim().equals("happened")){
			riskStatus=RiskStatus.HAPPENED;
		}else if(request.getParameter("riskStatus").trim().equals("solved")){
			riskStatus=RiskStatus.SOLVED;
		}
		
		String projectName="";
		RiskItem item=new RiskItem(riskItemId,projectId,submitterId,createDate,riskName,riskContent,trigger,
				possibility,impact,riskStatus,projectName);
		
		//增加
		boolean isSuccess=DaoFactory.getRiskItemDao().addRiskItem(item);
		if(!isSuccess){
			pw.print("<script>alert('增加风险条目失败！');location.href='./checkRisk/checkRiskList.jsp'</script>"); 
		}else{
			RiskItemListBean riskItemList=new RiskItemListBean();
			riskItemList.setRiskItemList(DaoFactory.getRiskItemDao().findAllRiskItem(submitterId));
			session.setAttribute("riskItemList",riskItemList);
			pw.print("<script>alert('增加风险条目成功！');location.href='./checkRisk/checkRiskList.jsp'</script>");
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

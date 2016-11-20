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
import model.RiskType;
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
			
		RiskItem riskItem=new RiskItem();
		int riskItemId=0;
		//int agendaId=Integer.parseInt(request.getParameter("agendaName"));
		int projectId=Integer.parseInt(request.getParameter("projectName"));
		riskItem.setProjectId(projectId);
		String riskType=request.getParameter("riskType").trim();
		RiskType type=riskItem.convertRiskTypefromString(riskType);
		riskItem.setRiskType(type);
		
		String riskName=request.getParameter("riskName").trim();
		riskItem.setRiskName(riskName);
		String trigger=request.getParameter("trigger").trim();
		riskItem.setTrigger(trigger);
		String riskContent=request.getParameter("riskContent").trim();
		riskItem.setRiskContent(riskContent);
		String measures=request.getParameter("measures").trim();
		riskItem.setMeasures(measures);
		int possibility=0;
		if(request.getParameter("possibility").trim().equals("high")){
			possibility=3;
		}else if(request.getParameter("possibility").trim().equals("middle")){
			possibility=2;
		}else if(request.getParameter("possibility").trim().equals("low")){
			possibility=1;
		}
		riskItem.setPossibility(possibility);
		
		int impact=0;
		if(request.getParameter("impact").trim().equals("high")){
			impact=3;
		}else if(request.getParameter("impact").trim().equals("middle")){
			impact=2;
		}else if(request.getParameter("impact").trim().equals("low")){
			impact=1;
		}	
		riskItem.setImpact(impact);
		
		String  riskStatus=request.getParameter("riskStatus").trim();
		RiskStatus status=riskItem.convertRiskStatusfromString(riskStatus);
		riskItem.setRiskStatus(status);
		
		int submitterId=(Integer)session.getAttribute("LoginId");
		riskItem.setSubmitterId(submitterId);
		java.sql.Date createDate=new java.sql.Date(System.currentTimeMillis());
		riskItem.setCreateDate(createDate);
		
		String[] trackersId=request.getParameterValues("trackres");
		
		ArrayList<User> trackers=new ArrayList<User>();
		User user=new User();//创建风险条目的用户
		user.setUserId(submitterId);
		trackers.add(user);
		if(trackersId.length>0){
			for(int i=0;i<trackersId.length;i++){
				int trackerId=Integer.parseInt(trackersId[i]);
				User u=new User();
				u.setUserId(trackerId);
				trackers.add(u);
			}			
		}

		riskItem.setTrackers(trackers);
		int riskAgendaId=Integer.parseInt((String)session.getAttribute("agendaId"));
		
		String result="";
		try {
			result = DaoFactory.getRiskAgendaDao().addRiskItem(riskAgendaId, riskItem);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RiskItemListBean riskItemList=new RiskItemListBean();
		riskItemList.setRiskItemList(DaoFactory.getRiskItemDao().findAllRiskItem());
		session.setAttribute("riskItemList",riskItemList);
		String print="<script>alert('"+result+"');location.href='./checkRisk/AgendaRiskItemList.jsp'</script>";
		pw.print(print); 
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

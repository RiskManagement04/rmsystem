package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.RiskItem;
import model.RiskType;
import model.RiskTypeRank;
import factory.DaoFactory;

/**
 * Servlet implementation class SearchRisk
 */
@WebServlet("/SearchRisk")
public class SearchRiskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchRiskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		ServletContext context = getServletContext();
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter pw=response.getWriter();
		
		
		Date startDate=null;
		Date endDate=null;
		String startTime=request.getParameter("startDate").trim();
		System.out.println("before"+startTime);
		String endTime=request.getParameter("endDate").trim();
		
		startDate=SearchRiskServlet.strToDate(startTime);
		endDate=SearchRiskServlet.strToDate(endTime);
		System.out.println("after"+startDate.toString());
		String str=request.getParameter("condition").trim();
		List riskItemList=new ArrayList<>();
		List<RiskTypeRank> riskTypeRankLst=new ArrayList<RiskTypeRank>();
		try{
			if(str.equals("identify")){
				
				riskTypeRankLst=DaoFactory.getRiskItemDao().findRiskItemTypeByCreatingMost(startDate, endDate);
				
				if(riskTypeRankLst.size()==0){
					System.out.println("try");
					pw.print("<script>alert('There is no riskitem!');location.href='./checkRisk/AgendaRiskItemList.jsp'</script>"); 
					return;

				}else{
					RiskTypeRank typeRank=riskTypeRankLst.get(0);
					RiskType type=typeRank.getRiskType();
					riskItemList=DaoFactory.getRiskItemDao().findRiskItemByCreatingMost(startDate, endDate, type);
				}
			
			}else{
				riskTypeRankLst=DaoFactory.getRiskItemDao().findRiskItemTypeByHappeningMost(startDate, endDate);
				if(riskTypeRankLst.size()==0){
					pw.print("<script>alert('There is no riskitem!');location.href='./checkRisk/AgendaRiskItemList.jsp'</script>");
					return;
				}else{
					RiskTypeRank typeRank=riskTypeRankLst.get(0);
					RiskType type=typeRank.getRiskType();
					riskItemList=DaoFactory.getRiskItemDao().findRiskItemByHappeningMost(startDate, endDate, type);
				}
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		String riskTypeStr="";
		String riskAmountStr="";
		String temp=",";
		for(int i=0;i<riskTypeRankLst.size();i++){
			
			RiskType riskType=riskTypeRankLst.get(i).getRiskType();
			String type=((RiskItem)riskItemList.get(0)).convertRiskTypeToString(riskType);
			
			riskTypeStr+=type;
			
			int amount=riskTypeRankLst.get(i).getAmount();
			riskAmountStr+=String.valueOf(amount);
			if(i!=riskTypeRankLst.size()-1){
				riskTypeStr+= temp;
				riskAmountStr+=temp;
			}
			
		}
		System.out.print("Type"+riskTypeStr);
		request.setAttribute("riskType", riskTypeStr);		
		request.setAttribute("riskRank", riskAmountStr);
		
		session.setAttribute("riskItemList",riskItemList);
		session.setAttribute("riskTypeRank", riskTypeRankLst);
		try {
			context.getRequestDispatcher("/checkRisk/queryResult.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private static java.sql.Date strToDate(String strDate) {  
        String str = strDate;  
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
        java.util.Date d = null;  
        try {  
            d = format.parse(str);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        System.out.println("middle"+d.toString());
        java.sql.Date date = new java.sql.Date(d.getTime());  
        return date;  
    }  



}

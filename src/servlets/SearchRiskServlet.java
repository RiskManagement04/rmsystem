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
		
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-mm-dd");
		Date startDate=null;
		Date endDate=null;
		String startTime=request.getParameter("startDate").trim();
		String endTime=request.getParameter("endDate").trim();
		try {
			startDate=(Date) sf.parse(startTime);
			endDate=(Date) sf.parse(endTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String str=request.getParameter("condition").trim();
		List riskItemList=new ArrayList<>();
		List<RiskTypeRank> riskTypeRankLst=new ArrayList<RiskTypeRank>();
		try{
			if(str.equals("ʶ�����")){
				
				riskTypeRankLst=DaoFactory.getRiskItemDao().findRiskItemTypeByCreatingMost(startDate, endDate);
				
				RiskTypeRank typeRank=riskTypeRankLst.get(0);
				RiskType type=typeRank.getRiskType();
				riskItemList=DaoFactory.getRiskItemDao().findRiskItemByCreatingMost(startDate, endDate, type);
			}else{
				riskTypeRankLst=DaoFactory.getRiskItemDao().findRiskItemTypeByHappeningMost(startDate, endDate);
				RiskTypeRank typeRank=riskTypeRankLst.get(0);
				RiskType type=typeRank.getRiskType();
				riskItemList=DaoFactory.getRiskItemDao().findRiskItemByHappeningMost(startDate, endDate, type);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		ArrayList<String> riskTypeList=new ArrayList<String>();
		ArrayList<Integer> riskAmountList=new ArrayList<Integer>();
		
		for(int i=0;i<riskTypeRankLst.size();i++){
			
			RiskType riskType=riskTypeRankLst.get(i).getRiskType();
			String type=((RiskItem)riskItemList.get(0)).convertRiskTypeToString(riskType);
			riskTypeList.add(i, type);
			riskAmountList.add(i, riskTypeRankLst.get(i).getAmount());
		}
		request.setAttribute("riskType", riskTypeList);
		request.setAttribute("riskRank", riskAmountList);
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

}

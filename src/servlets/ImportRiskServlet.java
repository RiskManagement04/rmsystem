package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
import model.RiskItem;
import factory.DaoFactory;

/**
 * Servlet implementation class ImportRiskServlet
 */
@WebServlet("/ImportRiskServlet")
public class ImportRiskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImportRiskServlet() {
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
		System.out.println("biubiu");
		int agendaId=(Integer)session.getAttribute("agendaId");
		
		String[] values=request.getParameterValues("isChoosed");
		
		ArrayList<Integer> chosedRiskIdLst=new ArrayList<Integer>();
		if(values.length==0){
			
		}
		for(int i=0;i<values.length;i++){
			chosedRiskIdLst.add(Integer.parseInt(values[i]));
		}
		System.out.println("choose"+chosedRiskIdLst.size());
		boolean isSuccess=false;
		try {
			isSuccess = DaoFactory.getRiskAgendaDao().addRiskItem(agendaId, chosedRiskIdLst);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!isSuccess){
			pw.print("<script>alert('Import failed!');location.href='./login/login.jsp'</script>"); 
		}else{
			ArrayList<RiskItem> riskList=new ArrayList<RiskItem>();
			RiskItemListBean riskItemBean=new RiskItemListBean();
			
			try {
				riskList=DaoFactory.getRiskAgendaDao().findRiskItemByAgenda(agendaId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			riskItemBean.setRiskItemList(riskList);
			session.setAttribute("agendaRiskItemList", riskItemBean);
			 context.getRequestDispatcher("/checkRisk/AgendaRiskItemList.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

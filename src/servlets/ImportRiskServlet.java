package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		int agendaId=(Integer)session.getAttribute("agendaId");
		
		String[] values=request.getParameterValues("isChoosed");
		ArrayList<Integer> chosedRiskIdLst=new ArrayList<Integer>();
		for(int i=0;i<values.length;i++){
			chosedRiskIdLst.add(Integer.parseInt(values[i]));
		}
		
		boolean isSuccess=DaoFactory.getRiskAgendaDao().addRiskItem(agendaId, chosedRiskIdLst);
		if(!isSuccess){
			pw.print("<script>alert('µº»Î ß∞‹!');location.href='./login/login.jsp'</script>"); 
		}else{
			context.getRequestDispatcher("/checkRisk/checkRiskList.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

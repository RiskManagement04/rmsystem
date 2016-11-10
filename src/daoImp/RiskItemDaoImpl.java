package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoHelper;
import dao.RiskItemDao;
import edu.nju.onlinecheck.model.Exercise;

public class RiskItemDaoImpl implements RiskItemDao{
	
	private static RiskItemDaoImpl riskItemDao=new RiskItemDaoImpl();
	private static DaoHelper daoHelper=DaoHelperImpl.getBaseDaoInstance();
	
	private RiskItemDaoImpl(){
		
	}
	
	public static RiskItemDaoImpl getInstance(){
		return riskItemDao;
	}

	@Override
	public List findAllRiskItem(int userId) {
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		ArrayList riskItemList=new ArrayList();
		
		try {
			stmt = con.prepareStatement("select * from exercise where studentid=?");
			stmt.setInt(1,studentid);
			result = stmt.executeQuery();
			
			while(result.next()){
				Exercise ex=new Exercise();
				ex.setExerciseid(result.getInt("exerciseid"));
				ex.setExercisename(result.getString("exercisename").trim());
				
				if(result.getDate("submitdate")!=null){
					ex.setSubmitdate(result.getDate("submitdate"));
				}
								
				if(result.getObject("score")!=null){
					ex.setScore(result.getDouble("score"));
				}
				
				allExercise.add(ex);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResult(result);
		}
		
		return riskItemList;
	}

}

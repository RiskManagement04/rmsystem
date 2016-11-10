package daoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.RiskTrackingItem;
import dao.DaoHelper;
import dao.RiskTrackingItemDao;

public class RiskTrackingItemDaoImpl implements RiskTrackingItemDao{

	private static RiskTrackingItemDaoImpl riskTrackingItemDao=new RiskTrackingItemDaoImpl();
	private static DaoHelper daoHelper=DaoHelperImpl.getBaseDaoInstance();
	
	private RiskTrackingItemDaoImpl(){
		
	}
	
	public static RiskTrackingItemDaoImpl getInstance(){
		return riskTrackingItemDao;
	}
	@Override
	public List<RiskTrackingItem> findRiskTrackingItem(int riskItemId) {
		// TODO Auto-generated method stub
		java.sql.Connection con=daoHelper.getConnection();
		java.sql.PreparedStatement statement=null;
		ResultSet result=null;
		ArrayList<RiskTrackingItem> trackingItemList=new ArrayList<RiskTrackingItem>();
		
		try {
			statement=con.prepareStatement("select * from risktrackingitem where riskTrackingItemId=?");
			statement.setInt(1, riskItemId);
			
			result=statement.executeQuery();
			
			while(result.next()){
				RiskTrackingItem item=new RiskTrackingItem();
				item.setRiskTrackingItemId(result.getInt("riskTrackingItemId"));
				item.setRiskItemId(result.getInt("riskItemId"));
				item.setTrackerId(result.getInt("trackerId"));
				item.setCreateTime(result.getDate("createTime"));
				item.setRiskStatus(result.getString("riskStatus"));
				item.setRiskContent(result.getString("riskContent"));
				item.setMeasures(result.getString("measures"));
				
				trackingItemList.add(item);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(statement);
			daoHelper.closeResult(result);
		}
		return trackingItemList;
	}

	@Override
	public boolean addRiskTrackingItem(RiskTrackingItem item) {
		java.sql.Connection con=daoHelper.getConnection();
		java.sql.PreparedStatement statement=null;
//		ResultSet result=null;
//		ArrayList<RiskTrackingItem> trackingItemList=new ArrayList<RiskTrackingItem>();
		
		
		return false;
	}

}

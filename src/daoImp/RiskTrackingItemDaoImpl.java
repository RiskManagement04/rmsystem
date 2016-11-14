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
	
	private  RiskTrackingItemDaoImpl(){
		
	}
	
	public static RiskTrackingItemDaoImpl getInstance(){
		return riskTrackingItemDao;
	}
	@Override
	public List findRiskTrackingItem(int riskItemId) {
		// TODO Auto-generated method stub
		java.sql.Connection con=daoHelper.getConnection();
		java.sql.PreparedStatement statement=null;
		ResultSet result=null;
		ArrayList trackingItemList=new ArrayList();
		
		try {
			statement=con.prepareStatement("select * from RiskTrackingItem r,User u,RiskItem risk where r.riskItemId=? and r.trackerId=u.userId "
					+ "and r.riskItemId=risk.riskItemId");
			statement.setInt(1, riskItemId);
			
			result=statement.executeQuery();
			
			while(result.next()){
				RiskTrackingItem item=new RiskTrackingItem();
				item.setRiskTrackingItemId(result.getInt("r.riskTrackingItemId"));
				item.setRiskItemId(result.getInt("r.riskItemId"));
				item.setTrackerId(result.getInt("r.trackerId"));
				item.setCreateTime(result.getDate("r.createTime"));
				item.setRiskStatus(result.getString("r.riskStatus").trim());
				item.setRiskContent(result.getString("r.riskContent").trim());
				item.setMeasures(result.getString("r.measures").trim());
				item.setTrackerName(result.getString("u.trueName").trim());
				item.setRiskItemName(result.getString("risk.riskName").trim());
				
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

		boolean isSuccess=true;
		try {
			statement=con.prepareStatement("insert into RiskTrackingItem(riskItemId,trackerId,createTime,riskStatus,riskContent,measures) values(?,?,?,?,?,?)");
			statement.setInt(1, item.getRiskItemId());
			statement.setInt(2,item.getTrackerId());
			statement.setDate(3, item.getCreateTime());
			statement.setString(4, item.getRiskStatusString());
			statement.setString(5,item.getRiskContent());
			statement.setString(6, item.getMeasures());
			
			statement.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(statement);
			
		}
		return isSuccess;
	}

}

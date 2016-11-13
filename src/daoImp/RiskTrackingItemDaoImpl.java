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
			statement=con.prepareStatement("select * from RiskTrackingItem,User where riskItemId=? and trackerId=userId");
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
				item.setTrackerName(result.getString("trueName"));
				
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
			statement=con.prepareStatement("insert into risktrackingitem(riskItemId,trackerId,createTime,riskStatus,riskContent,measures) values(?,?,?,?,?,?)");
			statement.setInt(1, item.getRiskItemId());
			statement.setInt(2,item.getTrackerId());
			statement.setDate(3, item.getCreateTime());
			statement.setString(4, item.getRiskStatusString());
			statement.setString(5,item.getRiskContent());
			statement.setString(6, item.getMeasures());
			
			isSuccess=statement.execute();
			
			
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

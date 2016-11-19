package daoimp;

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
				item.setPossibility(result.getInt("r.possibility"));
				item.setImpact(result.getInt("r.impact"));
				
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
		//插入风险跟踪条目列表
		try {
			statement=con.prepareStatement("insert into RiskTrackingItem(riskItemId,trackerId,createTime,riskStatus,riskContent,measures,"
					+ "possibility,impact) values(?,?,?,?,?,?,?,?)");
			statement.setInt(1, item.getRiskItemId());
			statement.setInt(2,item.getTrackerId());
			statement.setDate(3, item.getCreateTime());
			statement.setString(4, item.getRiskStatusString().trim());
			statement.setString(5,item.getRiskContent().trim());
			statement.setString(6, item.getMeasures().trim());
			statement.setInt(7, item.getPossibility());
			statement.setInt(8, item.getImpact());
			
			statement.execute();						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isSuccess=false;
		}finally{
			daoHelper.closePreparedStatement(statement);			
		}		
		
		if(!isSuccess){//如果插入失败
			daoHelper.closeConnection(con);
			return isSuccess;
		}else{//如果插入成功
			//根据RiskItemId修改相应的风险条目
			int i=0;
			try {
				statement=con.prepareStatement("update RiskItem set riskContent=?,possibility=?,impact=?,riskStatus=?,measures=?"
						+ " where riskItemId=?");
				statement.setString(1, item.getRiskContent().trim());
				statement.setInt(2, item.getPossibility());
				statement.setInt(3, item.getImpact());
				statement.setString(4, item.getRiskStatusString().trim());
				statement.setString(5, item.getMeasures().trim());
				statement.setInt(6, item.getRiskItemId());
				
				i=statement.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				daoHelper.closePreparedStatement(statement);	
				daoHelper.closeConnection(con);
			}	
			
			if(i!=1){
				isSuccess=false;
			}
			
			return isSuccess;
		}
	}
	
	@Override
	public boolean deleteRiskTrackingItem(int riskTrackingItemId) {
		java.sql.Connection con=daoHelper.getConnection();
		java.sql.PreparedStatement statement=null;

		boolean isSuccess=true;
		try {
			statement=con.prepareStatement("delete from RiskTrackingItem where riskItemId=?");
			statement.setInt(1,riskTrackingItemId);
		
			
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

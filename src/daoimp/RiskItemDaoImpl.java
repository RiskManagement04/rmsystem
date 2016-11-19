package daoimp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoHelper;
import dao.RiskItemDao;
import model.RiskItem;
import model.RiskStatus;

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
			

						
			stmt = con.prepareStatement("select * from Developing d,RiskItem r,User u,Project p where d.userId=? and d.projectId=r.projectId and"
					+ " r.submitterId=u.userId and d.projectId=p.projectId");
			stmt.setInt(1,userId);
			result = stmt.executeQuery();
			
			while(result.next()){
				RiskItem item=new RiskItem();
				item.setCreateDate(result.getDate("createDate"));
				item.setImpact(result.getInt("impact"));
				item.setPossibility(result.getInt("possibility"));
				item.setProjectId(result.getInt("r.projectId"));
				item.setRiskContent(result.getString("riskContent").trim());
				item.setRiskItemId(result.getInt("riskItemId"));
				item.setRiskName(result.getString("riskName").trim());
				
				String status=result.getString("riskStatus").trim();				
				
				if(status.equals("PREDICTED")){
					item.setRiskStatus(RiskStatus.PREDICTED);	
				}else if(status.equals("HAPPENED")){
					item.setRiskStatus(RiskStatus.HAPPENED);	
				}else{
					item.setRiskStatus(RiskStatus.SOLVED);	
				}
							
				item.setSubmitterId(result.getInt("submitterId"));
				item.setTrigger(result.getString("trigger").trim());
				item.setSubmitterName(result.getString("u.trueName").trim());
				
				item.setProjectName(result.getString("p.projectName").trim());

				riskItemList.add(item);
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
/*
	@Override
	public boolean addRiskItem(RiskItem riskItem) {
			
		java.sql.Connection con=daoHelper.getConnection();
		java.sql.PreparedStatement statement=null;

		boolean isSuccess=true;
		try {
			
			statement=con.prepareStatement("insert into RiskItem(projectId,submitterId,createDate,riskName,riskContent,`trigger`,possibility,impact,riskStatus) values(?,?,?,?,?,?,?,?,?)");
			statement.setInt(1,riskItem.getProjectId());
			statement.setInt(2,riskItem.getSubmitterId());
			statement.setDate(3, riskItem.getCreateDate());
			statement.setString(4, riskItem.getRiskName());
			statement.setString(5, riskItem.getRiskContent());
			statement.setString(6, riskItem.getTrigger());
			statement.setInt(7, riskItem.getPossibility());
			statement.setInt(8, riskItem.getImpact());
			statement.setString(9, riskItem.getRiskStatusString());
			
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
*/	
	@Override
	public boolean deleteRiskItem(int riskItemId) {
		java.sql.Connection con=daoHelper.getConnection();
		java.sql.PreparedStatement statement=null;

		boolean isSuccess=true;
		try {
			statement=con.prepareStatement("delete from RiskItem where riskItemId=?");
			statement.setInt(1,riskItemId);
			isSuccess=statement.execute();
			
			final RiskTrackingItemDaoImpl riskTrackingDao=RiskTrackingItemDaoImpl.getInstance();
			boolean isTrackingSuccess=riskTrackingDao.deleteRiskTrackingItem(riskItemId);
			
			isSuccess=isSuccess&isTrackingSuccess;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(statement);
			
		}
		return isSuccess;
	}

	@Override
	public List findRiskItemByCreatingMost(Date startDate, Date finishDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findRiskItemByHappeningMost(Date startDate, Date finishDate) {
		// TODO Auto-generated method stub
		return null;
	}

}

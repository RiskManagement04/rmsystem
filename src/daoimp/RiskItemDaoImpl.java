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
import model.RiskType;
import model.User;

public class RiskItemDaoImpl implements RiskItemDao{
	
	private static RiskItemDaoImpl riskItemDao=new RiskItemDaoImpl();
	private static DaoHelper daoHelper=DaoHelperImpl.getBaseDaoInstance();
	
	private RiskItemDaoImpl(){
		
	}
	
	public static RiskItemDaoImpl getInstance(){
		return riskItemDao;
	}

	@Override
	public List findAllRiskItem() {
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		ArrayList riskItemList=new ArrayList();
		
		try {			
			stmt = con.prepareStatement("select * from RiskItem r,Project p,User u where r.projectId=p.projectId "
					+ "and r.submitterId=u.userId");
			result = stmt.executeQuery();
			
			while(result.next()){
				RiskItem item=new RiskItem();
				int riskItemId=result.getInt("r.riskItemId");
				item.setCreateDate(result.getDate("r.createDate"));
				item.setImpact(result.getInt("r.impact"));
				item.setPossibility(result.getInt("r.possibility"));
				item.setProjectId(result.getInt("r.projectId"));
				item.setRiskContent(result.getString("r.riskContent").trim());
				item.setRiskItemId(riskItemId);
				item.setRiskName(result.getString("r.riskName").trim());
				
				String status=result.getString("r.riskStatus").trim();				
				
				if(status.equals("PREDICTED")){
					item.setRiskStatus(RiskStatus.PREDICTED);	
				}else if(status.equals("HAPPENED")){
					item.setRiskStatus(RiskStatus.HAPPENED);	
				}else{
					item.setRiskStatus(RiskStatus.SOLVED);	
				}
							
				item.setSubmitterId(result.getInt("r.submitterId"));
				item.setTrigger(result.getString("r.trigger").trim());
				item.setSubmitterName(result.getString("u.trueName").trim());
				
				item.setProjectName(result.getString("p.projectName").trim());
				item.setMeasures(result.getString("r.measures").trim());
				
				String riskType=result.getString("r.riskType").trim();
				item.setRiskType(item.convertRiskTypefromString(riskType));
				
				//获取风险条目中的追踪者列表
				PreparedStatement statement3=null;
				ResultSet result3=null;
				ArrayList<User> trackers=new ArrayList<User>();
				statement3=con.prepareStatement("select * from Tracking t,User u where t.riskItemId=? and "
						+ "t.userId=u.userId");
				
				statement3.setInt(1, riskItemId);				
				result3 = statement3.executeQuery();
				
				while(result3.next()){
					User user=new User();
					int trackerId=result3.getInt("u.userId");
					user.setUserId(trackerId);
					String trueName=result3.getString("u.trueName").trim();
					user.setTrueName(trueName);
					String nickName=result3.getString("u.nickName").trim();
					user.setNickName(nickName);
					String password=result3.getString("u.password").trim();
					user.setPassword(password);
					String identity=result3.getString("u.identity").trim();
					user.setIdentity(user.convertIdentityFromString(identity));
					
					trackers.add(user);
				}
				
				daoHelper.closeResult(result3);
				daoHelper.closePreparedStatement(statement3);
				
				item.setTrackers(trackers);

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
	public List findRiskItemTypeByCreatingMost(Date startDate, Date finishDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findRiskItemByCreatingMost(Date startDate, Date finishDate, RiskType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findRiskItemTypeByHappeningMost(Date startDate, Date finishDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findRiskItemByHappeningMost(Date startDate, Date finishDate, RiskType type) {
		// TODO Auto-generated method stub
		return null;
	}	
	
/*
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
*/


}

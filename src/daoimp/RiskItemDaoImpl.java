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
import model.RiskTypeRank;
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
	public List findRiskItemTypeByCreatingMost(Date startDate, Date finishDate) throws SQLException {
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		ArrayList riskTypeRankList=new ArrayList();
		
		stmt=con.prepareStatement("select riskType,count(riskItemId) as c from RiskItem "
				+ "where createDate between ? and ? "
				+ "group by riskType order by c desc");
		stmt.setDate(1, startDate);
		stmt.setDate(2, finishDate);
		result = stmt.executeQuery();
		
		while(result.next()){
			RiskTypeRank rank=new RiskTypeRank();
			String type=result.getString("riskType").trim();
			rank.setRiskType(rank.convertRiskTypefromString(type));
			
			int amount=result.getInt("c");
			rank.setAmount(amount);
			
			riskTypeRankList.add(rank);
		}
				
		daoHelper.closeResult(result);
		daoHelper.closePreparedStatement(stmt);
		daoHelper.closeConnection(con);
		
		return riskTypeRankList;
	}

	@Override
	public List findRiskItemByCreatingMost(Date startDate, Date finishDate, RiskType type) throws SQLException {
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		ArrayList riskItemList=new ArrayList();
		
		String riskType=null;
		
		if(type==RiskType.Scope_Risk){
			riskType="Scope_Risk";
		}else if(type==RiskType.Schedule_Risk){
			riskType="Schedule_Risk";
		}else if(type==RiskType.Cost_Risk){
			riskType="Cost_Risk";
		}else if(type==RiskType.Quality_Risk){
			riskType="Quality_Risk";
		}else if(type==RiskType.Technology_Risk){
			riskType="Technology_Risk";
		}else if(type==RiskType.Management_Risk){
			riskType="Management_Risk";
		}else if(type==RiskType.Commercial_Risk){
			riskType="Commercial_Risk";
		}else if(type==RiskType.Legal_Risk){
			riskType="Legal_Risk";
		}else if(type==RiskType.SocialEnvironment_Risk){
			riskType="SocialEnvironment_Risk";
		}
		
		stmt=con.prepareStatement("select * from RiskItem r,Project p,User u "
				+ "where r.createDate between ? and ? and r.riskType=? "
				+ "and r.projectId=p.projectId and r.submitterId=u.userId");
		stmt.setDate(1, startDate);
		stmt.setDate(2, finishDate);		
		stmt.setString(3, riskType);
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
			
			item.setRiskType(item.convertRiskTypefromString(riskType));		
			
			riskItemList.add(item);
		}
		
		daoHelper.closeResult(result);
		daoHelper.closePreparedStatement(stmt);
		daoHelper.closeConnection(con);
		
		return riskItemList;
	}

	@Override
	public List findRiskItemTypeByHappeningMost(Date startDate, Date finishDate) throws SQLException {
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		ArrayList riskTypeRankList=new  ArrayList();
		
		stmt=con.prepareStatement("select r.riskType,count(DISTINCT t.riskItemId) as c "
				+ "from RiskTrackingItem t,RiskItem r "
				+ "where t.createTime between ? and ? and t.riskStatus=? and t.riskItemId=r.riskItemId "
				+ "group by r.riskType order by c desc");
		stmt.setDate(1, startDate);
		stmt.setDate(2, finishDate);
		stmt.setString(3, "HAPPENED");
		result = stmt.executeQuery();
		
		while(result.next()){
			RiskTypeRank rank=new RiskTypeRank();
			String type=result.getString("r.riskType").trim();
			rank.setRiskType(rank.convertRiskTypefromString(type));
			
			int amount=result.getInt("c");
			rank.setAmount(amount);
			
			riskTypeRankList.add(rank);
		}
				
		daoHelper.closeResult(result);
		daoHelper.closePreparedStatement(stmt);
/*	
		stmt=con.prepareStatement("select * from RiskItem r "
				+ "where r.createTime between ? and ? and r.riskStatus=?");		
		stmt.setDate(1, startDate);
		stmt.setDate(2, finishDate);
		stmt.setString(3, "HAPPENED");
		result = stmt.executeQuery();
		ArrayList tmpList=riskTypeRankList;
		while(result.next()){
			for(int i=0;i<tmpList.size();i++){
				
			}
		}
*/		
		
		daoHelper.closeConnection(con);
		
		return riskTypeRankList;
	}

	@Override
	public List findRiskItemByHappeningMost(Date startDate, Date finishDate, RiskType type) throws SQLException {
		
		return this.findRiskItemByCreatingMost(startDate, finishDate, type);
	}

	@Override
	public String getRiskName(int riskItemId){
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		String name="";
		
		try {
			stmt=con.prepareStatement("select * from RiskItem "
					+ "where riskItemId=?");
			stmt.setInt(1, riskItemId);
			result = stmt.executeQuery();	
			
			if(result.next()){
				name=result.getString("riskName").trim();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			daoHelper.closeResult(result);
			daoHelper.closePreparedStatement(stmt);	
			daoHelper.closeConnection(con);
		}
	
		
		return name;
	}

	@Override
	public ArrayList<Integer> getTrackers(int riskItemId) {
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		ArrayList<Integer> trackers=new ArrayList<Integer>();
		
		try {
			stmt=con.prepareStatement("select * from Tracking "
					+ "where riskItemId=?");
			stmt.setInt(1, riskItemId);
			result = stmt.executeQuery();	
			
			while(result.next()){
				
				int tracker=result.getInt("userId");
				trackers.add(tracker);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			daoHelper.closeResult(result);
			daoHelper.closePreparedStatement(stmt);	
			daoHelper.closeConnection(con);
		}		
		
		return trackers;
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

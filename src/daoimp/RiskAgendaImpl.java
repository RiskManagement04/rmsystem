package daoimp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoHelper;
import dao.RiskAgendaDao;
import model.RiskAgenda;
import model.RiskItem;
import model.RiskStatus;
import model.RiskType;
import model.User;

public class RiskAgendaImpl implements RiskAgendaDao{
	
	private static RiskAgendaImpl agendaDao=new RiskAgendaImpl();
	private static DaoHelper daoHelper=DaoHelperImpl.getBaseDaoInstance();
	
	private RiskAgendaImpl(){
		
	}
	
	public static RiskAgendaImpl getInstance(){
		return agendaDao;
	}

	@Override
	public boolean addRiskAgenda(RiskAgenda risk) throws SQLException {
		Connection con=daoHelper.getConnection();
		PreparedStatement statement=null;
		ResultSet result=null;
		boolean isSuccess=true;
		
		//检查计划名称是否存在
		statement = con.prepareStatement("select * from RiskAgenda where agendaName=?");
		statement.setString(1, risk.getAgendaName().trim());
		result = statement.executeQuery();
		
		if(result.next()){
			isSuccess=false;
		}
		
		daoHelper.closeResult(result);
		daoHelper.closePreparedStatement(statement);
		
		if(!isSuccess){
			return isSuccess;
		}
		
		//插入风险计划
		statement=con.prepareStatement("insert into RiskAgenda(createTime,userId,agendaName) values(?,?,?)");
		statement.setDate(1, risk.getCreateTime());
		statement.setInt(2, risk.getUserId());
		statement.setString(3, risk.getAgendaName().trim());
		
		statement.execute();
		
		daoHelper.closeConnection(con);
		daoHelper.closePreparedStatement(statement);
		
		return isSuccess;
	}

	@Override
	public List findRiskAgendaByUser(int userId) throws SQLException {
		Connection con=daoHelper.getConnection();
		PreparedStatement statement=null;
		ResultSet result=null;
		List agendaList=new ArrayList();
		
		//查找用户的计划列表
		statement = con.prepareStatement("select * from RiskAgenda where userId=?");
		statement.setInt(1, userId);
		result = statement.executeQuery();
		while(result.next()){
			RiskAgenda agenda=new RiskAgenda();
			
			agenda.setUserId(userId);
			int agendaId=result.getInt("agendaId");
			agenda.setAgendaId(agendaId);
			String agendaName=result.getString("agendaName").trim();
			agenda.setAgendaName(agendaName);
			Date createTime=result.getDate("createTime");
			agenda.setCreateTime(createTime);
			
			//根据计划编号查找风险条目列表
			PreparedStatement statement2=null;
			ResultSet result2=null;
			ArrayList<RiskItem> risks=new ArrayList<RiskItem>();
			
			statement2=con.prepareStatement("select * from AgendaToRisk a,RiskItem r where a.agendaId=? and "
					+ "a.riskItemId=r.riskItemId");
			statement2.setInt(1, agendaId);
			result2 = statement2.executeQuery();
			
			while(result2.next()){
				RiskItem item=new RiskItem();
				int riskItemId=result2.getInt("r.riskItemId");
				item.setRiskItemId(riskItemId);
				int projectId=result2.getInt("r.projectId");
				item.setProjectId(projectId);
				int submitterId=result2.getInt("r.submitterId");
				item.setSubmitterId(submitterId);
				Date createDate=result2.getDate("r.createDate");
				item.setCreateDate(createDate);
				String riskName=result2.getString("r.riskName").trim();
				item.setRiskName(riskName);
				String riskContent=result2.getString("r.riskContent").trim();
				item.setRiskContent(riskContent);
				String trigger=result2.getString("r.trigger").trim();
				item.setTrigger(trigger);
				int possibility=result2.getInt("r.possibility");
				item.setPossibility(possibility);
				int impact=result2.getInt("r.impact");
				item.setImpact(impact);
				String riskStatus=result2.getString("r.riskStatus").trim();
				item.setRiskStatus(item.convertRiskStatusfromString(riskStatus));
				String measures=result2.getString("r.measures").trim();
				item.setMeasures(measures);
				String riskType=result2.getString("r.riskType").trim();
				item.setRiskType(item.convertRiskTypefromString(riskType));
				
				//根据riskItem的编号查找跟踪者
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
				risks.add(item);
				
			}//里层while结束
			
			daoHelper.closeResult(result2);
			daoHelper.closePreparedStatement(statement2);
			
			agenda.setRisks(risks);
			agendaList.add(agenda);
			
		}//外层while结束
		
		daoHelper.closeResult(result);
		daoHelper.closePreparedStatement(statement);
		daoHelper.closeConnection(con);
		
		return agendaList;
	}

	@Override
	public String addRiskItem(int riskAgendaId, RiskItem riskItem) throws SQLException {
		Connection con=daoHelper.getConnection();
		PreparedStatement statement=null;
		ResultSet result=null;
		
		boolean isSuccess=true;		
		
		//检查风险条目名称是否存在
		statement = con.prepareStatement("select * from RiskItem where riskName=?");
		statement.setString(1, riskItem.getRiskName());
		result = statement.executeQuery();
		
		if(result.next()){
			isSuccess=false;
		}
		
		daoHelper.closeResult(result);
		daoHelper.closePreparedStatement(statement);
		
		if(!isSuccess){
			daoHelper.closeConnection(con);
			return "风险条目名称已存在！";
		}else{
			//插入到风险条目表
			statement=con.prepareStatement("insert into RiskItem(projectId,submitterId,createDate,riskName,riskContent,"
					+ "`trigger`,possibility,impact,riskStatus,measures,riskType) values(?,?,?,?,?,?,?,?,?,?,?)");
			statement.setInt(1, riskItem.getProjectId());
			statement.setInt(2, riskItem.getSubmitterId());
			statement.setDate(3, riskItem.getCreateDate());
			statement.setString(4, riskItem.getRiskName().trim());
			statement.setString(5, riskItem.getRiskContent().trim());
			statement.setString(6, riskItem.getTrigger().trim());
			statement.setInt(7, riskItem.getPossibility());
			statement.setInt(8, riskItem.getImpact());
			
			RiskStatus status=riskItem.getRiskStatus();			
			statement.setString(9, riskItem.convertRiskStatus(status));
			
			statement.setString(10, riskItem.getMeasures().trim());
			
			RiskType type=riskItem.getRiskType();
			statement.setString(11, riskItem.convertRiskTypeToString(type));
			
			statement.execute();
			daoHelper.closePreparedStatement(statement);
			
			//得到风险条目的编号
			statement = con.prepareStatement("select * from RiskItem where riskName=?");
			statement.setString(1, riskItem.getRiskName().trim());
			result = statement.executeQuery();
			int riskItemId=0;
			if(result.next()){
				riskItemId=result.getInt("riskItemId");
				daoHelper.closeResult(result);
				daoHelper.closePreparedStatement(statement);
			}else{
				daoHelper.closeResult(result);
				daoHelper.closePreparedStatement(statement);
				return "新增风险条目失败！";
			}
			
			//插入到风险计划和条目关系表
			statement=con.prepareStatement("insert into AgendaToRisk(agendaId,riskItemId) values(?,?)");
			statement.setInt(1, riskAgendaId);
			statement.setInt(2, riskItemId);
			statement.execute();
			
			daoHelper.closePreparedStatement(statement);
			
			//插入到风险条目和跟踪者关系表
			statement=con.prepareStatement("insert into Tracking values(?,?)");
			
			ArrayList<User> trackers=riskItem.getTrackers();
			for(int i=0;i<trackers.size();i++){
				User u=trackers.get(i);
				statement.setInt(1, riskItemId);
				statement.setInt(2, u.getUserId());
				
				statement.addBatch();
			}
			statement.executeBatch();
			
			daoHelper.closePreparedStatement(statement);
			daoHelper.closeConnection(con);
			return "新增风险条目成功！";
		}
		
	}

	@Override
	public boolean deleteRiskItem(int riskAgendaId, int riskItemId) throws SQLException {
		Connection con=daoHelper.getConnection();
		PreparedStatement statement=null;
		
		statement = con.prepareStatement("delete from AgendaToRisk where agendaId=? and riskItemId=?");
		statement.setInt(1, riskAgendaId);
		statement.setInt(2, riskItemId);
		statement.execute();

		daoHelper.closePreparedStatement(statement);
		daoHelper.closeConnection(con);
		return true;
	}

	@Override
	public boolean addRiskItem(int riskAgendaId, ArrayList<Integer> riskItemList) throws SQLException {
		Connection con=daoHelper.getConnection();
		PreparedStatement statement=null;
		
		statement=con.prepareStatement("insert into AgendaToRisk values(?,?)");
		for(int i=0;i<riskItemList.size();i++){
			statement.setInt(1, riskAgendaId);
			statement.setInt(2, riskItemList.get(i));
			
			statement.addBatch();
		}
		statement.executeBatch();
		
		daoHelper.closePreparedStatement(statement);
		daoHelper.closeConnection(con);		
		
		return true;
	}

	@Override
	public ArrayList<RiskItem> findRiskItemByAgenda(int riskAgendaId) throws SQLException {
		Connection con=daoHelper.getConnection();
		PreparedStatement statement=null;
		ResultSet result=null;
		ArrayList<RiskItem> riskItemList=new ArrayList<RiskItem>();
		
		statement = con.prepareStatement("select * from AgendaToRisk a,RiskItem r,Project p,User u "
				+ "where a.agendaId=? and a.riskItemId=r.riskItemId and r.projectId=p.projectId and r.submitterId=u.userId");
		statement.setInt(1, riskAgendaId);
		result = statement.executeQuery();		
		
		while(result.next()){
			RiskItem item=new RiskItem();
			Date createDate=result.getDate("r.createDate");
			item.setCreateDate(createDate);
			int impact=result.getInt("r.impact");
			item.setImpact(impact);
			String measures=result.getString("r.measures").trim();
			item.setMeasures(measures);
			int possibility=result.getInt("r.possibility");
			item.setPossibility(possibility);
			int projectId=result.getInt("r.projectId");
			item.setProjectId(projectId);
			String projectName=result.getString("p.projectName").trim();
			item.setProjectName(projectName);
			String riskContent=result.getString("r.riskContent").trim();
			item.setRiskContent(riskContent);
			int riskItemId=result.getInt("r.riskItemId");
			item.setRiskItemId(riskItemId);
			String riskName=result.getString("r.riskName").trim();
			item.setRiskName(riskName);
			String status=result.getString("r.riskStatus").trim();
			item.setRiskStatus(item.convertRiskStatusfromString(status));
			String riskType=result.getString("r.riskType").trim();
			item.setRiskType(item.convertRiskTypefromString(riskType));
			int submitterId=result.getInt("r.submitterId");
			item.setSubmitterId(submitterId);
			String submitterName=result.getString("u.trueName").trim();
			item.setSubmitterName(submitterName);
			String trigger=result.getString("r.trigger").trim();
			item.setTrigger(trigger);
			
			riskItemList.add(item);
		}
		
		daoHelper.closeResult(result);
		daoHelper.closePreparedStatement(statement);
		daoHelper.closeConnection(con);
		return riskItemList;
	}

}

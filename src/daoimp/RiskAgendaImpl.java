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
				int riskItemId=result.getInt("r.riskItemId");
				item.setRiskItemId(riskItemId);
				int projectId=result.getInt("r.projectId");
				item.setProjectId(projectId);
				int submitterId=result.getInt("r.submitterId");
				item.setSubmitterId(submitterId);
				Date createDate=result.getDate("r.createDate");
				item.setCreateDate(createDate);
				String riskName=result.getString("r.riskName").trim();
				item.setRiskName(riskName);
				String riskContent=result.getString("r.riskContent").trim();
				item.setRiskContent(riskContent);
				String trigger=result.getString("r.trigger").trim();
				item.setTrigger(trigger);
				int possibility=result.getInt("r.possibility");
				item.setPossibility(possibility);
				int impact=result.getInt("r.impact");
				item.setImpact(impact);
				String riskStatus=result.getString("r.riskStatus").trim();
				item.setRiskStatus(item.convertRiskStatusfromString(riskStatus));
				String measures=result.getString("r.measures").trim();
				item.setMeasures(measures);
				String riskType=result.getString("r.riskType").trim();
				item.setRiskType(item.convertRiskTypefromString(riskType));
				
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
			return "风险条目名称已存在！";
		}else{
			//插入到风险条目表
			statement=con.prepareStatement("insert into RiskItem(projectId,submitterId,createDate,riskName,riskContent,"
					+ "`trigger`,possibility,impact,riskStatus,measures,riskType) values(?,?,?,?,?,?,?,?,?,?,?)");
			statement.setInt(1, riskItem.getProjectId());
			statement.setInt(2, riskItem.getSubmitterId());
			statement.setDate(3, riskItem.getCreateDate());
			statement.setString(4, riskItem.getRiskName());
			statement.setString(5, riskItem.getRiskContent());
			statement.setString(6, riskItem.getTrigger());
			statement.setInt(7, riskItem.getPossibility());
			statement.setInt(8, riskItem.getImpact());
			
			RiskStatus status=riskItem.getRiskStatus();			
			statement.setString(9, riskItem.convertRiskStatus(status));
			
			statement.setString(10, riskItem.getMeasures());
			
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
			return "新增风险条目成功！";
		}
		
	}

	@Override
	public boolean deleteRiskItem(int riskAgendaId, int riskItemId) {
		Connection con=daoHelper.getConnection();
		PreparedStatement statement=null;
		
		
		return false;
	}

}

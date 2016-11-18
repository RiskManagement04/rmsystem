package daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public List findRiskAgendaByUser(int userId) {
		// TODO Auto-generated method stub
		return null;
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

}

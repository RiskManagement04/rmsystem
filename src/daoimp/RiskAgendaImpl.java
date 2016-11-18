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
	public boolean addRiskItem(int riskAgendaId, RiskItem riskItem) throws SQLException {
		Connection con=daoHelper.getConnection();
		PreparedStatement statement=null;
		ResultSet result=null;
		boolean isSuccess=true;
		
		statement=con.prepareStatement("insert into RiskItem(projectId,submitterId,createDate,riskName,riskContent,"
				+ "`trigger`,possibility,impact,riskStatus) values(?,?,?)");
		
		return false;
	}

}

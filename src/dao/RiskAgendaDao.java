package dao;

import java.sql.SQLException;
import java.util.List;

import model.RiskAgenda;
import model.RiskItem;

public interface RiskAgendaDao {
	
	public boolean addRiskAgenda(RiskAgenda risk) throws SQLException;//新建风险管理计划
	
	public List findRiskAgendaByUser(int userId);//获取用户的风险计划列表
	
	public boolean addRiskItem(int riskAgendaId,RiskItem riskItem) throws SQLException;//在计划中添加风险条目

}

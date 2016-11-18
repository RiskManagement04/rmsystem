package dao;

import java.util.List;

import model.RiskAgenda;

public interface RiskAgendaDao {
	
	public boolean addRiskAgenda(RiskAgenda risk);//新建风险管理计划
	
	public List findRiskAgendaByUser(int userId);

}

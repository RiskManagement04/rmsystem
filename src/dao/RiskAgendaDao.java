package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.RiskAgenda;
import model.RiskItem;

public interface RiskAgendaDao {
	
	public boolean addRiskAgenda(RiskAgenda risk) throws SQLException;//新建风险管理计划
	
	public List findRiskAgendaByUser(int userId) throws SQLException;//获取用户的风险计划列表
	
	public String addRiskItem(int riskAgendaId,RiskItem riskItem) throws SQLException;//在计划中添加风险条目
	
	public String addRiskItem(int riskAgendaId,ArrayList<Integer> riskItemList);//在计划中添加多条风险条目
	
	public boolean deleteRiskItem(int riskAgendaId,int riskItemId) throws SQLException;//删除风险条目

}

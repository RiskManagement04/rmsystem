package dao;

import java.util.List;

import model.RiskAgenda;

public interface RiskAgendaDao {
	
	public boolean addRiskAgenda(RiskAgenda risk);//�½����չ���ƻ�
	
	public List findRiskAgendaByUser(int userId);

}

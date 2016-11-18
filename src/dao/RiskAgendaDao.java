package dao;

import java.sql.SQLException;
import java.util.List;

import model.RiskAgenda;
import model.RiskItem;

public interface RiskAgendaDao {
	
	public boolean addRiskAgenda(RiskAgenda risk) throws SQLException;//�½����չ���ƻ�
	
	public List findRiskAgendaByUser(int userId) throws SQLException;//��ȡ�û��ķ��ռƻ��б�
	
	public String addRiskItem(int riskAgendaId,RiskItem riskItem) throws SQLException;//�ڼƻ�����ӷ�����Ŀ

}

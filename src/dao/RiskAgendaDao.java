package dao;

import java.sql.SQLException;
import java.util.List;

import model.RiskAgenda;
import model.RiskItem;

public interface RiskAgendaDao {
	
	public boolean addRiskAgenda(RiskAgenda risk) throws SQLException;//�½����չ���ƻ�
	
	public List findRiskAgendaByUser(int userId);//��ȡ�û��ķ��ռƻ��б�
	
	public boolean addRiskItem(int riskAgendaId,RiskItem riskItem) throws SQLException;//�ڼƻ�����ӷ�����Ŀ

}

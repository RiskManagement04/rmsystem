package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.RiskAgenda;
import model.RiskItem;

public interface RiskAgendaDao {
	
	public boolean addRiskAgenda(RiskAgenda risk) throws SQLException;//�½����չ���ƻ�
	
	public List findRiskAgendaByUser(int userId) throws SQLException;//��ȡ�û��ķ��ռƻ��б�
	
	public String addRiskItem(int riskAgendaId,RiskItem riskItem) throws SQLException;//�ڼƻ�����ӷ�����Ŀ
	
	public boolean addRiskItem(int riskAgendaId,ArrayList<Integer> riskItemList) throws SQLException;//�ڼƻ�����Ӷ���������Ŀ
	
	public boolean deleteRiskItem(int riskAgendaId,int riskItemId) throws SQLException;//ɾ��������Ŀ
	
	public ArrayList<RiskItem> findRiskItemByAgenda(int riskAgendaId) throws SQLException;//���ݼƻ���Ų������еķ�����Ŀ

}

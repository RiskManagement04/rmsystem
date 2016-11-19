package dao;

import java.util.List;

import model.RiskTrackingItem;

public interface RiskTrackingItemDao {

	public List findRiskTrackingItem(int riskItemId);//���ҷ�����Ŀ�еķ��ո����б�
	
	public boolean addRiskTrackingItem(RiskTrackingItem item);//���ӷ��ո�����Ŀ
	
	public boolean deleteRiskTrackingItem(int riskTrackingItemId);//ɾ�����ո�����Ŀ
}

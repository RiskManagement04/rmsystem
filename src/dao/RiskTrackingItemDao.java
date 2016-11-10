package dao;

import java.util.List;

import model.RiskTrackingItem;

public interface RiskTrackingItemDao {
//查找风险跟踪条目   增加
	public List<RiskTrackingItem> findRiskTrackingItem(int riskItemId);
	
	public  boolean addRiskTrackingItem(RiskTrackingItem item);
}

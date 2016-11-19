package dao;

import java.util.List;

import model.RiskTrackingItem;

public interface RiskTrackingItemDao {

	public List findRiskTrackingItem(int riskItemId);//查找风险条目中的风险跟踪列表
	
	public boolean addRiskTrackingItem(RiskTrackingItem item);//增加风险跟踪条目
	
	public boolean deleteRiskTrackingItem(int riskTrackingItemId);//删除风险跟踪条目
}

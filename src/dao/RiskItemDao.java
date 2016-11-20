package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.RiskItem;
import model.RiskType;

public interface RiskItemDao {

	public List findAllRiskItem();
	//public List findAllRiskItem();
	//public boolean addRiskItem(RiskItem riskItem);
	//public boolean deleteRiskItem(int riskItemId);
	
	public List findRiskItemTypeByCreatingMost(Date startDate,Date finishDate) throws SQLException;//特定时间段被识别最多的风险类别
	
	public List findRiskItemByCreatingMost(Date startDate,Date finishDate,RiskType type) throws SQLException;//返回被识别最多的风险列表
	
	public List findRiskItemTypeByHappeningMost(Date startDate,Date finishDate) throws SQLException;//特定时间演变成问题最多的风险类别
	
	public List findRiskItemByHappeningMost(Date startDate,Date finishDate,RiskType type) throws SQLException;//返回演变成问题最多的风险列表
	
	public String getRiskName(int riskItemId);
	
	public ArrayList<Integer> getTrackers(int riskItemId);//根据风险条目的编号获得相应的追踪者
}

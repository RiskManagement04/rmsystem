package dao;

import java.sql.Date;
import java.util.List;

import model.RiskItem;

public interface RiskItemDao {

	public List findAllRiskItem();
	//public List findAllRiskItem();
	//public boolean addRiskItem(RiskItem riskItem);
	//public boolean deleteRiskItem(int riskItemId);
	
	public List findRiskItemByCreatingMost(Date startDate,Date finishDate);//特定时间段被识别最多的风险
	
	public List findRiskItemByHappeningMost(Date startDate,Date finishDate);//特定时间演变成问题最多的风险
}

package dao;

import java.util.List;

import model.RiskItem;

public interface RiskItemDao {

	public List findAllRiskItem(int userId);
	//public List findAllRiskItem();
	//public boolean addRiskItem(RiskItem riskItem);
	public boolean deleteRiskItem(int riskItemId);
}

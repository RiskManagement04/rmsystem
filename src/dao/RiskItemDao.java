package dao;

import java.sql.Date;
import java.util.List;

import model.RiskItem;

public interface RiskItemDao {

	public List findAllRiskItem();
	//public List findAllRiskItem();
	//public boolean addRiskItem(RiskItem riskItem);
	//public boolean deleteRiskItem(int riskItemId);
	
	public List findRiskItemByCreatingMost(Date startDate,Date finishDate);//�ض�ʱ��α�ʶ�����ķ���
	
	public List findRiskItemByHappeningMost(Date startDate,Date finishDate);//�ض�ʱ���ݱ���������ķ���
}

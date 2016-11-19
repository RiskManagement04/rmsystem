package dao;

import java.sql.Date;
import java.util.List;

import model.RiskItem;
import model.RiskType;

public interface RiskItemDao {

	public List findAllRiskItem();
	//public List findAllRiskItem();
	//public boolean addRiskItem(RiskItem riskItem);
	//public boolean deleteRiskItem(int riskItemId);
	
	public List findRiskItemTypeByCreatingMost(Date startDate,Date finishDate);//�ض�ʱ��α�ʶ�����ķ������
	
	public List findRiskItemByCreatingMost(Date startDate,Date finishDate,RiskType type);//���ر�ʶ�����ķ����б�
	
	public List findRiskItemTypeByHappeningMost(Date startDate,Date finishDate);//�ض�ʱ���ݱ���������ķ������
	
	public List findRiskItemByHappeningMost(Date startDate,Date finishDate,RiskType type);//�����ݱ���������ķ����б�
}

package factory;

import dao.RiskItemDao;
import dao.RiskTrackingItemDao;
import dao.UserDao;
import daoImp.RiskItemDaoImpl;
import daoImp.RiskTrackingItemDaoImpl;
import daoImp.UserDaoImpl;

public class DaoFactory {	
	public static UserDao getUserDao(){
		return UserDaoImpl.getInstance();
	}
	
	public static RiskItemDao getRiskItemDao(){
		return RiskItemDaoImpl.getInstance();
	}
	
	public static RiskTrackingItemDao getRiskTrackingItemDao(){
		return RiskTrackingItemDaoImpl.getInstance();
	}
}

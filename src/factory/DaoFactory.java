package factory;

import dao.RiskItemDao;
import dao.UserDao;
import daoImp.RiskItemDaoImpl;
import daoImp.UserDaoImpl;

public class DaoFactory {	
	public static UserDao getUserDao(){
		return UserDaoImpl.getInstance();
	}
	
	public static RiskItemDao getRiskItemDao(){
		return RiskItemDaoImpl.getInstance();
	}
}

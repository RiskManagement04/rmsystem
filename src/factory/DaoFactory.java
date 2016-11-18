package factory;

import dao.ProjectDao;
import dao.RiskAgendaDao;
import dao.RiskItemDao;
import dao.RiskTrackingItemDao;
import dao.UserDao;
import daoimp.ProjectDaoImpl;
import daoimp.RiskAgendaImpl;
import daoimp.RiskItemDaoImpl;
import daoimp.RiskTrackingItemDaoImpl;
import daoimp.UserDaoImpl;

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
	
	public static ProjectDao getProjectDao(){
		return ProjectDaoImpl.getInstance();
	}
	
	public static RiskAgendaDao getRiskAgendaDao(){
		return RiskAgendaImpl.getInstance();
	}
}




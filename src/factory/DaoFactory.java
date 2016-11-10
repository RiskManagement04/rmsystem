package factory;

import dao.UserDao;
import daoImp.UserDaoImpl;

public class DaoFactory {	
	public static UserDao getUserDao(){
		return UserDaoImpl.getInstance();
	}
}

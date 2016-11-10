package daoImp;

import dao.DaoHelper;
import dao.RiskItemDao;

public class RiskItemDaoImpl implements RiskItemDao{
	
	private static RiskItemDaoImpl riskItemDao=new RiskItemDaoImpl();
	private static DaoHelper daoHelper=DaoHelperImpl.getBaseDaoInstance();
	
	private RiskItemDaoImpl(){
		
	}
	
	public static RiskItemDaoImpl getInstance(){
		return riskItemDao;
	}

}

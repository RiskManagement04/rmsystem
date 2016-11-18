package daoimp;

import java.util.List;

import dao.DaoHelper;
import dao.RiskAgendaDao;
import model.RiskAgenda;
import model.RiskItem;

public class RiskAgendaImpl implements RiskAgendaDao{
	
	private static RiskAgendaImpl agendaDao=new RiskAgendaImpl();
	private static DaoHelper daoHelper=DaoHelperImpl.getBaseDaoInstance();
	
	private RiskAgendaImpl(){
		
	}
	
	public static RiskAgendaImpl getInstance(){
		return agendaDao;
	}

	@Override
	public boolean addRiskAgenda(RiskAgenda risk) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List findRiskAgendaByUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addRiskItem(int riskAgendaId, RiskItem riskItem) {
		// TODO Auto-generated method stub
		return false;
	}

}

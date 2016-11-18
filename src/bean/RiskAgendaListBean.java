package bean;

import java.io.Serializable;
import java.util.List;

import model.RiskAgenda;
import model.RiskItem;

public class RiskAgendaListBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List riskAgendaList=null;

	
	public List getRiskAgendaList() {
		return riskAgendaList;
	}

	
	public void setRiskAgendaList(List riskAgendaList) {
		this.riskAgendaList = riskAgendaList;
	}
	
	
	public void setRiskAgendaList(RiskAgenda riskAgenda, int index) {
		riskAgendaList.set(index, riskAgenda);
	}
	
	public RiskAgenda getRiskAgenda(int index) {
		return (RiskAgenda) riskAgendaList.get(index);
	}

}

package bean;

import java.io.Serializable;
import java.util.List;

import model.RiskItem;

public class RiskItemListBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List riskItemList=null;

	
	public List getRiskItemList() {
		return riskItemList;
	}

	
	public void setRiskItemList(List riskItemList) {
		this.riskItemList = riskItemList;
	}
	
	
	public void setRiskItemList(RiskItem riskItem, int index) {
		riskItemList.set(index, riskItem);
	}
	
	public RiskItem getRiskItem(int index) {
		return (RiskItem) riskItemList.get(index);
	}
	

}

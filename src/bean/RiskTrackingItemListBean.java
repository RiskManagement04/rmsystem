package bean;

import java.io.Serializable;
import java.util.List;

import model.RiskItem;
import model.RiskTrackingItem;

public class RiskTrackingItemListBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List riskTrackingItemList;

	
	public List getRiskTrackingItemList() {
		return riskTrackingItemList;
	}

	
	public void setRiskTrackingItemList(List riskTrackingItemList) {
		this.riskTrackingItemList = riskTrackingItemList;
	}
	
	
	public void setriskTrackingItemList(RiskTrackingItem riskTrackingItem, int index) {
		riskTrackingItemList.set(index, riskTrackingItem);
	}
	
	public RiskTrackingItem getriskTrackingItem(int index) {
		return (RiskTrackingItem) riskTrackingItemList.get(index);
	}

}

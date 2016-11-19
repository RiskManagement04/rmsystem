package model;

import java.io.Serializable;

public class RiskTypeRank implements Serializable{
	
	private int amount;
	private RiskType riskType;
	
	public RiskTypeRank(int amount, RiskType riskType) {
		super();
		this.amount = amount;
		this.riskType = riskType;
	}
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public RiskType getRiskType() {
		return riskType;
	}
	public void setRiskType(RiskType riskType) {
		this.riskType = riskType;
	}

}

package model;

import java.io.Serializable;

public class RiskTypeRank implements Serializable{
	
	private int amount;
	private RiskType riskType;
	
	public RiskTypeRank(){
		
	}
	
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
	
	public RiskType convertRiskTypefromString(String riskType){
		RiskType type=null;
		riskType=riskType.trim();
		
		if(riskType.equals("Scope_Risk")){
			type=RiskType.Scope_Risk;
		}else if(riskType.equals("Schedule_Risk")){
			type=RiskType.Schedule_Risk;
		}else if(riskType.equals("Cost_Risk")){
			type=RiskType.Cost_Risk;
		}else if(riskType.equals("Quality_Risk")){
			type=RiskType.Quality_Risk;
		}else if(riskType.equals("Technology_Risk")){
			type=RiskType.Technology_Risk;
		}else if(riskType.equals("Management_Risk")){
			type=RiskType.Management_Risk;
		}else if(riskType.equals("Commercial_Risk")){
			type=RiskType.Commercial_Risk;
		}else if(riskType.equals("Legal_Risk")){
			type=RiskType.Legal_Risk;
		}else if(riskType.equals("SocialEnvironment_Risk")){
			type=RiskType.SocialEnvironment_Risk;
		}
		return type;
	}
	
	public String convertRiskTypeToString(RiskType riskType){
		String type=null;
		
		if(riskType==RiskType.Scope_Risk){
			type="Scope_Risk";
		}else if(riskType==RiskType.Schedule_Risk){
			type="Schedule_Risk";
		}else if(riskType==RiskType.Cost_Risk){
			type="Cost_Risk";
		}else if(riskType==RiskType.Quality_Risk){
			type="Quality_Risk";
		}else if(riskType==RiskType.Technology_Risk){
			type="Technology_Risk";
		}else if(riskType==RiskType.Management_Risk){
			type="Management_Risk";
		}else if(riskType==RiskType.Commercial_Risk){
			type="Commercial_Risk";
		}else if(riskType==RiskType.Legal_Risk){
			type="Legal_Risk";
		}else if(riskType==RiskType.SocialEnvironment_Risk){
			type="SocialEnvironment_Risk";
		}
		
		
		return type;
	}

}

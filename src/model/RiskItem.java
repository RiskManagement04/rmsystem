package model;

import java.io.Serializable;
import java.sql.Date;

public class RiskItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int riskItemId;
	private int projectId;
	private int submitterId;
	private String submitterName;
	private Date createDate;
	private String riskName;
	private String riskContent;
	private String trigger;
	private int possibility;//1,2,3
	private int impact;//1,2,3
	private RiskStatus riskStatus;
	
	public RiskItem(){
		
	}
	
	public RiskItem(int riskItemId,int projectId,int submitterId,Date createDate,String riskName,String riskContent,String trigger,
			int possibility,int impact,String riskStatus){
		this.riskItemId=riskItemId;
	    this.projectId=projectId;
	    this.submitterId=submitterId;
	    this.createDate=createDate;
	    this.riskName=riskName;
	    this.riskContent=riskContent;
	    this.trigger=trigger;
	    this.possibility=possibility;
	    this.impact=impact;
	    
	    if(riskStatus.equals("PREDICTED")){
	    	this.riskStatus=RiskStatus.PREDICTED;
	    }else if(riskStatus.equals("HAPPENED")){
	    	this.riskStatus=RiskStatus.HAPPENED;
	    }else{
	    	this.riskStatus=RiskStatus.SOLVED;
	    }
	    
	}
	
	public RiskItem(int riskItemId,int projectId,int submitterId,Date createDate,String riskName,String riskContent,String trigger,
			int possibility,int impact,RiskStatus riskStatus){
		this.riskItemId=riskItemId;
	    this.projectId=projectId;
	    this.submitterId=submitterId;
	    this.createDate=createDate;
	    this.riskName=riskName;
	    this.riskContent=riskContent;
	    this.trigger=trigger;
	    this.possibility=possibility;
	    this.impact=impact;
	    this.riskStatus=riskStatus;
	    
	}

	public int getRiskItemId() {
		return riskItemId;
	}

	public void setRiskItemId(int riskItemId) {
		this.riskItemId = riskItemId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getSubmitterId() {
		return submitterId;
	}

	public void setSubmitterId(int submitterId) {
		this.submitterId = submitterId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public String getRiskContent() {
		return riskContent;
	}

	public void setRiskContent(String riskContent) {
		this.riskContent = riskContent;
	}

	public String getTrigger() {
		return trigger;
	}

	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}

	public int getPossibility() {
		return possibility;
	}

	public void setPossibility(int possibility) {
		this.possibility = possibility;
	}

	public int getImpact() {
		return impact;
	}

	public void setImpact(int impact) {
		this.impact = impact;
	}

	public RiskStatus getRiskStatus() {
		return riskStatus;
	}

	public void setRiskStatus(RiskStatus riskStatus) {
		this.riskStatus = riskStatus;
	}

	public String getSubmitterName() {
		return submitterName;
	}

	public void setSubmitterName(String submitterName) {
		this.submitterName = submitterName;
	}

	public String getRiskStatusString() {
		// TODO Auto-generated method stub
		return convertRiskStatus(riskStatus);
	}
	private String convertRiskStatus(RiskStatus status){
		if(status.equals(RiskStatus.PREDICTED)){
			return "PREDICTED";
		}else if(status.equals(RiskStatus.HAPPENED)){
			return "HAPPENED";
		}else{
			return "SOLVED";
		}
	}

}

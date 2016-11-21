package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class RiskItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int riskItemId;
	private int projectId;
	private String projectName;
	private int submitterId;
	private String submitterName;
	private Date createDate;
	private String riskName;
	private String riskContent;
	private String trigger;
	private int possibility;//1,2,3
	private int impact;//1,2,3
	private RiskStatus riskStatus;
	private String measures;
	private RiskType riskType;
	private ArrayList<User> trackers;
	
	public RiskItem(){
		
	}
	
	public RiskItem(int riskItemId,int projectId,int submitterId,Date createDate,String riskName,String riskContent,String trigger,
			int possibility,int impact,String riskStatus,String projectName,String measures,String riskType,ArrayList<User> trackers){
		this.riskItemId=riskItemId;
	    this.projectId=projectId;
	    this.submitterId=submitterId;
	    this.createDate=createDate;
	    this.riskName=riskName;
	    this.riskContent=riskContent;
	    this.trigger=trigger;
	    this.possibility=possibility;
	    this.impact=impact;
	    this.projectName=projectName;
	    
	    riskStatus=riskStatus.trim();
	    if(riskStatus.equals("PREDICTED")){
	    	this.riskStatus=RiskStatus.PREDICTED;
	    }else if(riskStatus.equals("HAPPENED")){
	    	this.riskStatus=RiskStatus.HAPPENED;
	    }else{
	    	this.riskStatus=RiskStatus.SOLVED;
	    }
	    
	    this.measures=measures;
	    this.riskType=this.convertRiskTypefromString(riskType.trim());
	    
	    this.setTrackers(trackers);
	    
	}
	
	public RiskItem(int riskItemId,int projectId,int submitterId,Date createDate,String riskName,String riskContent,String trigger,
			int possibility,int impact,RiskStatus riskStatus,String projectName,String measures,RiskType riskType){
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
	    this.measures=measures;
	    this.riskType=riskType;
	    
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
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getRiskStatusString() {
		// TODO Auto-generated method stub
		return convertRiskStatus(riskStatus);
	}
	public String convertRiskStatus(RiskStatus status){
		if(status.equals(RiskStatus.PREDICTED)){
			return "PREDICTED";
		}else if(status.equals(RiskStatus.HAPPENED)){
			return "HAPPENED";
		}else{
			return "SOLVED";
		}
	}

	public String getMeasures() {
		return measures;
	}

	public void setMeasures(String measures) {
		this.measures = measures;
	}

	public RiskType getRiskType() {
		return riskType;
	}

	public void setRiskType(RiskType riskType) {
		this.riskType = riskType;
	}
	
	public RiskStatus convertRiskStatusfromString(String status){
		
		status=status.trim();
		if(status.equals("PREDICTED")){
			return RiskStatus.PREDICTED;
		}else if(status.equals("HAPPENED")){
			return RiskStatus.HAPPENED;
		}else{
			return RiskStatus.SOLVED;
		}
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
	
	public String convertRiskTypeToShow(RiskType riskType){
		String type=null;
		
		if(riskType==RiskType.Scope_Risk){
			type="范围风险";
		}else if(riskType==RiskType.Schedule_Risk){
			type="进度风险";
		}else if(riskType==RiskType.Cost_Risk){
			type="成本风险";
		}else if(riskType==RiskType.Quality_Risk){
			type="质量风险";
		}else if(riskType==RiskType.Technology_Risk){
			type="技术风险";
		}else if(riskType==RiskType.Management_Risk){
			type="管理风险";
		}else if(riskType==RiskType.Commercial_Risk){
			type="商业风险";
		}else if(riskType==RiskType.Legal_Risk){
			type="法律风险";
		}else if(riskType==RiskType.SocialEnvironment_Risk){
			type="社会环境风险";
		}
		
		
		return type;		
	}

	public ArrayList<User> getTrackers() {
		return trackers;
	}

	public void setTrackers(ArrayList<User> trackers) {
		this.trackers = trackers;
	}

}

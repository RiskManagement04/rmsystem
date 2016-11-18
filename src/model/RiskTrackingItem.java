package model;

import java.io.Serializable;
import java.sql.Date;


public class RiskTrackingItem implements Serializable{
	private int riskTrackingItemId;
	private int riskItemId;
	private String riskItemName;
	private int trackerId;
	private String trackerName;
	private Date createTime;
	private RiskStatus riskStatus;
	private String riskContent;
	private String measures;
	private int possibility;//1,2,3
	private int impact;//1,2,3
	
	public RiskTrackingItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RiskTrackingItem(int riskTrackingItemId, int riskItemId,
			int trackerId, Date createTime, String riskStatus,
			String riskContent, String measures,String riskItemName,int possibility,int impact) {
		super();
		this.riskTrackingItemId = riskTrackingItemId;
		this.riskItemId = riskItemId;
		this.trackerId = trackerId;
		this.createTime = createTime;
		this.riskStatus = convertStatus(riskStatus);
		this.riskContent = riskContent;
		this.measures = measures;
		this.riskItemName=riskItemName;
		this.possibility=possibility;
		this.impact=impact;
	}
	
	public RiskTrackingItem(int riskTrackingItemId, int riskItemId,
			int trackerId, Date createTime, RiskStatus riskStatus,
			String riskContent, String measures,String riskItemName,int possibility,int impact) {
		super();
		this.riskTrackingItemId = riskTrackingItemId;
		this.riskItemId = riskItemId;
		this.trackerId = trackerId;
		this.createTime = createTime;
		this.riskStatus = riskStatus;
		this.riskContent = riskContent;
		this.measures = measures;
		this.riskItemName=riskItemName;
		this.possibility=possibility;
		this.impact=impact;
	}
	
	private  RiskStatus convertStatus(String strStatus){
		if(strStatus.equals("PREDICTED")){
			return RiskStatus.PREDICTED;
					
		}else if(strStatus.equals("HAPPENED")){
			return RiskStatus.HAPPENED;
			
		}else if(strStatus.equals("SOLVED")){
			return RiskStatus.SOLVED;
		}else{
			return null;
		}
			
	}

	private  String convertStatus(RiskStatus strStatus){
		if(strStatus.equals(RiskStatus.PREDICTED)){
			return "PREDICTED";
					
		}else if(strStatus.equals(RiskStatus.HAPPENED)){
			return "HAPPENED";
			
		}else if(strStatus.equals(RiskStatus.SOLVED)){
			return "SOLVED";
		}else{
			return null;
		}
			
	}
	public int getRiskTrackingItemId() {
		return riskTrackingItemId;
	}

	public void setRiskTrackingItemId(int riskTrackingItemId) {
		this.riskTrackingItemId = riskTrackingItemId;
	}

	public int getRiskItemId() {
		return riskItemId;
	}

	public void setRiskItemId(int riskItemId) {
		this.riskItemId = riskItemId;
	}

	public int getTrackerId() {
		return trackerId;
	}

	public void setTrackerId(int trackerId) {
		this.trackerId = trackerId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public RiskStatus getRiskStatus() {
		return riskStatus;
	}
	public String getRiskStatusString(){
		return convertStatus(riskStatus);
	}
	public void setRiskStatus(RiskStatus riskStatus) {
		this.riskStatus = riskStatus;
	}
	public void setRiskStatus(String riskStatus){
		this.riskStatus=convertStatus(riskStatus);
	}
	public String getRiskContent() {
		return riskContent;
	}
	
	public void setRiskContent(String riskContent) {
		this.riskContent = riskContent;
	}

	public String getMeasures() {
		return measures;
	}

	public void setMeasures(String measures) {
		this.measures = measures;
	}

	public String getTrackerName() {
		return trackerName;
	}

	public void setTrackerName(String trackerName) {
		this.trackerName = trackerName;
	}

	public String getRiskItemName() {
		return riskItemName;
	}

	public void setRiskItemName(String riskItemName) {
		this.riskItemName = riskItemName;
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
	
	
}

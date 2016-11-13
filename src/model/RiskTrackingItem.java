package model;

import java.io.Serializable;
import java.sql.Date;


public class RiskTrackingItem implements Serializable{
	private int riskTrackingItemId;
	private int riskItemId;
	private int trackerId;
	private String trackerName;
	private Date createTime;
	private RiskStatus riskStatus;
	private String riskContent;
	private String measures;
	
	
	public RiskTrackingItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RiskTrackingItem(int riskTrackingItemId, int riskItemId,
			int trackerId, Date createTime, String riskStatus,
			String riskContent, String measures) {
		super();
		this.riskTrackingItemId = riskTrackingItemId;
		this.riskItemId = riskItemId;
		this.trackerId = trackerId;
		this.createTime = createTime;
		this.riskStatus = convertStatus(riskStatus);
		this.riskContent = riskContent;
		this.measures = measures;
	}
	
	private  RiskStatus convertStatus(String strStatus){
		if(strStatus.equals("未发生")){
			return RiskStatus.PREDICTED;
					
		}else if(strStatus.equals("已发生")){
			return RiskStatus.HAPPENED;
			
		}else if(strStatus.equals("已解决")){
			return RiskStatus.SOLVED;
		}else{
			return null;
		}
			
	}

	private  String convertStatus(RiskStatus strStatus){
		if(strStatus.equals(RiskStatus.PREDICTED)){
			return "未发生";
					
		}else if(strStatus.equals(RiskStatus.HAPPENED)){
			return "已发生";
			
		}else if(strStatus.equals(RiskStatus.SOLVED)){
			return "已解决";
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
	
	
}

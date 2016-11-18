package model;

import java.io.Serializable;
import java.sql.Date;

public class RiskAgenda implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int agendaId;
	private String agendaName;
	private Date createTime;
	private int userId;
	private String userName;
	
	public RiskAgenda(String agendaName,Date createTime,int userId,String userName){
		this.agendaName=agendaName;
		this.createTime=createTime;
		this.userId=userId;
		this.userName=userName;
	}
	
	public int getAgendaId() {
		return agendaId;
	}
	public void setAgendaId(int agendaId) {
		this.agendaId = agendaId;
	}
	public String getAgendaName() {
		return agendaName;
	}
	public void setAgendaName(String agendaName) {
		this.agendaName = agendaName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}

package model;

import java.io.Serializable;

public class Project implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int projectId;
	private String projectName;
	private int managerId;
	private String projectContent;
	
	public Project(){
		
	}
	
	public Project(int projectId,String projectName,int managerId,String projectContent){
		this.projectId=projectId;
		this.projectName=projectName;
		this.managerId=managerId;
		this.projectContent=projectContent;
	}
	
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getProjectContent() {
		return projectContent;
	}

	public void setProjectContent(String projectContent) {
		this.projectContent = projectContent;
	}
	
	

}

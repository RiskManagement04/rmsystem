package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Project implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int projectId;
	private String projectName;
	private int managerId;
	private String projectContent;
	private ArrayList<User> users;//项目组成员编号
	
	public Project(){
		
	}
	
	public Project(int projectId,String projectName,int managerId,String projectContent,ArrayList<User> users){
		this.projectId=projectId;
		this.projectName=projectName;
		this.managerId=managerId;
		this.projectContent=projectContent;
		this.setUsers(users);
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

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	

}

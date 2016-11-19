package dao;

import java.util.List;

import model.Project;

public interface ProjectDao {
	
	public List<Project> getProjectByUser(int userId);
	
	public boolean addProject(Project project);
	
	public List<Project> getProjects();

}

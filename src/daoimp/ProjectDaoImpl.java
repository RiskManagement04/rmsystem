package daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoHelper;
import dao.ProjectDao;
import model.Project;

public class ProjectDaoImpl implements ProjectDao{
	
	private static ProjectDaoImpl projectDao=new ProjectDaoImpl();
	private static DaoHelper daoHelper=DaoHelperImpl.getBaseDaoInstance();
	
	private ProjectDaoImpl(){
		
	}
	
	public static ProjectDaoImpl getInstance(){
		return projectDao;
	}

	@Override
	public List<Project> getProjectByUser(int userId) {
		// TODO Auto-generated method stub
		List<Project> list=new ArrayList<Project>();
		/*
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		
		try {
			stmt = con.prepareStatement("select * from Developing d,Project p where d.userId=? and d.projectId=p.projectId");
			stmt.setInt(1,userId);
			result = stmt.executeQuery();
			
			while(result.next()){
				int projectId=result.getInt("p.projectId");
				String projectName=result.getString("p.projectName").trim();
				int managerId=result.getInt("p.projectId");
				
				Project p=new Project(projectId,projectName,managerId);
				list.add(p);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResult(result);
		}
		*/
		return list;
	}

	@Override
	public boolean addProject(Project project) {
		Connection con=daoHelper.getConnection();
		PreparedStatement statement=null;
		boolean isSuccess=true;
		
		try {
			statement=con.prepareStatement("insert into Project(projectName,projectContent,managerId) values(?,?,?)");
			statement.setString(1, project.getProjectName());
			statement.setString(2, project.getProjectContent());
			statement.setInt(3, project.getManagerId());
			
			statement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isSuccess=false;
		}finally{
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(statement);
		}
		
		
		return isSuccess;
	}

}

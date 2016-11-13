package daoImp;

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
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		List<Project> list=new ArrayList<Project>();
		
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
		
		return list;
	}

}

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
import model.User;

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

	//新建一个项目
	@Override
	public boolean addProject(Project project) {
		Connection con=daoHelper.getConnection();
		PreparedStatement statement=null;
		boolean isSuccess=true;
		
		//插入项目信息
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
			daoHelper.closePreparedStatement(statement);
		}
				
		if(isSuccess==false){
			return isSuccess;
		}
		
		//得到项目编号
		ResultSet result=null;
		int projectId=0;
		try {
			statement = con.prepareStatement("select * from Project p where p.projectName=?");
			statement.setString(1,project.getProjectName());
			result = statement.executeQuery();
			
			if(result.next()){
				projectId=result.getInt("p.projectId");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isSuccess=false;
		} finally{
			daoHelper.closePreparedStatement(statement);
			daoHelper.closeResult(result);
		}
		
		if(isSuccess==false){
			return isSuccess;
		}

		//插入项目与用户关系
		ArrayList<User> users=project.getUsers();
		try {
			statement=con.prepareStatement("insert into Developing values(?,?)");
			
			for(int i=0;i<users.size();i++){
				User user=users.get(i);
				statement.setInt(1, projectId);
				statement.setInt(2, user.getUserId());
				
				statement.addBatch();
			}
			
			statement.executeBatch();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isSuccess=false;
		}finally{
			daoHelper.closePreparedStatement(statement);
			daoHelper.closeConnection(con);
		}
		
		return isSuccess;
	}

	@Override
	public List<Project> getProjects() {
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		List<Project> list=new ArrayList<Project>();
		
		try {
			stmt = con.prepareStatement("select * from Project");
			result = stmt.executeQuery();
			
			while(result.next()){
				Project p=new Project();
				int projectId=result.getInt("projectId");
				p.setProjectId(projectId);
				String projectName=result.getString("projectName");
				p.setProjectName(projectName);
				String projectContent=result.getString("projectContent");
				p.setProjectContent(projectContent);
				int managerId=result.getInt("managerId");
				p.setManagerId(managerId);
				
				list.add(p);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
						
			daoHelper.closeResult(result);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeConnection(con);
		}
		
		return list;
	}

	@Override
	public ArrayList<Project> getProjectByManager(int userId) {
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		ArrayList<Project> list=new ArrayList<Project>();
		
		try {
			stmt = con.prepareStatement("select * from Project where managerId=?");
			stmt.setInt(1, userId);
			result = stmt.executeQuery();
			
			while(result.next()){
				Project p=new Project();
				int projectId=result.getInt("projectId");
				p.setProjectId(projectId);
				String projectName=result.getString("projectName");
				p.setProjectName(projectName);
				String projectContent=result.getString("projectContent");
				p.setProjectContent(projectContent);
				p.setManagerId(userId);
				
				//获取项目组内的成员
				PreparedStatement stmt2=con.prepareStatement("select * from Developing d,User u where d.projectId=? and d.userId=u.userId");
				ResultSet result2=null;
				stmt2.setInt(1, projectId);
				result2=stmt2.executeQuery();
				ArrayList<User> users=new ArrayList<User>();
				while(result2.next()){
					User u=new User();
					int uId=result2.getInt("u.userId");
					u.setUserId(uId);
					String nickName=result2.getString("u.nickName").trim();
					u.setNickName(nickName);
					String trueName=result2.getString("u.trueName").trim();
					u.setTrueName(trueName);
					
					users.add(u);
				}
				
				p.setUsers(users);
				
				list.add(p);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
						
			daoHelper.closeResult(result);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeConnection(con);
		}
		
		return list;
	}

}

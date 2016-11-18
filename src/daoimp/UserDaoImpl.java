package daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DaoHelper;
import dao.UserDao;
import model.User;

public class UserDaoImpl implements UserDao{
	
	private static UserDaoImpl userDao=new UserDaoImpl();
	private static DaoHelper daoHelper=DaoHelperImpl.getBaseDaoInstance();
	
	private UserDaoImpl(){
		
	}
	
	public static UserDaoImpl getInstance(){
		return userDao;
	}

	@Override
	public User findUser(String userId, String password) {
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		User user=null;

		try {
			stmt = con.prepareStatement("select * from User where nickName=? and password=?");
			stmt.setString(1, userId.trim());
			stmt.setString(2, password.trim());
			
			result = stmt.executeQuery();
			
			if(result.next()){
				int id=result.getInt("userId");
				String trueName=result.getString("trueName").trim();
				String identity=result.getString("identity").trim();
				
				user=new User(id,trueName,userId.trim(),password.trim(),identity);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResult(result);
			
		}
		
		return user;
		
	}

	@Override
	public boolean addUser(User user) {
		java.sql.Connection con=daoHelper.getConnection();
		java.sql.PreparedStatement statement=null;
		ResultSet result=null;
		
		boolean isSuccess=true;
	
		try {
			statement = con.prepareStatement("select * from User where nickName=?");
			statement.setString(1, user.getNickName().trim());
			result = statement.executeQuery();
			
			if(result.next()){
				isSuccess=false;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			daoHelper.closeResult(result);
			daoHelper.closePreparedStatement(statement);			
		}

		
		if(isSuccess==false){
			return false;
		}else{
			try {
				statement=con.prepareStatement("insert into User(trueName,nickName,password,identity) values(?,?,?,?)");
				
				statement.setString(1, user.getTrueName());
				statement.setString(2,user.getNickName());
				statement.setString(3, user.getPassword());
				statement.setString(4, user.getIdentityString());
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

}

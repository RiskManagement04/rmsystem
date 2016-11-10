package daoImp;

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
	
	public String findPW(int studentid) {
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		String pw=null;
		
		try {
			stmt = con.prepareStatement("select password from students where studentid=?");
			stmt.setInt(1,studentid);
			result = stmt.executeQuery();
			
			if(result.next()){
				pw=result.getString("password").trim();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResult(result);
		}
		
		return pw;
	}

	@Override
	public User findUser(int userId, String password) {
		// TODO Auto-generated method stub
		return null;
	}


}

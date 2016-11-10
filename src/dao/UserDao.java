package dao;

import model.User;

public interface UserDao {
	
	public User findUser(int userId,String password);

}

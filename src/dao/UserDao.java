package dao;

import model.User;

public interface UserDao {
	
	public User findUser(String userId,String password);

}

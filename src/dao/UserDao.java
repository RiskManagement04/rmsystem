package dao;

import java.util.ArrayList;

import model.User;

public interface UserDao {
	
	public User findUser(String userId,String password);
	
	public boolean addUser(User user); 
	
	public ArrayList<User> findAllUsers();//查询所有用户

}

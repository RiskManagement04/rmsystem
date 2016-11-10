package model;

import java.io.Serializable;

public class User implements Serializable{
	
	private int userId;
	private String trueName;
	private String nickName;
	private String password;
	private UserType identity;
	
	public User(int userId,String trueName,String nickName,String password,String identity){
		this.userId=userId;
		this.trueName=trueName;
		this.nickName=nickName;
		this.password=password;
		
		if(identity.equals("MANAGER")){
			this.identity=UserType.MANAGER;
		}else{
			this.identity=UserType.DEVELOPER;
		}
		
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserType getIdentity() {
		return identity;
	}
	public String getIdentityString(){
		return convertIdentity(identity);
	}
	public void setIdentity(UserType identity) {
		this.identity = identity;
	}
	
	private String convertIdentity(UserType userType){
		if(userType.equals(UserType.DEVELOPER)){
			return "DEVELOPER";
		}else{
			return "MANAGER";
		}
	}

}

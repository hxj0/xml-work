package com.hxj.book.entity;

public class User {
	private String id;
	private String username;
	private String password;
	private String iconpath; //头像
	
	
	public User(String id, String username, String password, String iconpath) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.iconpath = iconpath;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIconpath() {
		return iconpath; 
	}
	public void setIconpath(String iconpath) {
		this.iconpath = iconpath;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", iconpath=" + iconpath + "]";
	}
	
	
}

package org.ritefruit.botter.model;

import java.sql.Timestamp;
import org.mongodb.morphia.annotations.IndexOptions;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.utils.IndexDirection;

@Entity(value = "User")
public class User {
	@Id
	private ObjectId id;

	@Property(value = "name")
	@Indexed(options = @IndexOptions(unique = true))
	private String username;
	
	@Property(value = "password")
	private String password;
	
	@Property(value = "first-name")
	@Indexed
	private String firstName;
	
	@Property(value = "last-name")
	@Indexed
	private String lastName;
	
	@Property(value = "reference-count")
	private int refCount;
	
	@Indexed(options = @IndexOptions(unique = true))
	private String email;
	private Timestamp createTime;
	private Timestamp updateTime;

	User() {
		refCount = 0;
	}

	public String getId(){
		return this.id.toHexString();
	}

	public String getUsername(){
		return this.username;
	}

	public void setUsername( String username){
		this.username = username;
	}

	public String getPassword(){
		return this.password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getFirstName(){
		return this.firstName;
	}

	public void setFirstName( String firstName){
		this.firstName = firstName;
	}

	public String getLastName(){
		return this.lastName;
	}

	public void setLastName( String lastName){
		this.lastName = lastName;
	}

	
	public String getEmail(){
		return this.email;
	}

	public void setEmail( String email){
		this.email = email;
	}

	public boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	public Timestamp getCreateTime(){
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime){
		this.createTime = createTime;
	}

	public void setCreateTime(){
		this.createTime = new Timestamp(System.currentTimeMillis());
	}

	public Timestamp getUpdateTime(){
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime){
		this.updateTime = updateTime;
	}

	public void setUpdateTime(){
		this.updateTime = new Timestamp(System.currentTimeMillis());
	}

}

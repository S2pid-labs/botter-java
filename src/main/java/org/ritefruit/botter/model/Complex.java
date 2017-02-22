package org.ritefruit.botter.model;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Reference;

@Entity(value = "complex")
public class Complex {
	@Id
	private ObjectId id;

	@Property(value = "name")
	@Indexed(options = @IndexOptions(unique = true))
	private String name;
	
	@Property(value = "address")
	private String address;
    
	@Property(value = "feedback_email")
	private String feedbackEmail;
	
	@Property(value = "create_time")
	private Timestamp createTime;
	
	@Property(value = "update_time")
	private Timestamp updateTime;
	
	@Reference("blocks")
	List<Block> block = new ArrayList<Block>();
	
	@Indexed
	@Embedded
	private List<Gate>  gate;
	
	@Reference	
	private User   createUser;

	@Reference
	ComplexUser UsersList;
	
	
	//toodo 
	//visit purpose
	

	public Complex() {
	
       // this.block       = new ArrayList <Block>();
       // this.gate        = new ArrayList <Gate>();
       // this.adminUser   = new ArrayList <User>();
       // this.securityUser= new ArrayList <User>();
	
	}

	public Complex(String name, String address, String feedbackEmailId, User createUser) {
		this.name    = name;
		this.address    = address;
		this.feedbackEmail  = feedbackEmailId;
        this.createUser     = createUser;
		this.createTime     = new Timestamp(System.currentTimeMillis());
		this.updateTime     = new Timestamp(System.currentTimeMillis());	


	}
	
    public String getId(){
		return this.id.toHexString();
	}

	public String getComplexName(){
		return this.name;
	}

	public void setComplexName(String complexName){
		this.name = complexName;
	}
	

	public String getComplexAddr(){
		return this.address;
	}

	public void setComplexAddr(String complexAddr){
		this.address = complexAddr;
	}

	public String getFeedbackEmail(){
		return this.feedbackEmail;
	}

	public void setFeedbackEmail(String feedbackEmail){
		this.feedbackEmail = feedbackEmail;
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

/*
	public User getCreateUser(){
		return this.createUser;
	}

	public void setCreateUser(User createUser){
		this.createUser = createUser;
	}
	
    public List< Block > getBlock() { return block; }
	public void setBlock( List< Block > block ) { this.block = block; }

	public List< Gate > getGate() { return gate; }
	public void setGate( List< Gate > gate ) { this.gate = gate; }
	
    public List< User > getSecurityUser() { return securityUser; }
	public void setSecurityUser( List< User > securityUser ) { this.securityUser = securityUser; }

	public List< User > getAdminUser() { return adminUser; }
	public void setAdminUser( List< User > adminUser ) { this.adminUser = adminUser; }
*/
}

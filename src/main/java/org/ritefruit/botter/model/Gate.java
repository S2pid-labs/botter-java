package org.ritefruit.botter.model;


import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Id;


@Entity(value = "gate")
public class Gate {
	@Id
	private ObjectId id;

	@Property(value = "complex-id")
	private ObjectId complexId;
	
	private String gateName;
	private String gateDesc;

    public String getGateName(){
		return this.gateName;
	}

	public void setGateName( String gateName){
		this.gateName = gateName;
	}
	
	public String getGateDesc(){
		return this.gateDesc;
	}

	public void setGateDesc( String gateDesc){
		this.gateDesc = gateDesc;
	}
}

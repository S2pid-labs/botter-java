package org.ritefruit.botter.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Entity(value = "complex")
public class UnitAdmin {
	@Id
	private ObjectId id;

	@Property(value = "complex-id")
	private ObjectId complexId;
	
	@Property(value = "block-id")
	private ObjectId blockId;
	
	@Property(value = "unit-id")
	private ObjectId unitId;
	
	
	@Property(value = "name")
	private String name;
	
	@Property(value = "description")
	private String description;
	
	@Property(value = "email")
	private String email;
	
	@Property(value = "contact")
	private String contact;

	

    public String getId(){
		return this.id.toHexString();
	}

	public String getComplexName(){
		return this.name;
	}
	
    public String getName(){
		return this.name;
	}

	public void setName( String name){
		this.name = name;
	}
	
    public String getDescription(){
		return this.description;
	}
    
    public void setDescription(String description){
		this.description = description;
	}
}

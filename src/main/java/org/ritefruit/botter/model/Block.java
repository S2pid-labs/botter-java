package org.ritefruit.botter.model;

import java.util.List;
import java.util.ArrayList;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

@Entity(value = "block")
public class Block {
	@Id
	private ObjectId id;

	@Property(value = "complex-id")
	private ObjectId complexId;
	
	@Property(value = "name")
	private String name;
	
	@Property(value = "description")
	private String description;
	
	@Reference("units")
	List<Unit> unit = new ArrayList<Unit>();
	

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

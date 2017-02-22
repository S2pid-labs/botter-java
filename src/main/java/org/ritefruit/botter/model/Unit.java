package org.ritefruit.botter.model;

import java.util.List;
import java.util.ArrayList;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

@Entity(value = "unit")
public class Unit {
	@Id
	private ObjectId id;

	@Property(value = "block-id")
	private ObjectId blockId;
	
	@Property(value = "name")
	private String name;
	
	//FIXME add intercom 
	//LET ROHIT COME BACK
	
//	@Property(value = "description")
//	private String description;
	
	@Reference("unit-admin")
	List<UnitAdmin> unitAdmin = new ArrayList<UnitAdmin>();
	

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
}

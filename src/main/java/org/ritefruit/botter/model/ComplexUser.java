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

@Entity(value = "complex-user")
public class ComplexUser {

	@Property(value = "complex-id")
	@Indexed
	private ObjectId complexId;
	
	public enum UserRole {
	    UNDEF,
	    UNIT_ADMIN,
	    COMPLEX_ADMIN,
	    SOFTWARE_ADMIN,
	    SECURITY_MAN
	}
	
	@Indexed
	@Reference("users")
	List<User> user = new ArrayList<User>();
	
	@Indexed
	List<UserRole> userRole = new ArrayList<UserRole>();
	
}
	
package org.ritefruit.botter.service;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.ritefruit.botter.model.*;

@Path("/complex")
public class ComplexService {

/*
	private void fixQuestionDisplay(Question question)
	{
		question.getUser().setPassword("xxxxxxxxx");
		for ( int i=0; i < question.getComments().size(); i++) {
			question.getComments().get(i).getUser().setPassword("XXXXX");
		}

		for ( int i=0; i < question.getAnswers().size(); i++) {
			question.getAnswers().get(i).getUser().setPassword("XXXXX");
		}
		question.setVotes(null);
	}
*/	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUpdateComplex(Complex complex, 
			@Context UriInfo uriInfo){
		Datastore dataStore = ServiceFactory.getMongoDB();
		complex.setCreateTime();
		complex.setUpdateTime();
		dataStore.save(complex);
		URI uriOfCreatedResource = URI.create(uriInfo.getRequestUri() +"/" + complex.getId());
		return Response.status(Response.Status.OK).location(uriOfCreatedResource).entity(complex).build();
	}
	//
	//curl -H 'Content-Type: application/json' -X PUT -d '{"complexName":"adfadf" , "complexAddr":"adfad", "feedbackEmail":"adfad" }' http://localhost:8080/ritefruit-botter/rest/complex
	
	
	
	@GET
	//@Secured
	//@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	public Response createUpdateComdplex( ){
		//Complex complx = null;
		return Response.status(Response.Status.OK).build();
	}
	
	
	@GET
	@Path("/{param}")
	//@Produces({MediaType.APPLICATION_JSON})
	public Response getQuestionById(@PathParam("param") String id) {
		//Datastore dataStore = ServiceFactory.getMongoDB();
		//ObjectId  oid;
		//Complex complex = null;
		//try {
		//	oid =  new ObjectId(id);
		//} catch(Exception e) {
		//	return Response.status(Response.Status.OK).build();
		//}
//
		//complex =  dataStore.get(Complex.class, oid);

		//if (complex != null) {
	//		return Response.status(Response.Status.OK).entity(complex).build();
	//	return Response.status(Response.Status.OK).build();
		String output = "Jersey say : " + id;

		return Response.status(200).entity(output).build();
	}
	
/*
	@PUT
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUpdateQuestion(Question ques, 
			@Context SecurityContext securityContext, 
			@Context UriInfo uriInfo){
		Question question = null;
		User user;
		String username = securityContext.getUserPrincipal().getName();
		Datastore dataStore = ServiceFactory.getMongoDB();

		ques.setCreateTime();
		ques.setUpdateTime();
		ques.setUsername(username);

		question =  dataStore.find(Question.class).field("title").equal(ques.getTitle()).get();

		if (question == null) {
			user =  dataStore.find(User.class).field("username").equal(username).get();
			ques.setUser(user);
			dataStore.save(ques);
			fixQuestionDisplay(ques);
			URI uriOfCreatedResource = URI.create(uriInfo.getRequestUri() +"/" + ques.getId());
			return Response.status(Response.Status.CREATED).location(uriOfCreatedResource).entity(ques).build();
		} else {
			if (question.getUsername().equals(username)) {
				question.setText(ques.getText());
				question.setUpdateTime();
				dataStore.save(question);
				fixQuestionDisplay(question);
				return Response.status(Response.Status.OK).entity(question).build();
			} else {
				return Response.status(Response.Status.UNAUTHORIZED).build();
			}
		}
	}

	@GET
	@Path("/{param}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getQuestionById(@PathParam("param") String id) {
		Datastore dataStore = ServiceFactory.getMongoDB();
		ObjectId  oid;
		Question question = null;
		try {
			oid =  new ObjectId(id);
		} catch(Exception e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		question =  dataStore.get(Question.class, oid);

		if (question != null) {
			fixQuestionDisplay(question);
			return Response.status(Response.Status.OK).entity(question).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@POST
	@Path("/{param}/view_incrementer")
	@Produces({MediaType.TEXT_PLAIN})
	public Response incViewCountById(@PathParam("param") String id) {
		Datastore dataStore = ServiceFactory.getMongoDB();
		ObjectId  oid;
		Question question = null;
		try {
			oid =  new ObjectId(id);
		} catch(Exception e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		question =  dataStore.get(Question.class, oid);

		if (question != null) {
			question.setViewCount(question.getViewCount() + 1);
			dataStore.save(question);
			return Response.status(Response.Status.OK).entity("Incremented View Count").build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@POST
	@Path("/search/length")
	@Consumes({MediaType.TEXT_PLAIN})
	@Produces({MediaType.TEXT_PLAIN})
	public int getLengthQuestionBySerch(String searchString) {
		Datastore dataStore = ServiceFactory.getMongoDB();
		Query<Question> q = dataStore.createQuery(Question.class).search(searchString);
		int size  = q.asList().size();
		return size;
	}	

	@POST
	@Path("/search")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.TEXT_PLAIN})
	public Response getQuestionBySearch(String search_string,
			@QueryParam("offset") String offset,
			@QueryParam("length") String length) {
		Datastore dataStore = ServiceFactory.getMongoDB();
		try {
			Query<Question> q = dataStore.createQuery(Question.class).search(search_string);
			List<Question> ques = null;

			if (offset == null && length == null) {
				ques = q.order("-viewCount").asList();
			} else {
				ques = q.offset(Integer.parseInt(offset)).limit(Integer.parseInt(length)).order("-viewCount").asList();
			}
			if (ques != null){
				for (int i = 0; i < ques.size(); i++) {
					//we should not send password
					ques.get(i).setAnswers(null);
					ques.get(i).setComments(null);
					ques.get(i).setVotes(null);
					ques.get(i).setUser(null);
				}
			}
			return Response.status(Response.Status.OK).entity(ques).build();
		}catch (Exception e) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
	}	

	@GET
	@Path("/length")
	@Produces({MediaType.TEXT_PLAIN})
	public int  getLengthAllQuestion() {
		Datastore dataStore = ServiceFactory.getMongoDB();
		Query<Question> q = dataStore.createQuery(Question.class);
		int size = q.asList().size();
		return size;
	}


	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getallQuestion( @QueryParam("offset") String offset,
			@QueryParam("length") String length){
		Datastore dataStore = ServiceFactory.getMongoDB();
		List<Question> ques = null;

		try {
			if (offset == null && length == null) {
				ques = dataStore.createQuery(Question.class).order("-viewCount").asList();
			} else {
				ques = dataStore.createQuery(Question.class).offset(Integer.parseInt(offset)).limit(Integer.parseInt(length)).order("-viewCount").asList();
			}
			if (ques != null){
				for (int i = 0; i < ques.size(); i++) {
					//we should not send password
					ques.get(i).setAnswers(null);
					ques.get(i).setComments(null);
					ques.get(i).setVotes(null);
					ques.get(i).setUser(null);
				}

				if (ques.size() > 0) {
					return Response.status(Response.Status.OK).entity(ques).build();
				}
			} 
			return Response.status(Response.Status.NO_CONTENT).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Secured
	@Path("/{param}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteQuestionById(@PathParam("param") String id,
			@Context SecurityContext securityContext) {
		Datastore dataStore = ServiceFactory.getMongoDB();
		ObjectId  oid;
		Question question = null;

		try {
			oid =  new ObjectId(id);
		} catch(Exception e) {
			return Response.status(Response.Status.OK).entity("Successfully Deleted question").build();
		}

		question =  dataStore.get(Question.class, oid);

		question =  dataStore.get(Question.class, oid);
		if (question != null){
			if( securityContext.getUserPrincipal().getName().equals("admin") || 
					securityContext.getUserPrincipal().getName().equals(
							question.getUser().getUsername()))	{

				for ( int i=0; i < question.getAnswers().size(); i++) {
					ObjectId idAns =  new ObjectId(question.getAnswers().get(i).getId());
					dataStore.delete(Answer.class, idAns);
				}	
				dataStore.delete(Question.class, oid);
				return Response.status(Response.Status.OK).entity("Successfully Deleted question").build();

			}else {
				return Response.status(Response.Status.UNAUTHORIZED).build();
			}	
		}
		return Response.status(Response.Status.OK).entity("Successfully Deleted question").build();
	}

	@PUT
	@Secured
	@Path("/{param}/comments")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postComment(Comment com, 
			@PathParam("param") String id,
			@Context SecurityContext securityContext ) {
		int i,j=0;
		boolean update = false;
		Datastore dataStore = ServiceFactory.getMongoDB();
		String username = securityContext.getUserPrincipal().getName();
		ObjectId  oid = null;
		try {
			oid =  new ObjectId(id);
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		Question question =  dataStore.get(Question.class, oid);

		if (question == null){
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		List <Comment> comment = question.getComments();

		for (i = 0; i < comment.size(); i++) {

			if (comment.get(i).getUsername().equals(username)) {
				update = true;
				j = i;
				break;
			}
		}

		if (update) {
			//EDIT case
			comment.get(j).setText(com.getText());
			comment.get(j).setUpdateTime();
			question.setComments(comment);
			dataStore.save(question);
			fixQuestionDisplay(question);	
			return Response.status(Response.Status.OK).entity(question).build();
		} else {
			//Create case
			User user =  dataStore.find(User.class).field("username").equal(username).get();
			Comment newComment = new Comment(com.getText(), username,user);
			comment.add(newComment);
			dataStore.save(question);
			fixQuestionDisplay(question);	
			return Response.status(Response.Status.CREATED).entity(question).build();
		}
	}

	@DELETE
	@Secured
	@Path("/{param}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteComment(@PathParam("param") String id,
			@Context SecurityContext securityContext ) {
		String username = securityContext.getUserPrincipal().getName();
		Datastore dataStore = ServiceFactory.getMongoDB();
		ObjectId  oid = null;
		int i;
		boolean found = false;

		try {
			oid =  new ObjectId(id);
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		Question question =  dataStore.get(Question.class, oid);

		if (question != null) {
			List <Comment> comment = question.getComments();

			for (i = 0; i < comment.size(); i++) {
				if (comment.get(i).getUsername().equals(username)) {
					found = true;
					break;
				}
			}
			if (found == true) {
				comment.remove(i);
				question.setComments(comment);
				dataStore.save(question);
			}
			fixQuestionDisplay(question);
			return Response.status(Response.Status.OK).entity(question).build();

		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}


	private Response postVote(String id,
			SecurityContext securityContext,
			boolean upvote ) {	
		String username = securityContext.getUserPrincipal().getName();
		Datastore dataStore = ServiceFactory.getMongoDB();
		ObjectId  oid = null;

		int i;
		int totalVote =0;
		boolean found = false;

		try {
			oid =  new ObjectId(id);
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		Question question =  dataStore.get(Question.class, oid);

		if (question == null){
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		List <Vote> votes = question.getVotes();

		
		if (votes == null) 
			votes = new ArrayList <Vote>();

		for (i = 0; i < votes.size(); i++) {
			if (votes.get(i).getUsername().equals(username)) {
				votes.get(i).setVote( upvote ? 1 : -1);
				found = true;
			}
			totalVote +=  votes.get(i).getVote();
		}

		//if vote from new user
		if (found == false) {
			User user =  dataStore.find(User.class).field("username").equal(username).get();
			Vote vote = new Vote(upvote ? 1 : -1, username,user); 
			votes.add(vote);
			totalVote +=  vote.getVote();
		}
		
		question.setVotes(votes);
		question.setTotalVotes(totalVote);
		dataStore.save(question);
		return Response.status(Response.Status.OK).entity( String.valueOf(totalVote)  ).build();
	}


	@POST
	@Secured
	@Path("/{param}/vote_down")
	@Produces(MediaType.TEXT_PLAIN)
	public Response postVoteDown(@PathParam("param") String id,
			@Context SecurityContext securityContext ) {

		return postVote(id, securityContext, false );

	}

	@POST
	@Secured
	@Path("/{param}/vote_up")
	@Produces(MediaType.TEXT_PLAIN)
	public Response postVoteUp(@PathParam("param") String id,
			@Context SecurityContext securityContext ) {
		return postVote(id, securityContext, true );
	}
	*/
}









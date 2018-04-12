package training.javabrains.facebook.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import training.javabrains.facebook.data.Comment;
import training.javabrains.facebook.service.CommentService;

@Path("/") // Coming from MessageResouce redirect: "/{messageId}/comments"
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

	CommentService commentService = new CommentService();

	// http://localhost:8080/RestTraining/rest/messages/1/comments
	@GET
	@Path("/")
	// KEY: The parent id (messageId) is accessable via @PathParam as well!
	public List<Comment> getCommentsForMessage(@PathParam("messageId") long messageId) {

		return commentService.getAllComments(messageId);
	}

	// http://localhost:8080/RestTraining/rest/messages/1/comments/2
	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {

		return commentService.getComment(messageId, commentId);
	}
}

package training.javabrains.facebook.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import training.javabrains.facebook.data.Message;
import training.javabrains.facebook.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON) // KEY: Consume input format
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();

	// ---------------------------------
	// KEY: Sub-Resource: Comment
	// ---------------------------------
	// KEY: Redirect to CommentResource!!!
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}

	// ---------------------------------
	// Main Resource: Message
	// ---------------------------------
	// http://localhost:8080/RestTraining/rest/messages
	@GET
	public List<Message> getMessages(@QueryParam("year") int year, @QueryParam("start") int start,
			@QueryParam("size") int size) {

		if (year > 0) {

			return messageService.getAllMessagesForYear(year);
		} else if (size > 0) {

			return messageService.getAllMessagesPagingnated(start, size);
		}

		return messageService.getAllMessages();
	}

	// http://localhost:8080/RestTraining/rest/messages/1
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) {

		// HATEOAS implementation
		Message message = messageService.getMessage(id);
		String uri = uriInfo.getBaseUriBuilder() // http://localhost:8080/RestTraining/rest/
							.path(MessageResource.class) // /messages
							.path(Long.toString(id)) // /2
							.build()
							.toString();
		message.addLink(uri, "self");

		return message;
	}

	// http://localhost:8080/RestTraining/rest/messages
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) {

		// Control the Response status!!
		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());

		URI uri = uriInfo	.getAbsolutePathBuilder()
							.path(newId) // Append the new Message ID to the URI
							.build(); // http://localhost:8080/RestTraining/rest/messages/3

		return Response	.created(uri)
						.entity(messageService.addMessage(message))
						.build();
	}

	// http://localhost:8080/RestTraining/rest/messages/2
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message) {

		message.setId(id);
		return messageService.updateMessage(message);
	}

	// http://localhost:8080/RestTraining/rest/messages/2
	@DELETE
	@Path("/{messageId}")
	public Message deleteMessage(@PathParam("messageId") long id) {

		return messageService.removeMessage(id);
	}

}

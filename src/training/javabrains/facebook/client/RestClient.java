package training.javabrains.facebook.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import training.javabrains.facebook.data.Message;

public class RestClient {

	public static void main(String[] args) {

		// ---------------------------------------
		// Get Message object
		// ---------------------------------------
		// 1. [KEY]: Create Client object
		Client client = ClientBuilder.newClient();

		// 2. [KEY]: Send request to get response
		Response response = client	.target("http://localhost:8080/RestTraining/rest/messages/1")
									.request()
									.get();

		// 3. [KEY]: Unwrap the Response object, and cast the entity
		Message message = response.readEntity(Message.class); // readEntity()!

		System.out.println(message.getMessage());

		// ---------------------------------------
		// Get the String response = JSON
		// ---------------------------------------
		String jsonString = client	.target("http://localhost:8080/RestTraining/rest/messages/1")
									.request()
									.get(String.class); // [KEY]: Sepcify the response Type to String.
		System.out.println(jsonString);

		// ---------------------------------------
		// Best Practice
		// ---------------------------------------
		WebTarget baseTarget = client.target("http://localhost:8080/RestTraining/rest/");
		WebTarget messagesTarget = baseTarget.path("messages");
		WebTarget singleMessageTarget = messagesTarget.path("{messageId}");

		Message message1 = singleMessageTarget	.resolveTemplate("messageId", "1")
												.request()
												.get(Message.class);
		Message message2 = singleMessageTarget	.resolveTemplate("messageId", "2")
												.request()
												.get(Message.class);
		System.out.println(message1.getMessage());
		System.out.println(message2.getMessage());

		// ---------------------------------------
		// POST: Add a Message
		// ---------------------------------------
		Message newMessage = new Message(5, "I love my family~ <3", "Eric");
		Response postResponse = messagesTarget	.request()
												.post(Entity.json(newMessage)); // [KEY]: Convert Message object to
																				// JSON!!
		Message savedMessage = postResponse.readEntity(Message.class);
		System.out.println("Saved Message: " + savedMessage.getMessage() + "; Status: " + postResponse.getStatus());

		// ---------------------------------------
		// PUT: Update a Message
		// ---------------------------------------
		message1 = new Message(1, "Updated Message", "Eric");
		Response putResponse = singleMessageTarget	.resolveTemplate("messageId", "1")
													.request()
													.put(Entity.json(message1));
		Message updatedMessage = putResponse.readEntity(Message.class);
		System.out.println("Updated Message: " + updatedMessage.getMessage() + "; Status: " + putResponse.getStatus());

		// ---------------------------------------
		// GET: List of Messages (Generic Type)
		// ---------------------------------------
		List<Message> messageList = messagesTarget	.request()
													.get(new GenericType<List<Message>>() {
													});
		messageList	.stream()
					.map(m -> m.getMessage())
					.forEach(System.out::println);

	}

}

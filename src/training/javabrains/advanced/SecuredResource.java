package training.javabrains.advanced;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * [KEY]: Will use "SecurityFilter" to handle the Basic Authentication.
 * Make it a cross-cutting concern!
 *
 */

@Path("secured")
public class SecuredResource {

	@GET
	@Path("message")
	@Produces(MediaType.TEXT_PLAIN)
	public String securedMethod() {

		return "A secured method!";
	}
}

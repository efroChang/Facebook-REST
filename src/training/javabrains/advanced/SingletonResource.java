package training.javabrains.advanced;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("singleton")
@Singleton // KEY: Create only one instance, so the count can increment.
public class SingletonResource {

	private int count = 0;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {

		count++;
		return "The count is : " + count;
	}
}

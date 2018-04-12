package training.javabrains.advanced;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("body")
public class BodyConverterResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Date getToday() {
		
		// [KEY]: JAX-RS will find "DateMessageBodyWriter" to do the conversion.
		return Calendar	.getInstance()
						.getTime();
	}

}

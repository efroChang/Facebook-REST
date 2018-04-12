package training.javabrains.advanced;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import training.javabrains.advanced.pojo.MyDate;

@Path("date/{dateString}")
public class ParamConvertorResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	// [KEY]: Accept a MyDate object, use MyDateConverterProvider
	// to convert from String to MyDate object!
	public String getRequestedDate(@PathParam("dateString") MyDate myDate) {

		return "Got " + myDate.toString();
	}

}

package training.eduraka.hello;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

// KEY: for running on Tomcat server
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@Path("/hello")
public class Hello {

	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayHelloXML() {

		String resource = "<? xml version='1.0; ?>" + "<hello>Hi Emma, This is Hello from XML</hello>";
		return resource;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String sayHelloJSON() {
		String resource = null;
		return resource;
	}

	// Right Click on "RestTraining" project and Run As: "Run On Server"
	// On web browser:
	// http://localhost:8080/RestTraining/rest/hello?name=Emma&Card_no=12345&amount=100000000

	// *** Difference between @PathParam and @QueryParam:
	// @PathParam: http://localhost:8080/RestTraining/rest/hello/{name}
	// @QueryParam: http://localhost:8080/RestTraining/rest/hello?name=Emma

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHelloHTML(@QueryParam("name") String name, @QueryParam("Card_no") String Card_no,
			@QueryParam("amount") int amount) {

		String response;

		System.out.println("Name is " + name);
		System.out.println("Amount is " + amount);

		if (amount > 100000) {
			System.out.println("Amount is greater than 100K");

			response = "Credit Card is not apprived.";

		} else {
			System.out.println("Amount is less than 100K");

			response = "Credit Card is apprived.";
		}

		return "<html>" + "<title>" + "Credit Card Auth " + name + "</title>" + "<body><h1>" + response
				+ "</h1></body></html>";
	}
}

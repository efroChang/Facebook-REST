package training.eduraka;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/temper")
public class TemperatureService {

	// http://localhost:8080/RestTraining/rest/temper/c2f/32.1
	@GET
	@Path("/c2f/{c}")
	@Produces(MediaType.TEXT_HTML)
	public String convertC2F(@PathParam("c") Double c) {

		String response;

		// [°F] = [°C] × 9/5 + 32
		Double f;
		f = ((c * 9) / 5) + 32;
		response = "Celsius: " + c + " is Fahreheit: " + f;

		System.out.println(response);

		return "<html>" + "<title>" + "Conver C to F </title>" + "<body><h1>" + response + "</h1></body></html>";
	}

	// http://localhost:8080/RestTraining/rest/temper/f2c/100.01
	@GET
	@Path("/f2c/{f}")
	@Produces(MediaType.TEXT_HTML)
	public String convertF2C(@PathParam("f") Double f) {

		String response;

		// [°C] = ([°F] - 32) × 5/9
		Double c;
		c = (f - 32) * 5 / 9;
		response = "Fahreheit: " + f + " is Celsius: " + c;

		System.out.println(response);

		return "<html>" + "<title>" + "Conver F to C </title>" + "<body><h1>" + response + "</h1></body></html>";
	}

	// ---------------------------------------
	// JSON Output
	// ---------------------------------------
	// http://localhost:8080/RestTraining/rest/temper/c2f/json/32.1
	@GET
	@Path("/c2f/json/{c}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response convertC2FJSON(@PathParam("c") Double c) throws JSONException {

		String response;

		// [°F] = [°C] × 9/5 + 32
		Double f;
		f = ((c * 9) / 5) + 32;
		response = "Celsius: " + c + " is Fahreheit: " + f;

		System.out.println(response);

		// JSON
		JSONObject json = new JSONObject();
		json.put("Celsius", c);
		json.put("Fahreheit", f);

		response = "@Produces(\"application/json\") Output: \n\nC to F \n\n" + json;
		
		return Response	.status(200)
						.entity(response)
						.build();
	}

	// http://localhost:8080/RestTraining/rest/temper/f2c/json/100.01
	@GET
	@Path("/f2c/json/{f}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response convertF2CJSON(@PathParam("f") Double f) throws JSONException {

		String response;

		// [°C] = ([°F] - 32) × 5/9
		Double c;
		c = (f - 32) * 5 / 9;
		response = "Fahreheit: " + f + " is Celsius: " + c;
		
		// JSON
		JSONObject json = new JSONObject();
		json.put("Celsius", c);
		json.put("Fahreheit", f);

		response = "@Produces(\"application/json\") Output: \n\nF to C \n\n" + json;

		System.out.println(response);

		return Response	.status(200)
				.entity(response)
				.build();
	}

}

package training.eduraka;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class TemperatureClient {

	private Client client;

	public static void main(String[] args) {

		TemperatureClient tempTester = new TemperatureClient();
		tempTester.init();
		tempTester.getC2F();

	}

	public void init() {
		
		// KEY: Create the Client
		this.client = ClientBuilder.newClient();
	}

	public void getC2F() {
		
		// KEY: Connect to REST Service
		Response response = client	.target("http://localhost:8080/RestTraining/rest/temper/c2f/json/32.1")
									.request(MediaType.APPLICATION_JSON)
									.get();

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed HTTP error code: " + response.getStatus());
		}

		System.out.println(response.readEntity(String.class)); // Print out the JSON
	}

}

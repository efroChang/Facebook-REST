package training.javabrains.advanced.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 * This will be called before the Response is sent to the client.
 *
 */

@Provider
public class PoweredByResponseFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {

		// [KEY]: This will be added to all the Response Header for every Request.
		// Test: http://localhost:8080/RestTraining/restful/singleton, and examine the Header.
		responseContext	.getHeaders()
						.add("X-Powered-By", "Eric Chang");

	}

}

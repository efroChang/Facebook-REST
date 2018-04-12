package training.javabrains.advanced.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 * Implement BOTH ContainerRequestFilter and ContainerResponseFilter
 *
 */

@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		System.out.println("Logging from Request Filter...");
		System.out.println(requestContext.getHeaders());
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {

		System.out.println("Logging from Response Filter...");
		System.out.println(responseContext.getHeaders());

	}

}

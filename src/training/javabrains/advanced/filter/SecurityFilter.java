package training.javabrains.advanced.filter;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

@Provider
public class SecurityFilter implements ContainerRequestFilter {

	private static final String AUTHERIZATION_HEADER = "Authorization";
	private static final String AUTHERIZATION_HEADER_PREFIX = "Basic ";
	private static final String SECURED_URL_PREFIX = "secured"; // the @Path() in the resource

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		// [KEY]: We only want to check the log in info when the URI contains "secured"
		if (requestContext	.getUriInfo()
							.getPath()
							.contains(SECURED_URL_PREFIX)) {

			List<String> authTokenList = requestContext	.getHeaders()
														.get(AUTHERIZATION_HEADER);
			
			if (authTokenList != null && authTokenList.size() > 0) {

				/**
				 * Get the encoded Authorization value from Header. Decode and parse out the
				 * User Name and Password
				 */
				String authToken = authTokenList.get(0); // "Basic dXNlcjpwYXNzd3JvZA=="
				authToken = authToken.replaceFirst(AUTHERIZATION_HEADER_PREFIX, ""); // "dXNlcjpwYXNzd3JvZA=="
				String decodedToken = Base64.decodeAsString(authToken); // "user:password" [KEY]: Use Base64 decoder
				StringTokenizer tokenizer = new StringTokenizer(decodedToken, ":"); // "user:password"
				String userName = tokenizer.nextToken(); // "user"
				String password = tokenizer.nextToken(); // "password"
				if ("user".equals(userName) && "password".equals(password)) {

					return; // Let the request pass!
				}
			}

			// [KEY]: Generated Unauthorized Response
			Response unAuthResponse = Response	.status(Response.Status.UNAUTHORIZED)
												.entity("The username and password is incorrect!")
												.build();
			// [KEY]: Send the Response back!
			requestContext.abortWith(unAuthResponse);

		}
	}

}

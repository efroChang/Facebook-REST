package training.javabrains.facebook.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import training.javabrains.facebook.data.ErrorInfo;

/**
 * The catch all Exception Mapper! 
 * It handles things like incomplete URI etc.
 *
 */

@Provider // KEY: Register this mapper to JAX-RS
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable e) {

		ErrorInfo errorInfo = new ErrorInfo(e.getMessage(), 500, "http://www.google.com");

		return Response	.status(Status.INTERNAL_SERVER_ERROR)
						.entity(errorInfo)
						.build();
	}
}

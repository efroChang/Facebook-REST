package training.javabrains.facebook.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import training.javabrains.facebook.data.ErrorInfo;

@Provider // KEY: Register this mapper to JAX-RS
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException e) {

		ErrorInfo errorInfo = new ErrorInfo(e.getMessage(), 404, "http://www.google.com");

		return Response	.status(Status.NOT_FOUND)
						.entity(errorInfo)
						.build();
	}

}

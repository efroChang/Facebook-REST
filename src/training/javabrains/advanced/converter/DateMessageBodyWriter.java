package training.javabrains.advanced.converter;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Date;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

// [KEY]: A Provider
@Provider
// [KEY]: Specify the output type. JAX-RS will know when it needs to return
// TEXT_PLAIN, look into this MessageBodyWriter.
@Produces(MediaType.TEXT_PLAIN)
public class DateMessageBodyWriter implements MessageBodyWriter<Date> {

	@Override
	public boolean isWriteable(Class<?> type, Type arg1, Annotation[] arg2, MediaType arg3) {

		// [KEY]: Check to see if the given "type" is a Date object!
		return Date.class.isAssignableFrom(type);
	}

	@Override
	// [KEY]: Key params are: date, type, and out.
	public void writeTo(Date date, Class<?> type, Type type1, Annotation[] annotations, MediaType mt,
			MultivaluedMap<String, Object> mm, OutputStream out) throws IOException, WebApplicationException {

		// Here we convert the given "date" object, and send to OutputStream.
		out.write(date	.toString()
						.getBytes());
	}
}

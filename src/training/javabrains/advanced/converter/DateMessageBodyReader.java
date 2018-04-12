package training.javabrains.advanced.converter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Date;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

// [KEY]: A Provider
@Provider
// [KEY]: Specify the input type. JAX-RS will know when it needs to return
// TEXT_PLAIN, look into this MessageBodyReader.
@Produces(MediaType.TEXT_PLAIN)
public class DateMessageBodyReader implements MessageBodyReader<Date> {

	@Override
	public boolean isReadable(Class<?> type, Type arg1, Annotation[] arg2, MediaType arg3) {

		return Date.class.isAssignableFrom(type);
	}

	@Override
	public Date readFrom(Class<Date> date, Type type, Annotation[] annotation, MediaType mt,
			MultivaluedMap<String, String> mm, InputStream in) throws IOException, WebApplicationException {

		return null;
	}

}

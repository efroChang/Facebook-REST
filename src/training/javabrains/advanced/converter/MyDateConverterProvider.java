package training.javabrains.advanced.converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Calendar;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import training.javabrains.advanced.pojo.MyDate;

// [KEY]: Declare this is a Provider!!! 
@Provider
public class MyDateConverterProvider implements ParamConverterProvider {

	@Override
	public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {

		// [KEY]: Check if the rawType is "MyDate"
		if (rawType	.getName()
					.equals(MyDate.class.getName())) {

			// Anonymous implementation of ParamConverter
			return new ParamConverter<T>() {

				// [KEY]: The real conversion from String to MyDate object.
				@Override
				public T fromString(String paramValue) {

					Calendar today = Calendar.getInstance();
					if ("tomorrow".equalsIgnoreCase(paramValue)) {

						today.add(Calendar.DATE, 1);

					} else if ("yesterday".equalsIgnoreCase(paramValue)) {

						today.add(Calendar.DATE, -1);
					}

					// Create MyDate object
					MyDate myDate = new MyDate();
					myDate.setDate(today.get(Calendar.DATE));
					myDate.setMonth(today.get(Calendar.MONTH));
					myDate.setYear(today.get(Calendar.YEAR));

					return rawType.cast(myDate); // [KEY]: T.case()
				}

				@Override
				public String toString(T myBean) {

					if (myBean == null) {
						return null;
					}
					return myBean.toString();
				}

			};
		}

		return null;
	}

}

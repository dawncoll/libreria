package daw.libreria.persistence.pagination;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamUtils {

	@SuppressWarnings("unchecked")
	public static <T> Stream<T> as(Object object) {
		if (object instanceof Iterable) {
			return StreamSupport.stream(((Iterable<T>) object).spliterator(), false);
		} else if (object.getClass().isArray()) {
			return Stream.of((T[])object);
		} else if (object instanceof Iterator) {
			return iteratorToFiniteStream((Iterator<T>) object, false);
		}
		return (Stream<T>) Stream.of(object);
	}
	
	private static <T> Stream<T> iteratorToFiniteStream(Iterator<T> iterator, boolean parallel) {
	    final Iterable<T> iterable = () -> iterator;
	    return StreamSupport.stream(iterable.spliterator(), parallel);
	}
}

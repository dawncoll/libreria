package daw.libreria.persistence.pagination;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

public interface BasePaginationMapper {

	default <R, T> Page<R> toPage(Page<T> source, Function<T, R> function) {
		List<R> result = source == null ? null : source.getContent().stream()
			.map(function::apply)
			.collect(Collectors.toList());
		return source == null ?  null : PageHelper.toPage(source, result);
	}
	
	default <R, T> Page<R> toPage(List<T> source, Function<T, R> function) {
		List<R> result = source == null ? null : source.stream()
			.map(function::apply)
			.collect(Collectors.toList());
		return result == null ?  null : PageHelper.toPage(result);
	}	
}

package daw.libreria.persistence.pagination;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StreamUtilsUnitTest {

		
	@Test
	public void givenListInt_whenAs_thenSuccess() {
		//given
		Object obj = List.of(1, 2, 3);
		
		//when
		List<Integer> results = StreamUtils.<Integer>as(obj).collect(Collectors.toList());

		//then
		assertThat(results).containsExactly(1, 2, 3);
	}	
	
	@Test
	public void givenArrayInt_whenAs_thenSuccess() {
		//given
		Object obj = List.of(1, 2, 3).toArray();
		
		//when
		List<Integer> results = StreamUtils.<Integer>as(obj).collect(Collectors.toList());

		//then
		assertThat(results).containsExactly(1, 2, 3);
	}	
	
	@Test
	public void givenOneObject_whenAs_thenSuccess() {
		//given
		Object obj = Boolean.TRUE;
		
		//when
		List<Boolean> results = StreamUtils.<Boolean>as(obj).collect(Collectors.toList());

		//then
		assertThat(results).containsExactly(true);
	}	
	
}

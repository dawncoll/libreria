package daw.libreria.persistence.pagination;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

class PageHelperUnitTest {

	@Test
	void givenEmptyPageAndList_whenToPage_thenSucess() {
		//given
		Page<String> page = Page.empty();
		List<String> list = Collections.nCopies(10, "1");
		
		// when
		Page<String> result = PageHelper.toPage(page, list);

		//then
		assertThat(result.getNumberOfElements()).isEqualTo(list.size());
		assertThat(result.getTotalElements()).isEqualTo(list.size());
	}
	
	@Test
	void givenEmptyPageWithPageableAndList_whenToPage_thenSucess() {
		//given
		Page<String> page = Page.empty(Pageable.ofSize(3));
		List<String> list = Collections.nCopies(10, "1");
		
		// when
		Page<String> result = PageHelper.toPage(page, list);

		//then
		assertThat(result.getNumberOfElements()).isEqualTo(3);
		assertThat(result.getTotalElements()).isEqualTo(list.size());
	}
	
	@Test
	void givenPageAndList_whenToPage_thenSucess() {
		//given
		Page<String> pageSource = new PageImpl<>(Collections.nCopies(3, "1"), Pageable.ofSize(3), 10);
		List<String> list = Collections.nCopies(3, "2");
		
		// when
		Page<String> result = PageHelper.toPage(pageSource, list);

		//then
		assertThat(result.getNumberOfElements()).isEqualTo(3);
		assertThat(result.getTotalElements()).isEqualTo(pageSource.getTotalElements());
		assertThat(result.getContent().get(0)).isEqualTo("2");
	}	
	
	@Test
	void givenOneList_whenToPage_thenSucess() {
		//given
		List<String> list = Collections.nCopies(10, "1");
		
		// when
		Page<String> result = PageHelper.toPage(list);

		//then
		assertThat(result.getNumberOfElements()).isEqualTo(list.size());
		assertThat(result.getTotalElements()).isEqualTo(list.size());
	}	
	
	@Test
	void givenOneListWithPaging_whenToPage_thenSucess() {
		//given
		List<String> list = Collections.nCopies(10, "1");
		Pageable paging = Pageable.ofSize(3);
		
		// when
		Page<String> result = PageHelper.toPage(list, paging);

		//then
		assertThat(result.getPageable()).isEqualTo(paging);
		assertThat(result.getNumberOfElements()).isEqualTo(paging.getPageSize());
		assertThat(result.getTotalElements()).isEqualTo(list.size());
	}	
	
	
	
	@Test
	void givenTwoListWithPage_whenToPage_thenSucess() {
		//given
		Page<String> paging = new PageImpl<>(Collections.nCopies(1, "1"), Pageable.ofSize(3), 1);
		List<String> list = Collections.nCopies(10, "1");
		
		// when
		Page<String> result = PageHelper.toPage(paging, list);

		//then
		assertThat(result.getSize()).isEqualTo(paging.getSize());
		assertThat(result.getNumberOfElements()).isEqualTo(paging.getSize());
		assertThat(result.getTotalElements()).isEqualTo(list.size());
	}	
	
	@Test
	void givenOnePage_whenToList_thenSucess() {
		//given
		Page<String> paging = new PageImpl<>(Collections.nCopies(10, "1"), Pageable.ofSize(3), 10);
		
		// when
		List<String> result = PageHelper.toList(paging);

		//then
		assertThat(result).hasSize(paging.getSize());
		assertThat((long)result.size()).isNotEqualTo(paging.getTotalElements());
	}	
	
	
}

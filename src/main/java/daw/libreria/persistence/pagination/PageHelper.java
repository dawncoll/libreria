package daw.libreria.persistence.pagination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.querydsl.QSort;



public abstract class PageHelper {
	
	private PageHelper() {
		throw new IllegalStateException("Utility class");
	}
	
	public static <T> Page<T> emptyPage() {
		return toPage(Collections.emptyList());
	}
	
	public static <T> Page<T> toPage(List<T> list) {
		return createPage(list, Pageable.unpaged(), list.size());
	}
	
	public static <T> Page<T> toPage(List<T> list, Pageable paging) {
		return createPage(list, paging, list.size());
	}
	
	public static <A, B> Page<B> toPage(Page<A> pageSource, List<B> newContent) {
		return createPage(newContent, pageSource.getPageable(), pageSource.getTotalElements());
	}		
	
	protected static <T> Page<T> createPage(List<T> list, Pageable paging, long total) {
		if (paging.isUnpaged()) {
			return new PageImpl<>(list);
		} else {
			long maxTotal = Math.max(total, list.size());
			if (paging.getOffset() >= list.size()) {
				return new PageImpl<>(list, toPageable(paging), maxTotal);
			}
			int start = (int) Math.min(paging.getOffset(), list.size());
			int end =  Math.min((start + paging.getPageSize()), list.size());
			return new PageImpl<>(list.subList(start, end), toPageable(paging), maxTotal);
		}
	}	
	
	private static Pageable toPageable(Pageable paging) {
		if (paging.getSort() instanceof QSort) {
			return org.springframework.data.domain.PageRequest.of(paging.getPageNumber(), paging.getPageSize(), 
					Sort.by(StreamUtils.<Order>as(((QSort)paging.getSort()).iterator()).collect(Collectors.toList())));
		}
		return paging;
	}

	public static <T> List<T> toList(Page<T> page) {
		if (page == null) {
			return new ArrayList<>();
		} else {
			//ToDo fix paging of content in memory
			return createPage(page.getContent(), page.getPageable(), page.getTotalElements()).getContent();
		}
	}
	
	
	
	public static Pageable toPageable(int pageNumber, int pageSize, String sortBy, boolean ascending) {
		Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return pageable;
	}	

}

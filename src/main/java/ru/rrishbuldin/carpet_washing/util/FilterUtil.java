package ru.rrishbuldin.carpet_washing.util;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.rrishbuldin.carpet_washing.dto.filter.FilterDto;
import ru.rrishbuldin.carpet_washing.enums.SortOrder;

public class FilterUtil {

    /**
     *
     */
    public static Pageable getPageable(FilterDto filter) {
        Sort sort = filter.getSort() != null && filter.getSort().getColumn() != null && filter.getSort().getSortOrder() != null ?
                Sort.by(filter.getSort().getSortOrder() == SortOrder.ASC ? Sort.Order.asc(filter.getSort().getColumn()) : Sort.Order.desc(filter.getSort().getColumn())) :
                Sort.unsorted();

        int offset = filter.getPage() != null && filter.getPage().getOffset() >= 0 ? filter.getPage().getOffset() : 0;
        int max = filter.getPage() != null && filter.getPage().getMax() > 0 ? filter.getPage().getMax() : 10;
        return PageRequest.of(offset, max, sort);
    }

}

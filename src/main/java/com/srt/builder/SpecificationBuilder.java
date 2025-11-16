package com.srt.builder;

import com.srt.model.ColumnFilter;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

public class SpecificationBuilder<T> {

    public Specification<T> buildSpecificationFromFilters(Class<T> clazz, List<ColumnFilter> filterModel) {
        Specification<T> specification = getEmptySpecification();

        for (ColumnFilter filter : filterModel) {
            Specification<T> fieldSpec = getFieldSpecification(clazz, filter);
            specification = specification.and(fieldSpec);
        }

        return specification;
    }

    private Specification<T> getFieldSpecification(Class<T> clazz, ColumnFilter filter) {
        return (root, query, criteriaBuilder) -> {
            return PredicateBuilder.toPredicate(filter, root, query, criteriaBuilder);
        };
    }

    private Specification<T> getEmptySpecification() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
    }
}

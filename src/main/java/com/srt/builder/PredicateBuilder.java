package com.srt.builder;

import com.srt.model.ColumnFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;
import java.util.List;

public class PredicateBuilder {

    public static <T> Predicate toPredicate(ColumnFilter columnFilter, Root<T> root, CriteriaQuery<?> query,
                                            CriteriaBuilder builder) {
        final String filterType = columnFilter.getFilterType();
        return switch (filterType) {
            case "text" -> buildTextPredicate(columnFilter, root, builder);
            case "number" -> buildNumberPredicate(columnFilter, root, builder);
            case "date" -> buildDatePredicate(columnFilter, root, builder);
            default -> throw new IllegalArgumentException("Unsupported filter type: " + filterType);
        };
    }

    private static <T> Predicate buildTextPredicate(ColumnFilter columnFilter, Root<T> root,
                                                CriteriaBuilder builder) {
        final String operator = columnFilter.getFilterOperator();
        if (operator.equalsIgnoreCase("equals")) {
            return builder.equal(root.get(columnFilter.getColumnId()), columnFilter.getValue());
        } else if (operator.equalsIgnoreCase("notEqual")) {
            return builder.notEqual(root.get(columnFilter.getColumnId()), columnFilter.getValue());
        } else if (operator.equalsIgnoreCase("like")) {
            return builder.like(root.get(columnFilter.getColumnId()), "%" + columnFilter.getValue() + "%");
        } else if (operator.equalsIgnoreCase("in")) {
            return root.get(columnFilter.getColumnId()).in(columnFilter.getValues());
        } else {
            throw new IllegalArgumentException("Unsupported text filter operator: " + operator);
        }
    }

    private static <T> Predicate buildNumberPredicate(ColumnFilter columnFilter, Root<T> root,
                                                  CriteriaBuilder builder) {
        final String operator = columnFilter.getFilterOperator();
        if (operator.equalsIgnoreCase("equals")) {
            return builder.equal(root.get(columnFilter.getColumnId()), Double.valueOf(columnFilter.getValue()));
        } else if (operator.equalsIgnoreCase("notEqual")) {
            return builder.notEqual(root.get(columnFilter.getColumnId()), Double.valueOf(columnFilter.getValue()));
        } else if (operator.equalsIgnoreCase("lessThan")) {
            return builder.lessThan(root.get(columnFilter.getColumnId()), Double.valueOf(columnFilter.getValue()));
        } else if (operator.equalsIgnoreCase("greaterThan")) {
            return builder.greaterThan(root.get(columnFilter.getColumnId()), Double.valueOf(columnFilter.getValue()));
        } else if (operator.equalsIgnoreCase("inRange")) {
            return builder.between(root.get(columnFilter.getColumnId()),
                    Double.valueOf(columnFilter.getValue()),
                    Double.valueOf(columnFilter.getValueTo()));
        } else if (operator.equalsIgnoreCase("in")) {
            final List<Double> values = columnFilter.getValues().stream()
                    .map(Double::valueOf)
                    .toList();
            return root.get(columnFilter.getColumnId()).in(values);
        } else {
            throw new IllegalArgumentException("Unsupported number filter operator: " + operator);
        }
    }

    private static <T> Predicate buildDatePredicate(ColumnFilter columnFilter, Root<T> root,
                                                CriteriaBuilder builder) {
        final String operator = columnFilter.getFilterOperator();
        final LocalDate dateValue = LocalDate.parse(columnFilter.getValue());
        if (operator.equalsIgnoreCase("equals")) {
            return builder.equal(root.get(columnFilter.getColumnId()), dateValue);
        } else if (operator.equalsIgnoreCase("notEqual")) {
            return builder.notEqual(root.get(columnFilter.getColumnId()), dateValue);
        } else if (operator.equalsIgnoreCase("lessThan")) {
            return builder.lessThan(root.get(columnFilter.getColumnId()), dateValue);
        } else if (operator.equalsIgnoreCase("greaterThan")) {
            return builder.greaterThan(root.get(columnFilter.getColumnId()), dateValue);
        } else if (operator.equalsIgnoreCase("inRange")) {
            final LocalDate dateTo = LocalDate.parse(columnFilter.getValueTo());
            return builder.between(root.get(columnFilter.getColumnId()), dateValue, dateTo);
        } else {
            throw new IllegalArgumentException("Unsupported date filter operator: " + operator);
        }
    }
}

package com.group.azura.maraissa.controleQualite.specification;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BaseSpecification <T> implements Specification<T> {
    private List<SearchCriteria> criterias;

    public BaseSpecification(List<SearchCriteria> criterias) {
        this.criterias = criterias;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        // create a new predicate list
        List<Predicate> predicates = new ArrayList<>();

        // add criteria to predicates
        for (SearchCriteria criteria : criterias) {
            String key = criteria.getKey();
            Object value = criteria.getValue();

            if (key == null || value == null || String.valueOf(value).isEmpty()) {
                continue;
            }

            Path<?> path = root;
            String[] attributes = key.split("\\.");

            for (String attribute : attributes) {
                path = path.get(attribute);
            }

            switch (criteria.getOperation()) {
                case GREATER_THAN:
                    predicates.add(builder.greaterThan(
                            path.as(Double.class), Double.parseDouble(value.toString())));
                    break;
                case LESS_THAN:
                    predicates.add(builder.lessThan(
                            path.as(Double.class), Double.parseDouble(value.toString())));
                    break;
                case GREATER_THAN_EQUAL:
                    predicates.add(builder.greaterThanOrEqualTo(
                            path.as(Double.class), Double.parseDouble(value.toString())));
                    break;
                case LESS_THAN_EQUAL:
                    predicates.add(builder.lessThanOrEqualTo(
                            path.as(Double.class), Double.parseDouble(value.toString())));
                    break;
                case NOT_EQUAL:
                    predicates.add(builder.notEqual(
                            path, value));
                    break;
                case EQUAL:
                    predicates.add(builder.equal(
                            path, value));
                    break;
                case MATCH:
                    predicates.add(builder.like(
                            builder.lower(path.as(String.class)),
                            "%" + value.toString().toLowerCase() + "%"));
                    break;
                case MATCH_END:
                    predicates.add(builder.like(
                            builder.lower(path.as(String.class)),
                            "%" + value.toString().toLowerCase()));
                    break;
                case MATCH_START:
                    predicates.add(builder.like(
                            builder.lower(path.as(String.class)),
                            value.toString().toLowerCase() + "%"));
                    break;
                case IN:
                    predicates.add(path.in(value));
                    break;
                case NOT_IN:
                    predicates.add(builder.not(path.in(value)));
                    break;
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}

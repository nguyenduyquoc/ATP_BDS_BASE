package com.anthinhphatjsc.ezisolutions.core;

import com.anthinhphatjsc.ezisolutions.common.Filter;
import com.anthinhphatjsc.ezisolutions.entities.RoleEntity;
import com.anthinhphatjsc.ezisolutions.entities.UserEntity;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

public class BaseSpecifications<T> {

    public static <T> Specification<T> getSpecification(List<Filter> filters) {
        BaseSpecifications<T> baseSpecifications = new BaseSpecifications<>();
        return baseSpecifications.getQueryResult(filters);
    }

    public static <T> Specification<T> ofEmpty() {
        BaseSpecifications<T> baseSpecifications = new BaseSpecifications<>();
        return baseSpecifications.getQueryResult(null);
    }



    public Specification<T> getQueryResult(List<Filter> filters) {
        return (filters != null && !filters.isEmpty())
                ? getSpecificationFromFilters(filters)
                : Specification.where(null);
    }

    public Specification<T> getSpecificationFromFilters(List<Filter> filter) {
        Specification<T> specification = Specification.where(null);
        for (Filter input : filter) {
            specification = specification.and(createSpecification(input));
        }
        return specification;
    }
    public Specification<T> createSpecification(Filter input) {

        return switch (input.getOperator()) {

            case EQUALS -> (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                if (input.getField().equals("roles.id") && input.getValues() != null && !input.getValues().isEmpty()) {
                    Join<UserEntity, RoleEntity> rolesJoin = root.join("roles");
                    for (String roleId : input.getValues()) {
                        predicates.add(criteriaBuilder.equal(rolesJoin.get("id"),
                                castToRequiredType(Long.class, roleId)));
                    }
                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                } else {
                    return criteriaBuilder.equal(root.get(input.getField()),
                            castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
                }
            };

            case NOT_EQ -> (root, query, criteriaBuilder) ->
                    criteriaBuilder.notEqual(root.get(input.getField()),
                            castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
            case GREATER_THAN -> (root, query, criteriaBuilder) ->
                    criteriaBuilder.gt(root.get(input.getField()),
                            (Number) castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
            case LESS_THAN -> (root, query, criteriaBuilder) ->
                    criteriaBuilder.lt(root.get(input.getField()),
                            (Number) castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
            case LIKE -> (root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get(input.getField()), "%" + input.getValue() + "%");
            case IN -> (root, query, criteriaBuilder) ->
                    criteriaBuilder.in(root.get(input.getField()))
                            .value(castToRequiredType(root.get(input.getField()).getJavaType(), input.getValues()));
        };
    }

    // tìm chính xác record không phân biệt hoa thường
    public Specification<T> exactIgnoreCase(String property, String term) {
        return (root, query, cb) -> cb.equal(cb.lower(root.get(property)), term);
    }

    // tìm các records chứa một phần giá trị của trường đó không phân biệt hoa thường
    public Specification<T> containsIgnoreCase(String property, String searchTerm) {
        return (root, query, cb) -> {
            String containsLikePattern = getContainsLikePattern(searchTerm);
            return cb.like(cb.lower(root.get(property)), containsLikePattern);
        };
    }

    public static String getContainsLikePattern(String searchTerm) {
        return (searchTerm == null || searchTerm.isEmpty())
                ? "%"
                : "%" + searchTerm.toLowerCase() + "%";
    }

    public Object castToRequiredType(Class<?> fieldType, String value) {
        return switch (fieldType.getSimpleName()) {
            case "Double" -> Double.valueOf(value);
            case "Integer" -> Integer.valueOf(value);
            case "Long" -> Long.valueOf(value);
            case "BigDecimal" -> new BigDecimal(value);
            case "Enum" -> Enum.valueOf(fieldType.asSubclass(Enum.class), value);
            default -> value;
        };
    }

    public Object castToRequiredType(Class<?> fieldType, List<String> value) {
        List<Object> lists = new ArrayList<>();
        for (String s : value) {
            lists.add(castToRequiredType(fieldType, s));
        }
        return lists;
    }
}

package com.anthinhphatjsc.ezisolutions.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Filter {
    private String field;
    private QueryOperator operator;
    private String value;
    private List<String> values;

    public static Filter of(String field, QueryOperator operator, String value) {
        return new Filter(field, operator, value, null);
    }

    public static Filter queryLike(String field, String value) {
        return new Filter(field, QueryOperator.LIKE, value, null);
    }

    public static Filter queryEquals(String field, List<String> value) {
        return new Filter(field, QueryOperator.EQUALS, null, value);
    }

    public static Filter query(String field, String value) {
        return new Filter(field, QueryOperator.EQUALS, value, null);
    }
    public static Filter queryIn(String field, List<String> values) {
        return new Filter(field, QueryOperator.IN, null, values);
    }

}

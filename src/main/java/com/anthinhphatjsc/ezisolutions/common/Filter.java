package com.anthinhphatjsc.ezisolutions.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    public static Filter query(String field, String value) {
        return new Filter(field, QueryOperator.EQUALS, value, null);
    }

}

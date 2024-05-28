package com.anthinhphatjsc.ezisolutions.utils;

import java.util.List;

public class ArrayUtil {
    public static <E> boolean isEmpty(List<E> list) {
        return list == null || list.isEmpty();
    }

    public static <E> boolean isNotEmpty(List<E> list) {
        return list != null && !list.isEmpty();
    }
}

package com.anthinhphatjsc.ezisolutions.core;

public interface BaseMapper<E extends BaseEntity, D extends BaseDTO, R extends BaseRequestDto> {

    D toDTO(E e);

    E toEntityFromRequest(R dto);

    E merge(E source,E target);


}

package com.anthinhphatjsc.ezisolutions.core;

import com.anthinhphatjsc.ezisolutions.exceptions.NotFoundEntityException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

public interface BaseRestController<E extends BaseEntity, D extends BaseDTO, R extends BaseRequestDto> {

    public BaseService<E> getService();

    public BaseMapper<E, D, R> getMapper();

    default String getErrorMessages(BindingResult bindingResult) {
        return String.join("\n", bindingResult
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toList());
    }

    default BaseResponse index(BaseRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return BaseResponse.badRequest(null).setMessage(getErrorMessages(bindingResult));
        }
        if (request.isPaginationRequest()) {
            request.setPage(BigDecimal.valueOf(request.getPage()));
            Page<D> page = getService().paginate(request.getFilters(), request.getPageable()).map(
                    e -> getMapper().toDTO(e)
            );
            return BaseResponse.success(new BasePagination<>(page));
        } else {
            return BaseResponse.success(getService().get(request.getFilters()).stream().map(e -> getMapper().toDTO(e)).toList());
        }
    }

    default BaseResponse show(Long id) {
        try {
            E e = getService().getById(id);
            return BaseResponse.success(getMapper().toDTO(e));
        } catch (NotFoundEntityException ex) {
            return BaseResponse.throwException(ex);
        }
    }


    default BaseResponse create(R dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return BaseResponse.badRequest(null).setMessage(getErrorMessages(bindingResult));
        }
        E e = getMapper().toEntityFromRequest(dto);
        return BaseResponse.created(getService().create(e));
    }


    default BaseResponse update(R dto, Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return BaseResponse.badRequest(null).setMessage(getErrorMessages(bindingResult));
        }
        try {
            E entity = getService().getById(id);
            entity = getMapper().merge(getMapper().toEntityFromRequest(dto), entity);
            entity = getService().update(entity);
            return BaseResponse.created(entity);
        } catch (NotFoundEntityException ex) {
            return BaseResponse.throwException(ex);
        }
    }

    default BaseResponse deleteById(@PathVariable(value = "id") Long id) {
        try {
            getService().deleteById(id);
            return BaseResponse.noContent();
        } catch (NotFoundEntityException ex) {
            return BaseResponse.throwException(ex);
        }
    }

}

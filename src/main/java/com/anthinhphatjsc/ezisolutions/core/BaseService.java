package com.anthinhphatjsc.ezisolutions.core;

import com.anthinhphatjsc.ezisolutions.common.Filter;
import com.anthinhphatjsc.ezisolutions.exceptions.NotFoundEntityException;
import com.anthinhphatjsc.ezisolutions.utils.ArrayUtil;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BaseService<E extends BaseEntity> {

    BaseRepository<E> getRepository();

    @Transactional
    default Page<E> paginate(List<Filter> filters, Pageable pageable) {
        Page<E> page;
        if (ArrayUtil.isEmpty(filters)) {
            page = getRepository().findAll(pageable);
        } else {
            page = getRepository().findAll(BaseSpecifications.getSpecification(filters), pageable);
        }
        return page;
    }

    @Transactional
    default List<E> get(List<Filter> filters) {
        List<E> list;
        if (ArrayUtil.isEmpty(filters)) {
            list = getAll();
        } else {
            list = getRepository().findAll(BaseSpecifications.getSpecification(filters));
        }
        return list;
    }

    @Transactional
    default E find(List<Filter> filters) {
        Optional<E> optional;
        if (ArrayUtil.isEmpty(filters)) {
            optional = getRepository().findOne(BaseSpecifications.ofEmpty());
        } else {
            optional = getRepository().findOne(BaseSpecifications.getSpecification(filters));
        }
        return optional.orElse(null);
    }

    @Transactional
    default List<E> getAll() {
        return getRepository().findAll();
    }


    default E create(E e) {
        return getRepository().save(e);
    }

    default E update(E e) throws NotFoundEntityException {
        if (!getRepository().existsById(e.getId())) {
            throw new NotFoundEntityException("Bản ghi", e.getId());
        } else {
            return getRepository().save(e);
        }
    }

    default E getById(Long id) throws NotFoundEntityException {
        Optional<E> entityOptional = getRepository().findById(id);
        if (entityOptional.isEmpty()) {
            throw new NotFoundEntityException("Bản ghi", id);
        } else {
            return entityOptional.get();
        }
    }

    default void deleteById(Long id) throws NotFoundEntityException {
        Optional<E> entityOptional = getRepository().findById(id);
        if (entityOptional.isEmpty()) {
            throw new NotFoundEntityException("Bản ghi", id);
        } else {
            getRepository().deleteById(id);
        }
    }

    default List<Filter> listOf(Filter filter) {
        return List.of(new Filter[]{filter});
    }

}

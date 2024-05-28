package com.anthinhphatjsc.ezisolutions.controllers.admin;

import com.anthinhphatjsc.ezisolutions.common.Filter;
import com.anthinhphatjsc.ezisolutions.core.BaseDTO;
import com.anthinhphatjsc.ezisolutions.core.BaseResponse;
import com.anthinhphatjsc.ezisolutions.core.BaseRestController;
import com.anthinhphatjsc.ezisolutions.dtos.RoleDTO;
import com.anthinhphatjsc.ezisolutions.entities.RoleEntity;
import com.anthinhphatjsc.ezisolutions.mappers.RoleMapper;
import com.anthinhphatjsc.ezisolutions.requests.RequestDtoRole;
import com.anthinhphatjsc.ezisolutions.requests.RequestDtoUser;
import com.anthinhphatjsc.ezisolutions.requests.RoleSearchFormRequest;
import com.anthinhphatjsc.ezisolutions.services.IRoleService;
import com.anthinhphatjsc.ezisolutions.utils.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = Constants.REQUEST_MAPPING_PREFIX + Constants.ADMIN_MODULE_PREFIX + Constants.VERSION_API_V1 + "/roles")
public class RoleController implements BaseRestController<RoleEntity, RoleDTO, RequestDtoRole> {

    @Autowired
    IRoleService service;

    @Autowired
    RoleMapper mapper;


    @Override
    public IRoleService getService() {
        return service;
    }

    @Override
    public RoleMapper getMapper() {
        return mapper;
    }

    @GetMapping(path = "")
    public BaseResponse index(@Valid RoleSearchFormRequest request, BindingResult result) {
        request.addFilter(Filter.queryLike("name", request.getSearchName()));
        return BaseRestController.super.index(request, result);
    }

    @GetMapping(path = "/{id}")
    public BaseResponse show(@PathVariable("id") Long id) {
        return BaseRestController.super.show(id);
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse create(@RequestBody RequestDtoRole dto, BindingResult result) {
        return BaseRestController.super.create(dto, result);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse update(@RequestBody RequestDtoRole dto, @PathVariable("id") Long id, BindingResult result) {
        return BaseRestController.super.update(dto, id, result);
    }

    @DeleteMapping(path = "/{id}")
    public BaseResponse deleteById(@PathVariable("id") Long id) {
        return BaseRestController.super.deleteById(id);
    }
}

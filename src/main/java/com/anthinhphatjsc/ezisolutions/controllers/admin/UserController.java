package com.anthinhphatjsc.ezisolutions.controllers.admin;

import com.anthinhphatjsc.ezisolutions.common.Filter;
import com.anthinhphatjsc.ezisolutions.core.BaseResponse;
import com.anthinhphatjsc.ezisolutions.core.BaseRestController;
import com.anthinhphatjsc.ezisolutions.dtos.UserDTO;
import com.anthinhphatjsc.ezisolutions.entities.UserEntity;
import com.anthinhphatjsc.ezisolutions.mappers.UserMapper;
import com.anthinhphatjsc.ezisolutions.requests.RequestDtoUser;
import com.anthinhphatjsc.ezisolutions.requests.UserSearchFormRequest;
import com.anthinhphatjsc.ezisolutions.services.IUserService;
import com.anthinhphatjsc.ezisolutions.utils.AuthUtil;
import com.anthinhphatjsc.ezisolutions.utils.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Validated
@RequestMapping(value = Constants.REQUEST_MAPPING_PREFIX + Constants.ADMIN_MODULE_PREFIX + Constants.VERSION_API_V1 + "/users")
public class UserController implements BaseRestController<UserEntity, UserDTO, RequestDtoUser> {

    @Autowired
    IUserService service;

    @Autowired
    UserMapper mapper;


    @Override
    public IUserService getService() {
        return service;
    }


    @Override
    public UserMapper getMapper() {
        return mapper;
    }


    @GetMapping(path = "")
    public BaseResponse index(@Valid UserSearchFormRequest request, BindingResult result) {
        request.addFilter(Filter.queryLike("name", request.getSearchName()));
        return BaseRestController.super.index(request, result);
    }

    @GetMapping(path = "/{id}")
    public BaseResponse show(@PathVariable("id") Long id) {
        return BaseRestController.super.show(id);
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse create(@RequestBody RequestDtoUser dto, BindingResult result) {
        return BaseRestController.super.create(dto, result);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse update(@RequestBody RequestDtoUser dto, @PathVariable("id") Long id, BindingResult result) {
        return BaseRestController.super.update(dto, id, result);
    }

    @DeleteMapping(path = "/{id}")
    public BaseResponse deleteById(@PathVariable("id") Long id) {
        return BaseRestController.super.deleteById(id);
    }

}

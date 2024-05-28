package com.anthinhphatjsc.ezisolutions.controllers.passport;


import com.anthinhphatjsc.ezisolutions.core.BaseResponse;
import com.anthinhphatjsc.ezisolutions.dtos.UserLoginDTO;
import com.anthinhphatjsc.ezisolutions.entities.UserEntity;
import com.anthinhphatjsc.ezisolutions.mappers.UserMapper;
import com.anthinhphatjsc.ezisolutions.responses.LoginResponse;
import com.anthinhphatjsc.ezisolutions.services.IUserService;
import com.anthinhphatjsc.ezisolutions.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Constants.REQUEST_MAPPING_PREFIX + Constants.PASSPORT_MODULE_PREFIX + Constants.VERSION_API_V1 + "/login")
public class LoginController {

    private static final Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    IUserService service;

    @Autowired
    UserMapper mapper;

    @PostMapping(path = "", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseResponse login(@RequestBody UserLoginDTO userDTO) {
        UserEntity userEntity = UserEntity.builder().username(userDTO.getUsername()).password(userDTO.getPassword()).build();
        userEntity = service.verify(userEntity);
        if (userEntity != null) {
            return BaseResponse.success(LoginResponse.builder().name(userEntity.getName()).rememberToken(userEntity.getRememberToken()).build());
        }
        return BaseResponse.unauthorized("Tài khoản hoặc mật khẩu không đúng");
    }

}

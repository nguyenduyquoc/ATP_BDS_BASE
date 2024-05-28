package com.anthinhphatjsc.ezisolutions.requests;

import com.anthinhphatjsc.ezisolutions.core.BaseDTO;
import com.anthinhphatjsc.ezisolutions.core.BaseRequestDto;
import com.anthinhphatjsc.ezisolutions.validate.RequestCreateUserValidation;
import com.anthinhphatjsc.ezisolutions.validate.RequestUpdateUserValidation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
public class RequestDtoUser extends BaseRequestDto {
    @NotBlank(message = "name không được để trống", groups = {RequestCreateUserValidation.class, RequestUpdateUserValidation.class})
    String name;
    @NotBlank(message = "username không được để trống", groups = {RequestCreateUserValidation.class, RequestUpdateUserValidation.class})
    String username;
    @Email(message = "email nhập không đúng định dạng", groups = {RequestCreateUserValidation.class, RequestUpdateUserValidation.class})
    String email;
    @NotBlank(message = "Mật khẩu không được để trống", groups = {RequestCreateUserValidation.class})
    @Length(min = 4, message = "Mật khẩu không được ít hơn 4 ký tự", groups = {RequestCreateUserValidation.class})
    @Length(max = 100, message = "Mật khẩu không được vượt quá 100 ký tự", groups = {RequestCreateUserValidation.class})
    String password;
    List<Long> roles;
}

package com.anthinhphatjsc.ezisolutions.requests;

import com.anthinhphatjsc.ezisolutions.core.BaseDTO;
import com.anthinhphatjsc.ezisolutions.core.BaseRequestDto;
import com.anthinhphatjsc.ezisolutions.validate.RequestCreateUserValidation;
import com.anthinhphatjsc.ezisolutions.validate.RequestUpdateUserValidation;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
public class RequestDtoRole extends BaseRequestDto {

    String name;

    String code;

    Long priority;
}

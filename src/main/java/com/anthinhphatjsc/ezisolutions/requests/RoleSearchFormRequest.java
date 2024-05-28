package com.anthinhphatjsc.ezisolutions.requests;

import com.anthinhphatjsc.ezisolutions.core.BaseRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleSearchFormRequest extends BaseRequest {

//    @NotBlank(message = "name không được để trống")
    protected String searchName;

}

package com.anthinhphatjsc.ezisolutions.dtos;

import com.anthinhphatjsc.ezisolutions.core.BaseDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginDTO extends BaseDTO {
    private String username;
    private String password;
}
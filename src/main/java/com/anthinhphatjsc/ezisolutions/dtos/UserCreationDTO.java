package com.anthinhphatjsc.ezisolutions.dtos;

import com.anthinhphatjsc.ezisolutions.core.BaseDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreationDTO extends BaseDTO {
    private String name;
    private String username;
    private String password;
    private List<String> roles;
}

package com.anthinhphatjsc.ezisolutions.dtos;

import com.anthinhphatjsc.ezisolutions.core.BaseDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO extends BaseDTO {
    Long id;
    String name;
    String username;
    List<RoleDTO> roles;
}

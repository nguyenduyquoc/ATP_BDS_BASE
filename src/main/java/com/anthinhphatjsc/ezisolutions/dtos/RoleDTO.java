package com.anthinhphatjsc.ezisolutions.dtos;

import com.anthinhphatjsc.ezisolutions.core.BaseDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO extends BaseDTO {
    Long id;
    String name;
    String code;
    Long priority;
}

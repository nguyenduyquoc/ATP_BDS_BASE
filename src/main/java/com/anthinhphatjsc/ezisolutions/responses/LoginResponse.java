package com.anthinhphatjsc.ezisolutions.responses;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LoginResponse {
    String name;
    String rememberToken;
}

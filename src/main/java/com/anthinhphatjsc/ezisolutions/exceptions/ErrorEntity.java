package com.anthinhphatjsc.ezisolutions.exceptions;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ErrorEntity {
    private String code;
    private String valueError;
    private String message;
}

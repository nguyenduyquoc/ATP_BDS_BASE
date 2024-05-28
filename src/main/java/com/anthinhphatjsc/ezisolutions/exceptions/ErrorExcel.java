package com.anthinhphatjsc.ezisolutions.exceptions;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ErrorExcel {
    String message;
    Integer countCritBug;
    Integer countBug;
}

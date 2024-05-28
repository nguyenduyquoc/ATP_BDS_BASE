package com.anthinhphatjsc.ezisolutions.requests;

import com.anthinhphatjsc.ezisolutions.core.BaseRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankSearchFormRequest extends BaseRequest {
    protected String searchName;

}

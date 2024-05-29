package com.anthinhphatjsc.ezisolutions.requests;

import com.anthinhphatjsc.ezisolutions.core.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserSearchFormRequest extends BaseRequest {
    protected String name;
    protected String username;
//    protected String email;
//    protected List<Long> roleIds;

    protected String roleIds;
}

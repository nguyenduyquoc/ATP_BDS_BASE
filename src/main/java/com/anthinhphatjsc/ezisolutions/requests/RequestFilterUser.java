package com.anthinhphatjsc.ezisolutions.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestFilterUser {
    String name;
    String username;
    String email;
    String roles;
    public RequestFilterUser(){
        this.name = null;
        this.username = null;
        this.email = null;
        this.roles = null;
    }
}

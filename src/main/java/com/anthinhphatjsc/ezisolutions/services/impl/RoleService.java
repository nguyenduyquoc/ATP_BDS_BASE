package com.anthinhphatjsc.ezisolutions.services.impl;

import com.anthinhphatjsc.ezisolutions.repositories.RoleRepositoryImpl;
import com.anthinhphatjsc.ezisolutions.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {

    @Autowired
    RoleRepositoryImpl repository;

    @Override
    public RoleRepositoryImpl getRepository() {
        return repository;
    }

}

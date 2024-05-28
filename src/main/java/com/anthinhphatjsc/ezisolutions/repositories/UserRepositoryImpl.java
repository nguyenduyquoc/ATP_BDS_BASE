package com.anthinhphatjsc.ezisolutions.repositories;

import com.anthinhphatjsc.ezisolutions.core.BaseRepository;
import com.anthinhphatjsc.ezisolutions.entities.UserEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface UserRepositoryImpl extends BaseRepository<UserEntity> {
}
package com.anthinhphatjsc.ezisolutions.responses;

import com.anthinhphatjsc.ezisolutions.entities.UserEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResponseDtoUser {
    Long id;
    String name;
    String username;
    String password;
    String rememberToken;
    List<Long> roleAble;
    List<Long> roleNotAble;
    LocalDateTime createAt;
    LocalDateTime updateAt;

    public ResponseDtoUser(UserEntity userEntity, List<Long> roleAble, List<Long> roleNotAble){
        this.id = userEntity.getId();
        this.name = userEntity.getName();
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
        this.rememberToken = userEntity.getRememberToken();
        this.roleAble = roleAble;
        this.roleNotAble = roleNotAble;
        this.createAt = userEntity.getCreatedAt();
        this.updateAt = userEntity.getUpdatedAt();
    }
}

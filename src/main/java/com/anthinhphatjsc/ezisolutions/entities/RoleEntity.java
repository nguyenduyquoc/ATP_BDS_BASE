package com.anthinhphatjsc.ezisolutions.entities;

import com.anthinhphatjsc.ezisolutions.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class RoleEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "code", nullable = false)
    String code;

    @Column(name = "priority", nullable = false)
    Long priority;

}

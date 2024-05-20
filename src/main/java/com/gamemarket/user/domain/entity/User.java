package com.gamemarket.user.domain.entity;

import com.gamemarket.common.entity.BaseEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class User extends BaseEntity {

    private Long id;
    private String email;
    private String nickname;
    private String password;
    private Boolean status;

}

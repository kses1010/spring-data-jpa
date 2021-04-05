package com.inflearn.jpaactive2.dto.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class CreateMemberRequest {

    private Long id;
    private String username;

    public CreateMemberRequest(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}

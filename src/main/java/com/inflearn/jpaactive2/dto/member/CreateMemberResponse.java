package com.inflearn.jpaactive2.dto.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class CreateMemberResponse {

    private Long id;

    public CreateMemberResponse(Long id) {
        this.id = id;
    }
}

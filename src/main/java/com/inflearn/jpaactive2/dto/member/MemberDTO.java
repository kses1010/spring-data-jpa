package com.inflearn.jpaactive2.dto.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class MemberDTO {

    private String name;

    public MemberDTO(String name) {
        this.name = name;
    }
}

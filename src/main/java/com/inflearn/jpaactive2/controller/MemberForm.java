package com.inflearn.jpaactive2.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class MemberForm {

    @NotEmpty(message = "회원이름은 필수")
    private String name;

    private String city;

    private String street;

    private String zipcode;

}

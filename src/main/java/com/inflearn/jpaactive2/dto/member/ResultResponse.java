package com.inflearn.jpaactive2.dto.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ResultResponse<T> {

    private T data;

    public ResultResponse(T data) {
        this.data = data;
    }
}

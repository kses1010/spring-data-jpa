package com.inflearn.jpaactive2.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Setter
@Getter
@Entity
@DiscriminatorValue("book")
public class Book extends Item {

    private String author;
    private String isbn;
}

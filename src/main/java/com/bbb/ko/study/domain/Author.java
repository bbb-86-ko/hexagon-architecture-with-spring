package com.bbb.ko.study.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
public class Author {
    @Id
    private Long id;
    private String name;
}

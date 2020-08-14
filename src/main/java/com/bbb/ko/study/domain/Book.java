package com.bbb.ko.study.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
public class Book {
    @Id
    private Long id;
    private String title;
    private Long authorId;

    public Book(Long id, String title, Long authorId) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
    }
}

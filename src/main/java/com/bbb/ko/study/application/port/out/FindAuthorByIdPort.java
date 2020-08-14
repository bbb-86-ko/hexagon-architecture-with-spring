package com.bbb.ko.study.application.port.out;

import com.bbb.ko.study.common.OutputAdapter;
import com.bbb.ko.study.domain.Author;

@OutputAdapter
public interface FindAuthorByIdPort {
    Author findAuthorById(Long authorId) throws AuthorNotFoundException;

    class AuthorNotFoundException extends RuntimeException {
        public AuthorNotFoundException(Long authorId) {
            super(String.format("Author with ID '%d' does not exist!", authorId));
        }
    }
}

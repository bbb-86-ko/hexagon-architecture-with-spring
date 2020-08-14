package com.bbb.ko.study.application.port.out;

import com.bbb.ko.study.common.OutputAdapter;
import com.bbb.ko.study.domain.Book;

import java.util.Optional;

@OutputAdapter
public interface FindBookByTitlePort {

    Optional<Book> findBookByTitle(String title);

    class AuthorNotFoundException extends RuntimeException {
        public AuthorNotFoundException(Long authorId) {
            super(String.format("Author with ID '%d' does not exist!", authorId));
        }
    }
}

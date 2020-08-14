package com.bbb.ko.study.adapter.persistence;

import com.bbb.ko.study.application.port.out.FindAuthorByIdPort;
import com.bbb.ko.study.application.port.out.FindBookByTitlePort;
import com.bbb.ko.study.domain.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorPersistenceAdapter implements FindAuthorByIdPort {

    private final AuthorRepository authorRepository;

    public AuthorPersistenceAdapter(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author findAuthorById(Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new FindBookByTitlePort.AuthorNotFoundException(authorId));
    }
}

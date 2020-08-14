package com.bbb.ko.study.adapter.persistence;

import com.bbb.ko.study.application.port.out.FindBookByTitlePort;
import com.bbb.ko.study.application.port.out.PersistBookPort;
import com.bbb.ko.study.common.PersistenceAdapter;
import com.bbb.ko.study.domain.Book;

import java.util.Optional;

@PersistenceAdapter
class BookPersistenceAdapter implements FindBookByTitlePort, PersistBookPort {

    private final BookRepository bookRepository;

    BookPersistenceAdapter(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Optional<Book> findBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
}

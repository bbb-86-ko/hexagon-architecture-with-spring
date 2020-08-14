package com.bbb.ko.study.application.service;

import com.bbb.ko.study.application.port.in.RegisterBookUseCase;
import com.bbb.ko.study.application.port.out.FindAuthorByIdPort;
import com.bbb.ko.study.application.port.out.FindBookByTitlePort;
import com.bbb.ko.study.application.port.out.PersistBookPort;
import com.bbb.ko.study.common.UseCase;
import com.bbb.ko.study.domain.Author;
import com.bbb.ko.study.domain.Book;

@UseCase
public class RegisterBookService implements RegisterBookUseCase {

    private final FindAuthorByIdPort findAuthorByIdPort;
    private final FindBookByTitlePort findBookByTitlePort;
    private final PersistBookPort saveBookPort;

    public RegisterBookService(FindAuthorByIdPort findAuthorByIdPort, FindBookByTitlePort findBookByTitlePort, PersistBookPort saveBookPort) {
        this.findAuthorByIdPort = findAuthorByIdPort;
        this.findBookByTitlePort = findBookByTitlePort;
        this.saveBookPort = saveBookPort;
    }

    @Override
    public void registerBook(RegisterBookCommand command) {

        Author author = findAuthorByIdPort.findAuthorById(command.getAuthorId());

        requireUniqueTitle(command.getBookTitle());
        requireAuthorHasPremiumAccount(author);

        // more business validations ...

        Book book = new Book(null, command.getBookTitle(), author.getId());
        saveBookPort.saveBook(book);
    }

    private void requireAuthorHasPremiumAccount(Author author) {
        // some business validation ...
    }

    private void requireUniqueTitle(String bookTitle) {
        if (findBookByTitlePort.findBookByTitle(bookTitle).isPresent()) {
            throw new NonUniqueBookTitleException(bookTitle);
        }
    }

}

package com.bbb.ko.study.adapter.web;

import com.bbb.ko.study.application.port.in.RegisterBookUseCase;
import com.bbb.ko.study.application.port.in.RegisterBookUseCase.RegisterBookCommand;
import com.bbb.ko.study.domain.Book;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class RegisterBookController {

    private final RegisterBookUseCase registerBookUseCase;

    RegisterBookController(RegisterBookUseCase registerBookUseCase) {
        this.registerBookUseCase = registerBookUseCase;
    }

    @PostMapping(path = "/books/register")
    void registerBook(@RequestBody Book book) {
        RegisterBookCommand command = new RegisterBookCommand(book.getTitle(), book.getAuthorId());
        registerBookUseCase.registerBook(command);
    }

}

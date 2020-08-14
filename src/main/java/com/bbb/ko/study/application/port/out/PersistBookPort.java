package com.bbb.ko.study.application.port.out;

import com.bbb.ko.study.common.OutputAdapter;
import com.bbb.ko.study.domain.Book;

@OutputAdapter
public interface PersistBookPort {
    Book saveBook(Book book);
}

package com.bbb.ko.study.adapter.persistence;

import com.bbb.ko.study.domain.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {

    @Query("select b.* from Book b where b.title = :title")
    Optional<Book> findByTitle(@Param("title") String title);

}

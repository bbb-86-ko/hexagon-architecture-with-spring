package com.bbb.ko.study.adapter.persistence;

import com.bbb.ko.study.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}

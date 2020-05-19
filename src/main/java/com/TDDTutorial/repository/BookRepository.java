package com.TDDTutorial.repository;

import com.TDDTutorial.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityExistsException;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
//    default Book findByIdOrError(Integer id) {
//        return findById(id).orElseThrow(EntityExistsException::new);
//    }
}

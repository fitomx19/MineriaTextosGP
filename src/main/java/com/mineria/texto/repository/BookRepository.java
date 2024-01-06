package com.mineria.texto.repository;

import com.mineria.texto.entities.Book;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BookRepository extends MongoRepository<Book, String> {
    @Query("{ $text: { $search: ?0 } }")
    List<Book> searchByContent(String searchText);
}

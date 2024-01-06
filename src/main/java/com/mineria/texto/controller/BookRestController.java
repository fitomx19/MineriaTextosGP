package com.mineria.texto.controller;

import com.mineria.texto.entities.Book;
import com.mineria.texto.repository.BookRepository;
import com.mineria.texto.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;




@RestController
@RequestMapping("/book")
public class BookRestController {

    private final BookService bookService;

    @Autowired
    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    BookRepository bookRepository;

    @GetMapping()
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> get(@PathVariable String id) {
        return bookRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<String> searchByContent(@RequestBody Map<String, String> requestBody) {
        String searchText = requestBody.get("searchText");
        return bookService.searchByContentAndGetNames(searchText);
    }
    
    @GetMapping("/countOccurrences")
    public Map<String, Integer> countOccurrencesInBooks(@RequestBody Map<String, String> requestBody) {
        String searchText = requestBody.get("searchText");
        return bookService.countOccurrencesInBooks(searchText);
    }
    
    @GetMapping("/searchByTFIDF")
    public Map<String, Object> searchByTFIDF(@RequestBody Map<String, String> requestBody) {
        String searchText = requestBody.get("searchText");
        return bookService.searchByTFIDF(searchText);
    }

    @PostMapping
    public ResponseEntity<Book> post(@RequestBody Book input) {
        System.out.println(input);
        Book savedBook = bookRepository.save(input);
        return ResponseEntity.ok(savedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return bookRepository.findById(id)
                .map(book -> {
                    bookRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

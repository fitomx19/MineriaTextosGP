package com.mineria.texto.services;

import com.mineria.texto.entities.Book;
import com.mineria.texto.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<String> searchByContentAndGetNames(String searchText) {
        List<Book> allBooks = bookRepository.findAll();
        List<String> bookNames = allBooks.stream()
                .filter(book -> containsSearchText(book.getContenido(), searchText))
                .map(Book::getName)
                .collect(Collectors.toList());
        return bookNames;
    }

    public Map<String, Integer> countOccurrencesInBooks(String searchText) {
        List<Book> allBooks = bookRepository.findAll();
        Map<String, Integer> occurrencesMap = new HashMap<>();

        for (Book book : allBooks) {
            String content = book.getContenido().toLowerCase();
            int occurrences = countOccurrences(content, searchText.toLowerCase());
            occurrencesMap.put(book.getName(), occurrences);
        }

        return occurrencesMap;
    }

       public Map<String, Object> searchByTFIDF(String searchText) {
        long startTime = System.currentTimeMillis();

        List<Book> allBooks = bookRepository.findAll();

        // Calcular el TF-IDF para cada libro
        for (Book book : allBooks) {
            String content = book.getContenido().toLowerCase();
            double tfidfScore = calculateTFIDF(content, searchText.toLowerCase(), allBooks);
            book.setTFIDFScore(tfidfScore);
        }

        // Ordenar los resultados por relevancia (mayor a menor)
        List<Map<String, Object>> result = allBooks.stream()
                .sorted(Comparator.comparingDouble(Book::getTFIDFScore).reversed())
                .map(book -> {
                    Map<String, Object> bookInfo = new HashMap<>();
                    bookInfo.put("name", book.getName());
                    bookInfo.put("author", book.getAuthor());
                    bookInfo.put("year", book.getYear());
                    bookInfo.put("num_palabras", book.getNum_Palabras());
                    bookInfo.put("tfidfScore", book.getTFIDFScore());
                    return bookInfo;
                })
                .collect(Collectors.toList());

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Tiempo de respuesta: " + executionTime + " ms");
        
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("books", result);
        responseMap.put("executionTime", executionTime);
        
        return responseMap;

    }
    
       
       
       private double calculateTFIDF(String content, String searchText, List<Book> allBooks) {
        // Calcular el TF (Frecuencia de Término)
        double tf = (double) countOccurrences(content, searchText) / content.split("\\s+").length;

        // Calcular el IDF (Frecuencia Inversa de Documento)
        double idf = calculateIDF(searchText, allBooks);

        // Calcular el TF-IDF
        return tf * idf;
    }

    private double calculateIDF(String searchText, List<Book> allBooks) {
        // Calcular la cantidad de documentos que contienen el término de búsqueda
        long documentsWithTerm = allBooks.stream()
                .filter(book -> containsSearchText(book.getContenido(), searchText))
                .count();

        // Evitar la división por cero
        if (documentsWithTerm == 0) {
            return 0.0;
        }

        // Calcular IDF
        return Math.log((double) allBooks.size() / documentsWithTerm);
    }

    private boolean containsSearchText(String content, String searchText) {
        return content.toLowerCase().contains(searchText.toLowerCase());
    }

    private int countOccurrences(String content, String searchText) {
        int count = 0;
        int lastIndex = 0;

        while (lastIndex != -1) {
            lastIndex = content.indexOf(searchText, lastIndex);

            if (lastIndex != -1) {
                count++;
                lastIndex += searchText.length();
            }
        }

        return count;
    }
}

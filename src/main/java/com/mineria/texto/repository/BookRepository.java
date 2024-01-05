/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mineria.texto.repository;
 
import com.mineria.texto.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author Alex
 */
public interface BookRepository extends JpaRepository<Book,Long> {
    
}

package com.mineria.texto.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
public class Book {
    @Id
    private String id;
    private String name;
    private String author;
    private int year;
    private int num_Palabras;
    @TextIndexed
    private String contenido;
    private double tfidFScore;

  

    // Constructor vacío necesario para MongoDB
    public Book() {
    }

    // Constructor con parámetros para facilitar la creación de instancias
    public Book(String name, String author, int year, int num_Palabras, String contenido) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.num_Palabras = num_Palabras;
        this.contenido = contenido;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    public int getNum_Palabras(){
        return num_Palabras;
    }
    
    public void setNum_Palabras(int num_palabras){
        this.num_Palabras = num_palabras;
    }
    public String getContenido(){
        return contenido;
    }
    
    public void setContenido(String contenido){
        this.contenido = contenido;
    }
    
    public double getTFIDFScore() {
        return tfidFScore;
    }

    public void setTFIDFScore(double tfidFScore) {
        this.tfidFScore = tfidFScore;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year='" + year + '\'' +
                ", num_Palabras='" + num_Palabras + '\'' +
                ", contenido=" + (contenido.length() > 50 ? contenido.substring(0, 50) + "..." : contenido) +
                ", tfidFScore=" + tfidFScore +
                '}';
    }
}

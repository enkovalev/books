package org.example.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Книга
 */
@Getter
@Setter
public class Book {

    /**
     * Название
     */
    private String title;

    /**
     * Автор
     */
    private String author;

    /**
     * Описание
     */
    private String description;
}

package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * Книга
 */
@Getter
@Setter
public class Book {
    /**
     * Идентификатор
     */
    private int id;

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

    public int getStatistic(char symbol) {
        return StringUtils.countMatches(title.toLowerCase(), symbol) +
                StringUtils.countMatches(title.toUpperCase(), symbol);
    }
}

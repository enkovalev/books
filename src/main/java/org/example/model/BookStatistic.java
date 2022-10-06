package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Статистика по автору и символу
 */
@Getter
@Setter
@AllArgsConstructor
public class BookStatistic {
    /**
     * Автор
     */
    private String author;

    /**
     * Количество вхождений символа во все названия книг автора
     */
    private int amountSymbols;
}

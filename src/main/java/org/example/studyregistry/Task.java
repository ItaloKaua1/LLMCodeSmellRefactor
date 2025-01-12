package org.example.studyregistry;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task extends Registry {
    private final String title;
    private final String description;
    private final String author;
    private final LocalDateTime date;

    public Task(String title, String description, String author, LocalDateTime date) {
        validateTitle(title);
        validateDescription(description);
        validateAuthor(author);
        validateDate(date);

        this.title = title;
        this.name = title; // Supondo que 'name' é herdado de Registry
        this.description = description;
        this.author = author;
        this.date = date;
    }

    private void validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or blank");
        }
    }

    private void validateAuthor(String author) {
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be null or blank");
        }
    }

    private void validateDate(LocalDateTime date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
    }


    // Getters (removemos setters para garantir imutabilidade onde aplicável)
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getDate() {
        return date;
    }

    // Adicionando comportamento significativo

    /**
     * Verifica se a tarefa está atrasada.
     *
     * @return true se a data da tarefa for anterior ao momento atual, caso contrário, false.
     */
    public boolean isOverdue() {
        return date.isBefore(LocalDateTime.now());
    }

    /**
     * Formata a data da tarefa em um formato legível.
     *
     * @return Data formatada como String.
     */
    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return date.format(formatter);
    }

    /**
     * Retorna o status da tarefa.
     *
     * @return "Overdue" se a tarefa estiver atrasada, caso contrário, "On Schedule".
     */
    public String calculateStatus() {
        return isOverdue() ? "Overdue" : "On Schedule";
    }

    /**
     * Representação textual da tarefa.
     *
     * @return Uma String com os detalhes da tarefa.
     */
    @Override
    public String toString() {
        return String.format("Task: %s%nDescription: %s%nAuthor: %s%nDate: %s%nStatus: %s",
                title, description, author, getFormattedDate(), calculateStatus());
    }
}


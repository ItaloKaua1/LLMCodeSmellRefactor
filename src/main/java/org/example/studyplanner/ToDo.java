package org.example.studyplanner;

import java.text.MessageFormat;

public class ToDo implements PlannerMaterial {
    private Integer id;
    private String title;
    private String description;
    private int priority;
    private boolean completed;

    public ToDo(Integer id, String title, String description, int priority) {
        validateId(id);
        validateTitle(title);
        validateDescription(description);

        this.id = id;
        this.title = title;
        this.description = description;
        setPriority(priority);
        this.completed = false;
    }

    private void validateId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer.");
        }
    }

    private void validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank.");
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or blank.");
        }
    }

    // Adds behavior to mark a task as completed
    public void markAsCompleted() {
        if (completed) {
            throw new IllegalStateException("Task is already completed.");
        }
        this.completed = true;
    }

    // Adds behavior to reset task status
    public void resetStatus() {
        this.completed = false;
    }

    // Validates priority to ensure it is within a valid range
    public void setPriority(int priority) {
        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException("Priority must be between 1 and 5.");
        }
        this.priority = priority;
    }

    // Provides a more detailed summary of the task
    public String getSummary() {
        return MessageFormat.format(
                "Task ID: {0}\nTitle: {1}\nDescription: {2}\nPriority: {3}\nStatus: {4}",
                id, title, description, priority, completed ? "Completed" : "Pending"
        );
    }

    // Compares tasks based on priority (higher priority first)
    public int compareTo(ToDo other) {
        return Integer.compare(other.priority, this.priority);
    }

    @Override
    public String toString() {
        return MessageFormat.format(
                "[(Priority:{3}) ToDo {0}: {1}, {2}, Status: {4}]",
                id, title, description, priority, completed ? "Completed" : "Pending"
        );
    }

    // Getter and setter methods

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer.");
        }
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank.");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or blank.");
        }
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }
}

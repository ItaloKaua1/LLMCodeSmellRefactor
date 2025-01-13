package org.example.studyregistry;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;

public class StepDetails {
    private List<String> stringProperties;
    private Integer numberOfSteps;
    private boolean isImportant;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public StepDetails(List<String> stringProperties, Integer numberOfSteps, boolean isImportant, LocalDateTime startDate, LocalDateTime endDate) {
        this.stringProperties = stringProperties;
        this.numberOfSteps = numberOfSteps;
        this.isImportant = isImportant;
        this.startDate = startDate;
        this.endDate = endDate;
        validateDates();
    }

    // Encapsular lógica de validação
    private void validateDates() {
        if (startDate != null && endDate != null && endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }
    }

    // Lógica adicional: calcular a duração entre datas
    public long getDurationInMinutes() {
        if (startDate == null || endDate == null) {
            throw new IllegalStateException("Start date or end date is not set.");
        }
        return Duration.between(startDate, endDate).toMinutes();
    }

    // Lógica adicional: verificar se a tarefa é longa
    public boolean isLongStep() {
        return numberOfSteps != null && numberOfSteps > 10;
    }

    // Lógica adicional: verificar se a data atual está dentro do intervalo
    public boolean isCurrentStep() {
        LocalDateTime now = LocalDateTime.now();
        return startDate != null && endDate != null && !now.isBefore(startDate) && !now.isAfter(endDate);
    }

    // Getters
    public List<String> getStringProperties() {
        return stringProperties;
    }

    public Integer getNumberOfSteps() {
        return numberOfSteps;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}

package org.example.studyplanner;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class HabitDetails {
    private String name;
    private String motivation;
    private LocalTime dailyDedication;
    private LocalDateTime startDate;
    private Boolean isConcluded;

    public HabitDetails(String name, String motivation, LocalTime dailyDedication, LocalDateTime startDate) {
        if (dailyDedication == null || startDate == null || name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name, daily dedication, and start date cannot be null or empty.");
        }
        this.name = name;
        this.motivation = motivation;
        this.dailyDedication = dailyDedication;
        this.startDate = startDate;
        this.isConcluded = false; // Assume not concluded initially
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getMotivation() {
        return motivation;
    }

    public LocalTime getDailyDedication() {
        return dailyDedication;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public Boolean isConcluded() {
        return isConcluded;
    }

    // Additional behavior
    public long getDaysSinceStart() {
        return Duration.between(startDate, LocalDateTime.now()).toDays();
    }

    public void markAsConcluded() {
        if (isConcluded) {
            throw new IllegalStateException("Habit is already concluded.");
        }
        isConcluded = true;
    }

    public void updateMotivation(String newMotivation) {
        if (newMotivation == null || newMotivation.isEmpty()) {
            throw new IllegalArgumentException("Motivation cannot be null or empty.");
        }
        this.motivation = newMotivation;
    }

    public boolean isActive() {
        return !isConcluded;
    }

    public boolean isDailyGoalMet(LocalTime timeSpentToday) {
        return timeSpentToday.compareTo(dailyDedication) >= 0;
    }
}

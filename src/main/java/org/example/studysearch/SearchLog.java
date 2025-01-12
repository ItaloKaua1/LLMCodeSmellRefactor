package org.example.studysearch;

import org.example.studycards.CardManager;
import org.example.studyplanner.HabitTracker;
import org.example.studyplanner.TodoTracker;
import org.example.studyregistry.StudyTaskManager;

import java.util.*;

public class SearchLog {
    private final List<String> searchHistory;
    private final Map<String, Integer> searchCount;
    private boolean isLocked;
    private int numUsages;
    private final String logName;

    public SearchLog(String logName) {
        this.searchHistory = new ArrayList<>();
        this.searchCount = new HashMap<>();
        this.logName = logName;
        this.numUsages = 0;
        this.isLocked = false;
    }

    // Método para registrar uma busca
    public List<String> handleRegistrySearch(String text) {
        if (isLocked) {
            throw new IllegalStateException("SearchLog is locked and cannot process searches.");
        }

        List<String> results = new ArrayList<>();
        results.addAll(CardManager.getCardManager().searchInCards(text));
        results.addAll(HabitTracker.getHabitTracker().searchInHabits(text));
        results.addAll(TodoTracker.getInstance().searchInTodos(text));
        results.addAll(StudyTaskManager.getStudyTaskManager().searchInRegistries(text));

        logSearch(text);
        results.add("\nLogged in: " + logName);
        return results;
    }

    // Método para adicionar e contabilizar buscas
    private void logSearch(String searchText) {
        searchHistory.add(searchText);
        searchCount.put(searchText, searchCount.getOrDefault(searchText, 0) + 1);
        numUsages++;
    }

    // Método para recuperar histórico de buscas
    public List<String> getSearchHistory() {
        return Collections.unmodifiableList(searchHistory);
    }

    // Método para obter a contagem de buscas por termo
    public int getSearchCount(String searchText) {
        return searchCount.getOrDefault(searchText, 0);
    }

    // Métodos para status de bloqueio
    public boolean isLocked() {
        return isLocked;
    }

    public void lock() {
        isLocked = true;
    }

    public void unlock() {
        isLocked = false;
    }

    // Método para obter o número total de buscas realizadas
    public int getTotalUsages() {
        return numUsages;
    }

    // Método para obter o nome do log
    public String getLogName() {
        return logName;
    }
}




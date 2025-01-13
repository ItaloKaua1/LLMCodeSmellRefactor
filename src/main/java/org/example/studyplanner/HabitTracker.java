package org.example.studyplanner;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HabitTracker {
    private List<Habit> habits;
    private Map<Integer, List<LocalDateTime>> tracker;
    private Integer nextId;

    private static HabitTracker instance;

    public static HabitTracker getHabitTracker() {
        if (instance == null) {
            instance = new HabitTracker();
        }
        return instance;
    }

    private HabitTracker(){
        this.habits = new ArrayList<>();
        this.tracker = new HashMap<>();
        this.nextId = 1;
    }

    public String viewAllHabitRecords() {
        StringBuilder response = new StringBuilder();
        for (Habit habit : habits) {
            response.append(formatHabitWithRecords(habit));
        }
        return response.toString();
    }

    private String formatHabitWithRecords(Habit habit) {
        StringBuilder habitRecord = new StringBuilder();
        habitRecord.append("[ Habit: ")
                .append(habit.getName())
                .append(". Records: ");

        List<LocalDateTime> records = tracker.get(habit.getId());
        habitRecord.append(formatHabitRecords(records));
        habitRecord.append(" ]");

        return habitRecord.toString();
    }

    private String formatHabitRecords(List<LocalDateTime> records) {
        StringBuilder formattedRecords = new StringBuilder();
        for (LocalDateTime record : records) {
            formattedRecords.append(formatHabitDate(record)).append(", ");
        }
        if (!records.isEmpty()) {
            // Remove a última vírgula e espaço adicionados
            formattedRecords.setLength(formattedRecords.length() - 2);
        }
        return formattedRecords.toString();
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();
        for (Habit habit : habits) {
            response.append(habit.toString()).append(", ");
        }
        return "Habits: " + response.toString();
    }

    public Habit getHabitById(Integer id){
        return this.habits.stream()
                .filter(habit -> Objects.equals(habit.getId(), id))
                .findFirst().orElse(null);
    }

    public List<Habit> getHabits() {
        return this.habits;
    }

    public String formatHabitDate(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return date.format(formatter);
    }

    public List<Integer> getTrackerKeys(){
        return this.tracker.keySet().stream().toList();
    }

    public int addHabit(HabitDetails habitDetails) {
        // Criação de uma instância de Habit diretamente a partir do HabitDetails
        Habit habit = new Habit(
                habitDetails.getName(),          // Nome do hábito
                habitDetails.getMotivation(),    // Motivação
                habitDetails.getDailyDedication(), // Dedicação diária
                this.nextId,                     // ID único
                habitDetails.getStartDate(),     // Data de início
                habitDetails.isConcluded()       // Estado de conclusão
        );

        // Adiciona o hábito à lista de hábitos
        this.habits.add(habit);

        // Configuração de acompanhamento
        this.tracker.put(nextId, new ArrayList<>());

        // Incrementa o ID para o próximo hábito
        int generatedId = nextId;
        this.nextId++;

        // Retorna o ID gerado
        return generatedId;
    }


    public int handleAddHabitAdapter(List<String> stringProperties, List<Integer> intProperties) {
        if (stringProperties == null || stringProperties.size() < 2) {
            throw new IllegalArgumentException("stringProperties must contain at least name and motivation.");
        }
        if (intProperties == null || intProperties.size() < 8) {
            throw new IllegalArgumentException("intProperties must contain at least 8 elements for time and date.");
        }

        // Cria a dedicação diária usando as propriedades inteiras
        LocalTime dailyDedication = LocalTime.of(intProperties.get(1), intProperties.get(0));

        // Cria a data de início usando as propriedades inteiras
        LocalDateTime startDate = LocalDateTime.of(
                intProperties.get(2), // Ano
                intProperties.get(3), // Mês
                intProperties.get(4), // Dia
                intProperties.get(5), // Hora
                intProperties.get(6), // Minuto
                intProperties.get(7)  // Segundo
        );

        // Cria uma nova instância de HabitDetails com validações já no construtor
        HabitDetails habitDetails = new HabitDetails(
                stringProperties.get(0), // Nome
                stringProperties.get(1), // Motivação
                dailyDedication,
                startDate
        );

        // Adiciona o hábito e retorna o ID gerado
        return addHabit(habitDetails);
    }



    public int addHabit(String name, String motivation) {

        Habit habit = new Habit(name, motivation, this.nextId);
        this.habits.add(habit);
        int response = nextId;
        this.tracker.put(nextId, new ArrayList<>());
        this.nextId++;
        return response;
    }

    public void addHabitRecord(Integer id){
        tracker.get(id).add(LocalDateTime.now());
    }

    public void toggleConcludeHabit(Integer id) {
        for (Habit habit : this.habits) {
            if (habit.getId().equals(id)) {
                habit.setIsConcluded(!habit.getIsConcluded());
            }
        }
    }

    public void removeHabit(Integer id) {
        this.habits.removeIf(habit -> habit.getId().equals(id));
        this.tracker.remove(id);
    }

    public List<LocalDateTime> getHabitRecords(Integer id) {
        return this.tracker.get(id);
    }

    public List<String> searchInHabits(String search){
        List<String> habits = new ArrayList<>();
        for (Habit habit : this.habits) {
            if (habit.getName().toLowerCase().contains(search.toLowerCase()) || habit.getMotivation().toLowerCase().contains(search.toLowerCase())) {
                habits.add(habit.toString());
            }
        }
        return habits;
    }

}

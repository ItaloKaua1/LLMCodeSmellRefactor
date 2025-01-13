package org.example.studyregistry;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyTaskManager {
    private static StudyTaskManager instance;
    private StudyMaterial studyMaterial = StudyMaterial.getStudyMaterial();
    List<Registry> registryList;
    List<String> weekResponsibilities = List.of();

    private StudyTaskManager(){
        this.registryList = new ArrayList<Registry>();
    }

    public static StudyTaskManager getStudyTaskManager(){
        if (instance == null) {
            instance = new StudyTaskManager();
        }
        return instance;
    }

    public List<String> getWeekResponsibilities() {
        return weekResponsibilities;
    }

    public void setUpWeek(WeekSetup weekSetup) {
        this.weekResponsibilities = new ArrayList<>();
        this.weekResponsibilities.addAll(Arrays.asList(
                weekSetup.getPlanName(),
                weekSetup.getObjectiveTitle(),
                weekSetup.getObjectiveDescription(),
                weekSetup.getMaterialTopic(),
                weekSetup.getMaterialFormat(),
                weekSetup.getGoal(),
                weekSetup.getReminderTitle(),
                weekSetup.getReminderDescription(),
                weekSetup.getMainTaskTitle(),
                weekSetup.getMainHabit(),
                weekSetup.getMainCardStudy()
        ));
    }


    public void handleSetUpWeek(List<String> stringProperties) {
        if (stringProperties == null || stringProperties.size() < 11) {
            throw new IllegalArgumentException("Insufficient properties to set up the week.");
        }

        // Criar os objetos de valor a partir das strings fornecidas
        Objective objective = new Objective(
                stringProperties.get(1),  // objectiveTitle
                stringProperties.get(2)   // objectiveDescription
        );

        Material material = new Material(
                stringProperties.get(3),  // materialTopic
                stringProperties.get(4)   // materialFormat
        );

        Reminder reminder = new Reminder(
                stringProperties.get(6),  // reminderTitle
                stringProperties.get(7)   // reminderDescription
        );

        Task task = new Task(
                stringProperties.get(8)   // mainTaskTitle
        );

        Habit habit = new Habit(
                stringProperties.get(9)   // mainHabit
        );

        // Construir o WeekSetup usando o Builder
        WeekSetup weekSetup = new WeekSetup.Builder()
                .setPlanName(stringProperties.get(0))  // planName
                .setGoal(stringProperties.get(5))      // goal
                .setObjective(objective)
                .setMaterial(material)
                .setReminder(reminder)
                .setTask(task)
                .setHabit(habit)
                .build();

        setUpWeek(weekSetup);
    }



    public void addRegistry(Registry registry){
        registryList.add(registry);
    }
    public void removeRegistry(Registry registry){
        registryList.remove(registry);
    }
    public List<Registry> getRegistryList(){
        return registryList;
    }

    public List<String> searchInRegistries(String text){
        List<String> response = new ArrayList<>();
        for(Registry registry : registryList){
            String mix = (registry.getName() != null ? registry.getName() : "");
            if (mix.toLowerCase().contains(text.toLowerCase())){
                response.add(registry.getName());
            }
        }
        return response;
    }

}

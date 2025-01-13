package org.example.studyregistry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyPlan extends Registry{
    private StudyObjective objective;
    private List<String> steps;

    public StudyPlan(String planName, StudyObjective objective, List<StudyMaterial> materials) {
        this.name = planName;
        this.objective = objective;
        this.steps = new ArrayList<>();
    }

    @Override
    public String toString(){
        return "Plan: " + name + ",\nObjective: " + objective.getDescription() + ",\nSteps: " + String.join(", ", steps);
    }

    public List<String> getSteps() {
        return steps;
    }

    public StudyObjective getObjective() {
        return objective;
    }

    public void assignObjective(StudyObjective objective) {
        this.objective = objective;
    }

    public void addSingleStep(String toAdd){
        steps.add(toAdd);
    }

    public void assignSteps(StepDetails stepDetails) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        this.steps = new ArrayList<>(Arrays.asList(
                stepDetails.getStringProperties().get(0),
                stepDetails.getStringProperties().get(1),
                stepDetails.getStringProperties().get(2),
                stepDetails.getStringProperties().get(3),
                stepDetails.getStringProperties().get(4),
                stepDetails.getStringProperties().get(5),
                stepDetails.getStringProperties().get(6),
                "Number of steps: " + stepDetails.getNumberOfSteps().toString(),
                "Is it important to you? " + stepDetails.isImportant(),
                stepDetails.getStartDate().format(formatter),
                stepDetails.getEndDate().format(formatter)
        ));
    }

    public void handleAssignSteps(StepDetails stepDetails) {
        assignSteps(stepDetails);
    }


}

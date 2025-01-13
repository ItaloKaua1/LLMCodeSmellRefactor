package org.example.controllers;

import org.example.studyplanner.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class StudyPlannerController {
    private Map<String, Runnable> actions = new HashMap<>();
    private static TodoTracker todoTracker = TodoTracker.getInstance();
    private static HabitTracker habitTracker = HabitTracker.getHabitTracker();
    private static KanbanView kanbanView = new KanbanView(habitTracker, todoTracker);
    private static TimelineView timelineView = new TimelineView();

    public StudyPlannerController() {
        initializePlannerOptions();
        initializeTodoMenuOptions();
        initializeHabitMenuOptions();
        initializeViewMenuOptions();
    }

    private void initializePlannerOptions() {
        actions.put("1", this::handleTodoInput);
        actions.put("2", this::handleHabitInput);
        actions.put("3", this::handleViewInput);
    }

    private void initializeTodoMenuOptions() {
        actions.put("11", this::handleAddTodo);
        actions.put("12", this::handleRemoveTodo);
        actions.put("13", this::handleViewTodo);
        actions.put("14", this::handleViewByPriority);
        actions.put("15", this::handleAddTodoExecution);
    }

    private void initializeHabitMenuOptions() {
        actions.put("21", this::handleAddHabit);
        actions.put("22", this::handleRemoveHabit);
        actions.put("23", this::handleViewHabits);
    }

    private void initializeViewMenuOptions() {
        actions.put("31", this::safeHandleViewKanban);
        actions.put("32", this::safeHandleViewTimeline);
    }

    private void safeHandleViewKanban() {
        safeExecute(this::handleViewKanban);
    }

    private void safeHandleViewTimeline() {
        safeExecute(this::handleViewTimeline);
    }

    private void safeExecute(Runnable action) {
        try {
            action.run();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private String getInput() {
        return MainController.getInput();
    }

    private void handleAddTodoExecution() {
        System.out.println("Type todo id to add a new practiced time");
        Integer id = Integer.parseInt(getInput());
        todoTracker.addToDoExecutionTime(id);
    }

    private String formatTodosByPriority(List<ToDo> todos) {
        return todos.toString();
    }

    private void handleViewByPriority() {
        List<ToDo> todos = todoTracker.sortTodosByPriority();
        System.out.println(formatTodosByPriority(todos));
    }

    private void handleRemoveTodo() {
        System.out.println("Type todo id to remove");
        Integer id = Integer.parseInt(getInput());
        todoTracker.removeToDo(id);
    }

    private void handleRemoveHabit() {
        System.out.println("Type habit id to remove");
        Integer id = Integer.parseInt(getInput());
        habitTracker.removeHabit(id);
    }

    private void handleAddTodo() {
        System.out.println("Type the todo: title, description, priority (number)");
        String title = getUserInput("Title");
        String description = getUserInput("Description");
        Integer priority = Integer.valueOf(getUserInput("Priority"));
        todoTracker.addToDo(title, description, priority);
    }

    private LocalDateTime parseStartDate() {
        int year = Integer.parseInt(getInput());
        int month = Integer.parseInt(getInput());
        int day = Integer.parseInt(getInput());
        int hour = Integer.parseInt(getInput());
        int minute = Integer.parseInt(getInput());
        int seconds = Integer.parseInt(getInput());
        return LocalDateTime.of(year, month, day, hour, minute, seconds);
    }

    private void handleAddHabit() {
        try {
            HabitDetails habitDetails = collectHabitDetails();
            addHabitToTracker(habitDetails);
            System.out.println("Habit successfully added!");
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred while adding the habit: " + e.getMessage());
        }
    }

    private HabitDetails collectHabitDetails() {
        System.out.println("Enter the following details separated by pressing Enter:");
        System.out.println("Name, Motivation, Daily Hours Dedication, Daily Minutes Dedication");

        String name = getUserInput("Name");
        String motivation = getUserInput("Motivation");
        int dailyHoursDedication = Integer.parseInt(getUserInput("Daily Hours Dedication"));
        int dailyMinutesDedication = Integer.parseInt(getUserInput("Daily Minutes Dedication"));

        LocalTime dailyDedication = LocalTime.of(dailyHoursDedication, dailyMinutesDedication);
        LocalDateTime startDate = parseStartDate();

        return new HabitDetails(name, motivation, dailyDedication, startDate);
    }

    private String getUserInput(String prompt) {
        System.out.print(prompt + ": ");
        return this.getInput().trim();
    }

    private void addHabitToTracker(HabitDetails habitDetails) {
        habitTracker.addHabit(habitDetails);
    }

    private String viewToDoHeader() {
        return "Todos and latest usages:";
    }

    private void handleViewTodo() {
        System.out.println(viewToDoHeader());
        System.out.println(todoTracker.toString());
    }

    private String viewHabitHeader() {
        return "Habits found: ";
    }

    private void handleViewHabits() {
        System.out.println(viewHabitHeader());
        System.out.println(habitTracker.toString());
    }

    private void handleTodoInput() {
        handleMenuOptions(this::toDoOptions);
    }

    private void handleHabitInput() {
        handleMenuOptions(this::habitOptions);
    }

    private void handleViewInput() {
        handleMenuOptions(this::viewOptions);
    }

    private void handleMenuOptions(Runnable optionsPrinter) {
        try {
            while (true) {
                optionsPrinter.run();
                String response = MainController.validateInput(actions);
                if (response == null) {
                    return;
                }
                actions.get(response).run();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleMethodHeader(String header) {
        System.out.println("---" + header + "---");
    }

    private void handleViewKanban() {
        handleMethodHeader("Kanban view: ");
        System.out.println(kanbanView.kanbanView());
    }

    private void handleViewTimeline() {
        handleMethodHeader("Timeline view: ");
        System.out.println(habitTracker.viewAllHabitRecords());
    }

    public void handlePlannerInput() {
        handleMenuOptions(this::controllerOptions);
    }

    public static void controllerOptions() {
        System.out.println("""
                0 - return
                1 - todo menu
                2 - habit menu
                3 - view menu
               """);
    }

    public static void toDoOptions() {
        System.out.println("""
                0 - return
                11 - add todo
                12 - remove todo
                13 - view todos
                14 - view by priority
                15 - add todo execution date (now)
               """);
    }

    public static void habitOptions() {
        System.out.println("""
                0 - return
                21 - add habit
                22 - remove habit
                23 - view habit
               """);
    }

    public static void viewOptions() {
        System.out.println("""
                0 - return
                31 - kanban view
                32 - timeline view
               """);
    }
}


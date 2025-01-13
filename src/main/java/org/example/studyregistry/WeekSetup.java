package org.example.studyregistry;

import java.util.Objects;

// Classe principal que compõe outras classes menores
public final class WeekSetup {
    private final String planName;
    private final Objective objective;
    private final Material material;
    private final Reminder reminder;
    private final Task task;
    private final Habit habit;

    public final class WeekSetup {
        private final String planName;
        private final Objective objective;
        private final Material material;
        private final Reminder reminder;
        private final Task task;
        private final Habit habit;

        private WeekSetup(Builder builder) {
            this.planName = Objects.requireNonNull(builder.planName, "Plan name cannot be null");
            this.objective = Objects.requireNonNull(builder.objective, "Objective cannot be null");
            this.material = Objects.requireNonNull(builder.material, "Material cannot be null");
            this.reminder = Objects.requireNonNull(builder.reminder, "Reminder cannot be null");
            this.task = Objects.requireNonNull(builder.task, "Task cannot be null");
            this.habit = Objects.requireNonNull(builder.habit, "Habit cannot be null");
        }

        // Getters
        public String getPlanName() {
            return planName;
        }

        public Objective getObjective() {
            return objective;
        }

        public Material getMaterial() {
            return material;
        }

        public Reminder getReminder() {
            return reminder;
        }

        public Task getTask() {
            return task;
        }

        public Habit getHabit() {
            return habit;
        }

        // Builder Pattern
        public static class Builder {
            private String planName;
            private Objective objective;
            private Material material;
            private Reminder reminder;
            private Task task;
            private Habit habit;

            public Builder setPlanName(String planName) {
                this.planName = planName;
                return this;
            }

            public Builder setObjective(Objective objective) {
                this.objective = objective;
                return this;
            }

            public Builder setMaterial(Material material) {
                this.material = material;
                return this;
            }

            public Builder setReminder(Reminder reminder) {
                this.reminder = reminder;
                return this;
            }

            public Builder setTask(Task task) {
                this.task = task;
                return this;
            }

            public Builder setHabit(Habit habit) {
                this.habit = habit;
                return this;
            }

            public WeekSetup build() {
                return new WeekSetup(this);
            }
        }
    }

    // Getters
    public String getPlanName() {
        return planName;
    }

    public Objective getObjective() {
        return objective;
    }

    public Material getMaterial() {
        return material;
    }

    public Reminder getReminder() {
        return reminder;
    }

    public Task getTask() {
        return task;
    }

    public Habit getHabit() {
        return habit;
    }

    // Builder Pattern
    public static class Builder {
        private String planName;
        private Objective objective;
        private Material material;
        private Reminder reminder;
        private Task task;
        private Habit habit;

        public Builder setPlanName(String planName) {
            this.planName = planName;
            return this;
        }

        public Builder setObjective(Objective objective) {
            this.objective = objective;
            return this;
        }

        public Builder setMaterial(Material material) {
            this.material = material;
            return this;
        }

        public Builder setReminder(Reminder reminder) {
            this.reminder = reminder;
            return this;
        }

        public Builder setTask(Task task) {
            this.task = task;
            return this;
        }

        public Builder setHabit(Habit habit) {
            this.habit = habit;
            return this;
        }

        public WeekSetup build() {
            return new WeekSetup(this);
        }
    }
}

// Objetos de Valor (Value Objects) para maior coesão
class Objective {
    private final String title;
    private final String description;

    public Objective(String title, String description) {
        this.title = Objects.requireNonNull(title, "Title cannot be null");
        this.description = Objects.requireNonNull(description, "Description cannot be null");
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}

class Material {
    private final String topic;
    private final String format;

    public Material(String topic, String format) {
        this.topic = Objects.requireNonNull(topic, "Topic cannot be null");
        this.format = Objects.requireNonNull(format, "Format cannot be null");
    }

    public String getTopic() {
        return topic;
    }

    public String getFormat() {
        return format;
    }
}

class Reminder {
    private final String title;
    private final String description;

    public Reminder(String title, String description) {
        this.title = Objects.requireNonNull(title, "Title cannot be null");
        this.description = Objects.requireNonNull(description, "Description cannot be null");
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}

class WeekTask {
    private final String title;

    public WeekTask(String title) {
        this.title = Objects.requireNonNull(title, "Task title cannot be null");
    }

    public String getTitle() {
        return title;
    }
}

class Habit {
    private final String name;

    public Habit(String name) {
        this.name = Objects.requireNonNull(name, "Habit name cannot be null");
    }

    public String getName() {
        return name;
    }
}

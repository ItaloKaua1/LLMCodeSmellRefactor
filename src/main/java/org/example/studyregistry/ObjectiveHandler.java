package org.example.studyregistry;

public class ObjectiveHandler {

    // Classe para informações gerais de registro usando record
    public static record RegistryInfo(Integer id, Integer priority, boolean isActive) {}

    // Classe para informações textuais usando record
    public static record TextualInfo(String name, String title, String description, String topic,
                                     String objectiveInOneLine, String objectiveFullDescription, String motivation) {}

    // Classe para informações de tempo usando record
    public static record TimeInfo(Integer practicedDays, int day, int month, int year, Double duration) {}

}

package org.example.studycards;

public class Card {
    private String question;
    private String answer;

    public Card(String question, String answer) {
        if (question == null || question.isBlank()) {
            throw new IllegalArgumentException("Question cannot be null or blank");
        }
        if (answer == null || answer.isBlank()) {
            throw new IllegalArgumentException("Answer cannot be null or blank");
        }
        this.question = question;
        this.answer = answer;
    }

    public String displayCard() {
        return "Q: " + question + "\nA: " + answer;
    }

    public boolean isCorrectAnswer(String providedAnswer) {
        return answer.equalsIgnoreCase(providedAnswer.trim());
    }

    public void update(String newQuestion, String newAnswer) {
        if (newQuestion != null && !newQuestion.isBlank()) {
            this.question = newQuestion;
        }
        if (newAnswer != null && !newAnswer.isBlank()) {
            this.answer = newAnswer;
        }
    }
}

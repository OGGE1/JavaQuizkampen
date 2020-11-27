package Server;

import java.io.Serializable;

public class QA implements Serializable {

    private String question;
    private String correctAnswer;
    private String[] options;

    public QA(String question, String correctAnswer, String[] array) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.options = array;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String[] getOptions() {
        return options;
    }
}

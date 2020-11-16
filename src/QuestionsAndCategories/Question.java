package QuestionsAndCategories;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin Brankovic
 * Date: 2020-11-12
 * Time: 19:09
 * Project: JavaQuizkampen
 */
public class Question {

    private Categories categories;
    private String questionText;
    private List<String> answers = new ArrayList<>();
    private String answer;
    private String correctAnswer;

    Question(Categories categories, String questionText, String answer1, String answer2, String answer3, String correctAnswer) {
        this.categories = categories;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(correctAnswer);

    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public boolean isCorrectAnswer() {
        return answer.equals(correctAnswer);
    }

}

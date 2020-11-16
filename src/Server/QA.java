package Server;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-11-16 <br>
 * Time: 08:44 <br>
 * Project: JavaQuizkampen <br>
 */
public class QA{

    private String question;
    private String correctAnswer;
    private String[] options;

    public QA(String question, String correctAnswer, String[] array){
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.options = array;
    }

    public String getQuestion(){
        return question;
    }

    public String getCorrectAnswer(){
        return correctAnswer;
    }

    public String[] getOptions(){
        return options;
    }

    public static void main(String[] args) {
        String[] array = {"Kalle","John","Anders","Viktor"};
        QA qa = new QA("Vad heter du?","John",array);

        System.out.println(qa.getQuestion());

        System.out.println(qa.getCorrectAnswer());

        for(int i = 0; i < qa.getOptions().length; i++){
            System.out.println(qa.getOptions()[i]);
        }
    }
}

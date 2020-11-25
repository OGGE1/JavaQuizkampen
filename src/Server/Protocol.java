package Server;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-11-18   <br>
 * Time: 15:20   <br>
 * Project: JavaQuizkampen <br>
 */
public class Protocol {

    List<QA> qaList = new ArrayList<>();
    List<Boolean> player1Results = new ArrayList<>();
    List<Boolean> player2Results = new ArrayList<>();

    Database database = new Database();

    private int player = 1;

    private final int CHOOSE_CATEGORY = 0;
    private final int SEND_QUESTIONS = 1;
    private final int PLAYING_FIRST = 2;
    private final int PLAYING_SECOND = 3;
    private final int SEE_RESULT = 4;

    private int CURRENT_STATE;


    public Protocol() {
        CURRENT_STATE = CHOOSE_CATEGORY;
    }

    public Message getResponse(Message message) {
        if (CURRENT_STATE == CHOOSE_CATEGORY) {
            message.setPerform("CHOOSE CATEGORY");
            CURRENT_STATE = SEND_QUESTIONS;
            return message;
        }

        else  if (CURRENT_STATE == SEND_QUESTIONS) {
            qaList = database.getQuestions(message.getCategory());
            message.setQaList(qaList);
            message.setPerform("ANSWER QUESTION");

            CURRENT_STATE = PLAYING_FIRST;
            return message;
        }

        else if (CURRENT_STATE == PLAYING_FIRST) {
            if(message.getPlayerID() == 1){
                player1Results = message.getResultsFromAnswers();
            }
            else player2Results = message.getResultsFromAnswers();

            // Fortsätt

        }

        else if (CURRENT_STATE == PLAYING_SECOND){

        }


        return null;
    }


    public int getPlayer() {
        return player;
    }
}

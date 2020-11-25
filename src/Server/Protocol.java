package Server;

import java.util.ArrayList;
import java.util.Currency;
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

            if(message.getPlayerID() == 1){
                message.setSwitchToPlayer(2);
            }
            else message.setSwitchToPlayer(1);

            CURRENT_STATE = PLAYING_SECOND;
            return message;
        }

        else if (CURRENT_STATE == PLAYING_SECOND){
            if(message.getPlayerID() == 1){
                player1Results = message.getResultsFromAnswers();
            }
            else player2Results = message.getResultsFromAnswers();

            message.setSwitchToPlayer(3);
            message.setPerform("SEE RESULT");
            CURRENT_STATE = SEE_RESULT;
            message.setResultsFromAnswers(player2Results);
            return message;
        }

        else if (CURRENT_STATE == SEE_RESULT){
            message.setResultsFromAnswers(player1Results);
            if (player == 1) {
                player = 2;
                message.setSwitchToPlayer(2);
            }
            else {
                player = 1;
                message.setSwitchToPlayer(1);
            }

            message.setPerform("CHOOSE CATEGORY");
            CURRENT_STATE = CHOOSE_CATEGORY;
            return message;
        }
            // Behöver nog ett till mellan stadie här för att båda ska få upp sina resultat och sedan kunna gå vidare


        return null;
    }


    public int getPlayer() {
        return player;
    }
}

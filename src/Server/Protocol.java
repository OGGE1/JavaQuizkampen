package Server;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-11-18   <br>
 * Time: 15:20   <br>
 * Project: JavaQuizkampen <br>
 */
public class Protocol {

    private final int CHOOSE_CATEGORY = 0;
    private final int WAITING = 1;
    private final int PLAYING = 2;
    private final int SEE_RESULT = 3;

    private int CURRENT_STATE;

    public Protocol() {
        CURRENT_STATE = CHOOSE_CATEGORY;
    }

    public Message getResponse(Message message) {
        if (CURRENT_STATE == CHOOSE_CATEGORY) {

        }


        return null;
    }

}

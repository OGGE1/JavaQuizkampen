package Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-11-18   <br>
 * Time: 09:51   <br>
 * Project: JavaQuizkampen <br>
 */
public class Utility {

    private String playerName;
    private String enemyName;
    private int playerID;

    private List<Boolean> roundAnswers = new ArrayList<>();


    public List<Boolean> getRoundAnswers() {
        return roundAnswers;
    }

    public void addAnswers(boolean answer) {
        this.roundAnswers.add(answer);
    }

    public void addEnemyAnswers(List<Boolean> list) {
        for (var bool : list) {
            roundAnswers.add(bool);
        }
    }

    public void clearAnswersList() {
        roundAnswers.clear();
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public void setEnemyName(String enemyName) {
        this.enemyName = enemyName;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }
}

package Server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Message implements Serializable {

    private List<QA> qaList = new ArrayList<>();
    private List<Boolean> resultsFromAnswers = new ArrayList<>();

    private String name;
    private String category;
    private String perform;
    private int switchToPlayer = 0;

    private int playerID;

    public Message(String name, int playerID) {
        this.name = name;
        this.playerID = playerID;
    }

    public Message() {

    }

    public List<Boolean> getResultsFromAnswers() {
        return resultsFromAnswers;
    }

    public void setResultsFromAnswers(List<Boolean> resultsFromAnswers) {
        this.resultsFromAnswers = resultsFromAnswers;
    }

    public List<QA> getQaList() {
        return qaList;
    }

    public void setQaList(List<QA> qaList) {
        this.qaList = qaList;
    }

    public String getPerform() {
        return perform;
    }

    public void setPerform(String perform) {
        this.perform = perform;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getSwitchToPlayer() {
        return switchToPlayer;
    }

    public void setSwitchToPlayer(int switchToPlayer) {
        this.switchToPlayer = switchToPlayer;
    }
}

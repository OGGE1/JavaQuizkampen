package Server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-11-18   <br>
 * Time: 12:00   <br>
 * Project: JavaQuizkampen <br>
 */
public class Message implements Serializable {

    private List<QA> qaList = new ArrayList<>();
    private List<Boolean> resultsFromAnswers = new ArrayList<>();
    private String name;
    private int playerID;
    private String category;
    private String perform;

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

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }
}

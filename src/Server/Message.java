package Server;

import java.io.Serializable;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-11-18   <br>
 * Time: 12:00   <br>
 * Project: JavaQuizkampen <br>
 */
public class Message implements Serializable {

    private String name;
    private int playerID;
    private String category;

    public Message(String name, int playerID) {
        this.name = name;
        this.playerID = playerID;
    }

    public Message(String category) {
        this.category = category;
    }

    public Message() {

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

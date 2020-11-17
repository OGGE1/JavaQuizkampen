package Server;

import java.io.Serializable;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-11-17   <br>
 * Time: 10:38   <br>
 * Project: JavaQuizkampen <br>
 */
public class Response implements Serializable {

    private boolean isCorrect;
    private int r;
    private int c;

    public Response(boolean isCorrect, int r, int c) {
        this.isCorrect = isCorrect;
        this.r = r;
        this.c = c;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }
}

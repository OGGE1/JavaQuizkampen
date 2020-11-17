package Server;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-11-15   <br>
 * Time: 14:01   <br>
 * Project: JavaQuizkampen <br>
 */
public class ClientList {

    private static List<ObjectOutputStream> objectOut = new ArrayList<>();

    public void addObjectOutPutStream(ObjectOutputStream o) {
        objectOut.add(o);
    }

    public List<ObjectOutputStream> getObjectOut() {
        return objectOut;
    }

    public void removeObjectOutPutStream(ObjectOutputStream o) {
        objectOut.remove(o);
    }
}

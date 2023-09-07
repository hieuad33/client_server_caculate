package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class scobject {
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Socket sc;

    public void SocketHandler(Socket cs) throws IOException {

        this.oos = new ObjectOutputStream(cs.getOutputStream());
        this.ois = new ObjectInputStream(cs.getInputStream());
    }

    public void sendObject(Object o) throws IOException {

        this.oos.writeObject(o);
        this.oos.flush();
    }

    public Object receiveObject() throws IOException, ClassNotFoundException {

        return this.ois.readObject();
    }
}

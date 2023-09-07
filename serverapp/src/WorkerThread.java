import model.Cacular;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class WorkerThread extends Thread {
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public WorkerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        try {
            SocketHandler(socket);
            Cacular ca = (Cacular) receiveObject();
            ca.sta();
            System.out.printf(ca.toString());
            sendObject(ca);
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
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


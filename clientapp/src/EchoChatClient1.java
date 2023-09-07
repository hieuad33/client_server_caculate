import model.Cacular;
import socket.scobject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class EchoChatClient1 {
    public final static String SERVER_IP = "127.0.0.1";
    public final static int SERVER_PORT = 9;

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = null;
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT); // Connect to server
            System.out.println("Connected: " + socket);

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            // socket senrs object
            scobject sco = new scobject();
            sco.SocketHandler(socket);

            //khai bao
            int aNumber, bNumber;
            char kt;
            Scanner scanner = new Scanner(System.in);
            //nhap du lieu
            System.out.println("Nhập vào số thứ nhất: ");
            aNumber = scanner.nextInt();
            System.out.println("Nhập vào số thứ hai: ");
            bNumber = scanner.nextInt();
            System.out.println("Nhập phép toán (+, -, *, /): ");
            kt = scanner.next().charAt(0);

            Cacular ca=new Cacular(aNumber,bNumber,kt);

            sco.sendObject(ca);

            Cacular rs= (Cacular) sco.receiveObject();
            if(rs.getErr()==null)
            {
                System.out.println(String.valueOf(rs.getRs()));
            }
            else {
                System.out.println(rs.getErr());
            }


           // cacular sr= (cacular) sco.receiveObject();

        } catch (IOException ie) {
            System.out.println("Can't connect to server");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (socket != null) {

                socket.close();
            }
        }
        System.out.println("Complete processing: " + socket);
    }
}

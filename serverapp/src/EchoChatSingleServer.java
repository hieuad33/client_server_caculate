import model.Cacular;
import socket.scobject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
public class EchoChatSingleServer {

        public final static int SERVER_PORT = 9;

        public static void main(String[] args) throws IOException {
            ServerSocket serverSocket = null;
            try {
                System.out.println("Binding to port " + SERVER_PORT + ", please wait  ...");
                serverSocket = new ServerSocket(SERVER_PORT);
                System.out.println("Server started: " + serverSocket);
                System.out.println("Waiting for a client ...");
                while (true) {
                    try {
                        Socket socket = serverSocket.accept();
                        System.out.println("Client accepted: " + socket);

                        OutputStream os = socket.getOutputStream();
                        InputStream is = socket.getInputStream();

                        scobject sco = new scobject();
                        sco.SocketHandler(socket);


                        while (true) {
                            Cacular ca= (Cacular) sco.receiveObject();
                            ca.sta();


                            sco.sendObject(ca);
                        }

                    } catch (IOException e) {
                        System.err.println(" Connection Error: " + e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            }
        }

    }

package ch.hslu.tryit;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class tryClient {

    public static void main(final String[] args) throws IOException {
        getTime("localhost",1300);
    }

    static void getTime(String host, int port) throws IOException {
        Socket socket = new Socket(host, port);
        DataInputStream is;
        is = new DataInputStream(socket.getInputStream());
        byte[] bytes = is.readAllBytes();
        socket.close();
        String time = new String(bytes);
        System.out.println(time);
    }
}

package ch.hslu.tryit;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class tryClient {

    public static void main(final String[] args) throws IOException {
        first("localhost",1300); // dad
        second("localhost",1111); // bro
        first("localhost",1300);
    }

    static void first(String host, int port) throws IOException {
        Socket socket = new Socket(host, port);
        DataInputStream is;
        //is = new DataInputStream(socket.getInputStream());
        BufferedReader d = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //byte[] bytes = is.readAllBytes();
        byte[] date = d.readLine().getBytes();
        byte[] salam = d.readLine().getBytes();

        socket.close();
        String time = new String(date);
        System.out.println(time);
        String salamander = new String(salam);
        System.out.println(salamander);
    }

    static void second(String host, int port) throws IOException {
        Socket socket = new Socket(host, port);
        DataInputStream is;
        //is = new DataInputStream(socket.getInputStream());
        BufferedReader d = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //byte[] bytes = is.readAllBytes();
        byte[] date = d.readLine().getBytes();
        byte[] salam = d.readLine().getBytes();

        socket.close();
        String time = new String(date);
        System.out.println(time);
        String salamander = new String(salam);
        System.out.println(salamander);
    }
}

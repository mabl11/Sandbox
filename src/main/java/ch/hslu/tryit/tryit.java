package ch.hslu.tryit;

import java.io.DataInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class tryit {

    static final Logger Log = LoggerFactory.getLogger(tryit.class);

    public static void main(final String[] args) {
        try {
            final ServerSocket listen = new ServerSocket(1300);
            while (!Thread.interrupted()) {
                try (final Socket client = listen.accept()) {
                    client.getInetAddress();
                    final DataOutputStream dout = new DataOutputStream(client.getOutputStream());
                    final Date date = new Date();
                    dout.write((date.toString()).getBytes());
                }
            }
        } catch (IOException ex) {
            Log.debug(ex.getMessage());
        }
    }
}

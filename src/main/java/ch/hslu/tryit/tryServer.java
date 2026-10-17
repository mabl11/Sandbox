package ch.hslu.tryit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class tryServer {

    static final Logger Log = LoggerFactory.getLogger(tryServer.class);

    public static void main(final String[] args) {
        try {
            final ServerSocket bro = new ServerSocket(1300);
            final ServerSocket dad = new ServerSocket(1111);
            Log.info("start tcp");
            while (!Thread.interrupted()) {
                try (final Socket client = bro.accept()) {
                    Log.info("connected to: BRO " +  client);
                    client.getInetAddress();
                    final DataOutputStream dout = new DataOutputStream(client.getOutputStream());
                    final Date date = new Date();
                    dout.write((date.toString()).getBytes());
                    dout.write(("\nhallo bruder").getBytes());
                }
                try (final Socket client = dad.accept()) {
                    Log.info("connected to: DAD" +  client);
                    client.getInetAddress();
                    final DataOutputStream dout = new DataOutputStream(client.getOutputStream());
                    final Date date = new Date();
                    dout.write((date.toString()).getBytes());
                    dout.write(("\nhallo vater").getBytes());
                }
            }
        } catch (IOException ex) {
            Log.debug(ex.getMessage());
        }
    }
}

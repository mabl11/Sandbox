package ch.hslu.tryEcho;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

import static ch.hslu.tryEcho.EchoProtocol.getMessage;
import static ch.hslu.tryEcho.EchoProtocol.sendMessage;

public class EchoClient {

    private static final Logger LOG = LoggerFactory.getLogger(EchoClient.class);

    public static void main(final String[] args) {
        BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
        try (Socket socket = new Socket("localhost", 7777); OutputStream out = socket.getOutputStream(); InputStream in = socket.getInputStream()) {
            DataInputStream dataIn = new DataInputStream(in);
            DataOutputStream dataOut = new DataOutputStream(out);
            while (!Thread.interrupted()) {
                String messageOut = userIn.readLine();
                LOG.info("msg client: "+ messageOut);
                sendMessage(dataOut, messageOut);
                dataOut.flush();
                String messageIn = getMessage(dataIn);
                System.out.println(messageIn);
            }
        } catch (Exception ex) {
            LOG.debug(ex.getMessage());
        }
    }

}

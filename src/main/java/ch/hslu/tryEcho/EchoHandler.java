package ch.hslu.tryEcho;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import static ch.hslu.tryEcho.EchoProtocol.getMessage;
import static ch.hslu.tryEcho.EchoProtocol.sendMessage;


public class EchoHandler implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(EchoHandler.class);
    private final Socket client;

    public EchoHandler(final Socket client) {
        this.client = client;
    }

//    private static void sendMessage(DataOutputStream os, String msg) throws IOException {
//        byte[] bytesOut = msg.getBytes(StandardCharsets.UTF_8);
//        os.writeInt(bytesOut.length); // sendet länge von meinen msg bytes
//        os.write(bytesOut); // sendet die msg
//        os.writeInt(1111119919); // meine int zahl
//    }
//
//    private static String getMessage(DataInputStream is) throws IOException {
//        int length = is.readInt(); // liest erste int, also die länge
//        byte[] bytesIn = new byte[length];
//        is.readFully(bytesIn); // liest alle bytes danach die so lange sind wie die länge der nachricht. dh es liest die ganze msg.
//        int num = is.readInt(); // d.h meine Int Zahl bleibt noch übrig. Also lese die noch
//        LOG.info(String.valueOf(num));
//        return new String(bytesIn, StandardCharsets.UTF_8);
//    }

    public void run() {
        LOG.info("Connection to " + client);
        try (OutputStream out = client.getOutputStream(); InputStream in = client.getInputStream()) {
            DataInputStream dataIn = new DataInputStream(in);
            DataOutputStream dataOut = new DataOutputStream(out);
            while (!Thread.interrupted()) {
                String message = getMessage(dataIn);
                LOG.info("msg server: "+message);
                sendMessage(dataOut, message);
                dataOut.flush();
            }
        } catch (IOException ex) {
            LOG.debug(ex.getMessage());
        }
    }

//    public static void main(final String[] args) {
//        BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
//        try (Socket socket = new Socket("localhost", 7777); OutputStream out = socket.getOutputStream(); InputStream in = socket.getInputStream()) {
//            DataInputStream dataIn = new DataInputStream(in);
//            DataOutputStream dataOut = new DataOutputStream(out);
//            while (!Thread.interrupted()) {
//                String messageOut = userIn.readLine();
//                LOG.info("msg client: "+messageOut);
//                sendMessage(dataOut, messageOut);
//                dataOut.flush();
//                String messageIn = getMessage(dataIn);
//                System.out.println(messageIn);
//            }
//        } catch (Exception ex) {
//            LOG.debug(ex.getMessage());
//        }
//    }

}
package ch.hslu.tryEcho;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class EchoProtocol {

    private static final Logger LOG = LoggerFactory.getLogger(EchoProtocol.class);

    static void sendMessage(DataOutputStream os, String msg) throws IOException {
        byte[] bytesOut = msg.getBytes(StandardCharsets.UTF_8);
        os.writeInt(bytesOut.length); // sendet länge von meinen msg bytes
        os.write(bytesOut); // sendet die msg
        os.writeInt(1111119919); // meine int zahl
    }

    static String getMessage(DataInputStream is) throws IOException {
        int length = is.readInt(); // liest erste int, also die länge
        byte[] bytesIn = new byte[length];
        is.readFully(bytesIn); // liest alle bytes danach die so lange sind wie die länge der nachricht. dh es liest die ganze msg.
        int num = is.readInt(); // d.h meine Int Zahl bleibt noch übrig. Also lese die noch
        LOG.info(String.valueOf(num));
        return new String(bytesIn, StandardCharsets.UTF_8);
    }

}

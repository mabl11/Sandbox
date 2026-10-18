package ch.hslu.grpc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoClient {
    private static final int PORT = 5001;

    private static final Logger LOG = LoggerFactory.getLogger(EchoClient.class);

    public static void main(String[] args) throws IOException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", PORT)
                .usePlaintext()
                .build();
        EchoServiceGrpc.EchoServiceBlockingStub stub = EchoServiceGrpc.newBlockingStub(channel); // erzeuge client
        LOG.info("client done "+ stub);
        Echo.EchoRequest echoRequest = Echo.EchoRequest // anfrage bauen
                .newBuilder()
                .setMessage(msgBuilder())
                .build();
        LOG.info("message built ");
        Echo.EchoResponse echoResponse = stub.echo(echoRequest); // antwort holen
        System.out.println("response: " + echoResponse.getMessage());
        channel.shutdown();
    }

    private static String msgBuilder() throws IOException {
        BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
        return userIn.readLine();
    }
}

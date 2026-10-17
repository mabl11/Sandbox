package ch.hslu.grpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class EchoServer {
    private static final int PORT = 5001;

    public static void main(String[] args) throws IOException, InterruptedException {
        Server srv = ServerBuilder.forPort(PORT).addService(new EchoService()).build();
        srv.start();
        System.out.println("Server started, listening on " + PORT);
        srv.awaitTermination();
    }
}

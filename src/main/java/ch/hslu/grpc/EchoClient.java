package ch.hslu.grpc;
import java.io.IOException;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class EchoClient {
    private static final int PORT = 5001;

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", PORT)
                .usePlaintext()
                .build();
        EchoServiceGrpc.EchoServiceBlockingStub stub = EchoServiceGrpc.newBlockingStub(channel);
        Echo.EchoRequest echoRequest = Echo.EchoRequest
                .newBuilder()
                .setMessage("test")
                .build();
        Echo.EchoResponse echoResponse = stub.echo(echoRequest);
        System.out.println(echoResponse.getMessage());
        channel.shutdown();
    }
}

package ch.hslu.grpc;
import java.io.IOException;
import io.grpc.stub.StreamObserver;

public class EchoService extends EchoServiceGrpc.EchoServiceImplBase {
    public void echo(Echo.EchoRequest request, StreamObserver<Echo.EchoResponse> observer) {
        Echo.EchoResponse response = Echo.EchoResponse
                .newBuilder()
                .setMessage(request.getMessage())
                .build();
        observer.onNext(response);
        observer.onCompleted();
    }
}
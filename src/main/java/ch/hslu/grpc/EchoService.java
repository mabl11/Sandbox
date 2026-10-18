package ch.hslu.grpc;
import java.io.IOException;
import io.grpc.stub.StreamObserver;

public class EchoService extends EchoServiceGrpc.EchoServiceImplBase {
    public void echo(Echo.EchoRequest request, StreamObserver<Echo.EchoResponse> observer) {
        Echo.EchoResponse response = Echo.EchoResponse // erstellen der antwort
                .newBuilder()
                .setMessage(this.buildResponse(request)) // user defined
                .build();
        observer.onNext(response);
        observer.onCompleted();
    }

    private String buildResponse(Echo.EchoRequest request){
        String req = request.getMessage();
        System.out.println(req);
        if (req.equals("Dozent")){
            return "Hallo Dozent";
        } else if (req.equals("Daddy")) {
            return "Hallo Daddy";
        }
        else {
            return "Guten Tag mein Allerbester!";
        }
    }
}
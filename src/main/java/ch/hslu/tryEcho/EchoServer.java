package ch.hslu.tryEcho;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.LogManager;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class);

    public static void main(final String[] args) throws IOException {
        final ServerSocket listen = new ServerSocket(7777);
        final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor(); // new virtual thread for each task
        while (!Thread.interrupted()) {
            try {
                LOG.info("Waiting for connection...");
                final Socket client = listen.accept();
                final EchoHandler handler = new EchoHandler(client);
                executor.execute(handler);
            } catch (Exception ex) {
                LOG.debug(ex.getMessage());
            }
        }
    }
}

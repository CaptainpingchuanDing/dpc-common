package pers.captain.grpc;

import io.grpc.ServerBuilder;

public class Server {


    private int port;
    private io.grpc.Server server;
    private ServerImpl msServer = new ServerImpl();

    public Server(int port) {
        this.port = port;
    }

    public void startServer() throws Exception {
        server = ServerBuilder.forPort(port)
                .addService(msServer)
                .build()
                .start();
        System.out.println("Grpc server started, listening on port " + port);

    }

    public void shutDown() {
        server.shutdown();
    }


    public static void main(String[] args) throws Exception {
        Server server = new Server(8088);
        server.startServer();
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("TimerThread met exception");
            }
        }
    }
}

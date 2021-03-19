package pers.captain.gprc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;

public class Client {

    private MSServiceGrpc.MSServiceBlockingStub serviceBlockingStub;
    private ManagedChannel channel;

    public Client(String port) {
        channel = ManagedChannelBuilder.forTarget("127.0.0.1" + ":" + port).usePlaintext()
                .build();
        serviceBlockingStub = MSServiceGrpc.newBlockingStub(channel);
    }

    public MsResponse msNotify() {
        try {
            long start = System.currentTimeMillis();
            MsResponse msNoticeResponse = serviceBlockingStub.
                    withDeadlineAfter(10, TimeUnit.MILLISECONDS)
                    .notify(MsRequest.newBuilder().setAlias("testAlias").build());
            System.out.println("message:" + msNoticeResponse.getMessage());
            long end = System.currentTimeMillis();
            System.out.println("[PredictServiceClient.msNotify] cost time:" + (end - start));
            return msNoticeResponse;
        } catch (StatusRuntimeException ex) {
            System.err.println("[PredictServiceClient.msNotify]  met error" + ex.getCause());
            System.err.println("[PredictServiceClient.msNotify]  met error" + ex.getMessage());
            return null;
        }
    }

    public void shutdown() {
        //关闭连接
        try {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("PredictServiceClient channel shutdown met error" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Client client = new Client("8088");

        client.msNotify();

    }
}

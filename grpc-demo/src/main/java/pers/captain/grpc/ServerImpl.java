package pers.captain.grpc;

import io.grpc.stub.StreamObserver;
import pers.captain.gprc.MSServiceGrpc;
import pers.captain.gprc.MsRequest;
import pers.captain.gprc.MsResponse;

public class ServerImpl extends MSServiceGrpc.MSServiceImplBase {

    @Override
    public void notify(MsRequest request, StreamObserver<MsResponse> responseObserver) {
        try {
            // 注销jsf 服务
            MsResponse reply = MsResponse.newBuilder().setStatus(1).setMessage("success").build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
            System.out.println("recieve a message " + request.getAlias() + " will be stop");
            // 停止 当前运行的程序
        } catch (Exception e) {
            MsResponse reply = MsResponse.newBuilder().setStatus(0).setMessage("fail").build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
            System.out.println("recieve a message " + request.getAlias() + " but pid is not equal");
        }

    }
}
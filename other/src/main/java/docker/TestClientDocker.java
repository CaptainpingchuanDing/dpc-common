package docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.DockerCmdExecFactory;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.netty.NettyDockerCmdExecFactory;

public class TestClientDocker {

    private static String SERVERURL = "tcp://192.168.99.107:2377";
//    private static String SERVERURL = "tcp://10.0.0.0:8";


    public static void main(String[] args) {

        /**
         * ZBMAC-087479cf5:docker_test dingpingchuan1$ docker swarm init --advertise-addr 192.168.99.107
         * Swarm initialized: current node (r5j3x3r06tcar6mkq26d10bun) is now a manager.
         *
         * To add a worker to this swarm, run the following command:
         *
         *     docker swarm join --token SWMTKN-1-3iaz3hth2zlm0gtgo3sv9c49trhz6xnh4gilgnzk4nmdimpcyh-e2clsvultvj1atpqbtrw0afoe 192.168.99.107:2377
         *
         * To add a manager to this swarm, run 'docker swarm join-token manager' and follow the instructions.
         */
//        DockerClient dockerClient = DockerClientBuilder.getInstance("tcp://192.168.99.107:2377").build();
//        Info info = dockerClient.infoCmd().exec();
//        System.out.println(info);

        DockerCmdExecFactory factory = new NettyDockerCmdExecFactory().withConnectTimeout(20000);
        DockerClient dockerClient = DockerClientBuilder.getInstance(SERVERURL).withDockerCmdExecFactory(factory).build();
        Info info = dockerClient.infoCmd().exec();
        System.out.println(info);
    }
}

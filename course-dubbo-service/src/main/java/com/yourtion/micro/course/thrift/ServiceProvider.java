package com.yourtion.micro.course.thrift;

import com.yourtion.micro.user.thrift.UserService;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceProvider {

    @Value("${thrift.user.ip}")
    private String userServerIp;
    @Value("${thrift.user.port}")
    private int userServerPort;

    private enum ServiceType {
        USER,
    }

    public UserService.Client getUserService() {
        return this.getService(userServerIp, userServerPort, ServiceType.USER);
    }

    public <T> T getService(String ip, int port, ServiceType type) {
        var socket = new TSocket(ip, port, 3000);
        var transport = new TFastFramedTransport(socket);
        try {
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
            return null;
        }
        var protocol = new TBinaryProtocol(transport);

        TServiceClient result = null;

        switch (type) {
            case USER:
                result = new UserService.Client(protocol);
                break;
        }

        return (T) result;
    }
}

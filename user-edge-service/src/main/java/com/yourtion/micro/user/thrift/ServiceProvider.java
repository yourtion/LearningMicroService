package com.yourtion.micro.user.thrift;

import com.yourtion.micro.thrift.user.UserService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceProvider {

    @Value("${thrift.user.ip}")
    private String serverIp;
    @Value("${thrift.user.port}")
    private int serverPort;

    public UserService.Client getUserService() {

        var socket = new TSocket(serverIp, serverPort, 3000);
        var transport = new TFastFramedTransport(socket);
        try {
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
            return null;
        }
        var protocol = new TBinaryProtocol(transport);

        var client = new UserService.Client(protocol);

        return client;

    }
}

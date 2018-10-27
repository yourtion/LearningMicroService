package com.yourtion.micro.user.thrift;

import com.yourtion.micro.thrift.message.MessageService;
import com.yourtion.micro.thrift.user.UserService;
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

    @Value("${thrift.message.ip}")
    private String messageServerIp;
    @Value("${thrift.message.port}")
    private int messageServerPort;

    private enum ServiceType {
        USER,
        MESSAGE,
    }

    public UserService.Client getUserService() {
        return this.getService(userServerIp, userServerPort, ServiceType.USER);
    }

    public MessageService.Client geMessageService() {
        return this.getService(messageServerIp, messageServerPort, ServiceType.MESSAGE);

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
            case MESSAGE:
                result = new MessageService.Client(protocol);
                break;
        }

        return (T) result;
    }
}

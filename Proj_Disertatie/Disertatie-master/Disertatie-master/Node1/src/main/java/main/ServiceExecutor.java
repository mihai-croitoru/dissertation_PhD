package main;

import apps.ServiceApps;
import com.fasterxml.jackson.databind.ObjectMapper;
import heartbeat.OpRequestMsg;
import heartbeat.OpResponseMsg;
import states.OpState;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServiceExecutor implements Runnable {

    private DatagramSocket socket;
    private InetAddress address;
    private int port;
    private String requestAsString;

    public InetAddress getAddress() {
        return address;
    }

    public ServiceExecutor setAddress(InetAddress address) {
        this.address = address;
        return this;
    }

    public int getPort() {
        return port;
    }

    public ServiceExecutor setPort(int port) {
        this.port = port;
        return this;
    }

    public String getRequestAsString() {
        return requestAsString;
    }

    public ServiceExecutor setRequestAsString(String requestAsString) {
        this.requestAsString = requestAsString;
        return this;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public ServiceExecutor setDatagramSocket(DatagramSocket socket) {
        this.socket = socket;
        return this;
    }


    @Override
    public void run() {

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            byte[] buffer;

            System.out.println("Request: " + requestAsString);
            System.out.println("Port: " + port);
            System.out.println("address: " + address);

            OpRequestMsg opMessage;
            String opResponse;
            opMessage = objectMapper.readValue(requestAsString, OpRequestMsg.class);
            OpResponseMsg responseMessage = new OpResponseMsg();

            try {
                opResponse = ServiceApps.getResult(opMessage);
                responseMessage.setResponse(
                        opMessage.getServiceName() + " (" + opMessage.getParams()
                                + ") = " + opResponse);
                responseMessage.setState(OpState.SUCCESS);
            } catch (Exception e) {
                responseMessage.setState(OpState.FAILED);
                responseMessage.setErrorMessage(e.getMessage());
            }

            buffer = objectMapper.writeValueAsBytes(responseMessage);
            DatagramPacket response = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

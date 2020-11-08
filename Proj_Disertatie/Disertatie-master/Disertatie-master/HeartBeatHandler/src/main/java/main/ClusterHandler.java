package main;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ClusterHandler implements Runnable {
    HeartBeatHandler handler;
    int port;

    public HeartBeatHandler getHandler() {
        return handler;
    }

    public ClusterHandler setHandler(HeartBeatHandler handler) {
        this.handler = handler;
        return this;
    }

    public int getPort() {
        return port;
    }

    public ClusterHandler setPort(int port) {
        this.port = port;
        return this;
    }

    @Override
    public void run() {
        DatagramPacket request, response;
        DatagramSocket socket = null;
        Gson gson = new Gson();

        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        while (true) {
            byte[] buffer = new byte[4096];

            request = new DatagramPacket(buffer, buffer.length);

            try {
                socket.receive(request);

                InetAddress address = request.getAddress();
                int requestPort = request.getPort();

                System.out.println("Getting  the cluster info/data.");

                buffer = gson.toJson(handler.getMonitoredHosts()).getBytes();
                response = new DatagramPacket(buffer, buffer.length, address, requestPort);

                socket.send(response);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

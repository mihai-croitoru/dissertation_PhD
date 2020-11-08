package heartbeat;

import com.google.gson.Gson;
import main.HeartBeatHandler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.time.Instant;

public class HBReceiver implements Runnable {

    private HeartBeatHandler handler;
    private int port;
    private InetAddress address;

    public HeartBeatHandler getHandler() {
        return handler;
    }

    public HBReceiver setHandler(HeartBeatHandler handler) {
        this.handler = handler;
        return this;
    }

    public int getPort() {
        return port;
    }

    public HBReceiver setPort(int port) {
        this.port = port;
        return this;
    }

    public InetAddress getAddress() {
        return address;
    }

    public HBReceiver setAddress(InetAddress address) {
        this.address = address;
        return this;
    }

    //runnable code
    public void run() {
        MulticastSocket socket = null;
        try {
            socket = new MulticastSocket(port);
            socket.joinGroup(address);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            byte[] buffer;
            try {
                buffer = new byte[4096];

                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(datagramPacket);

                String responseAsString = new String(datagramPacket.getData(),
                        datagramPacket.getOffset(), datagramPacket.getLength());
                Instant now = Instant.now();

                //unpacking message from Gson
                Gson gson = new Gson();
                HBMessage receivedMessage = gson.fromJson(responseAsString, HBMessage.class);
                Host host = new Host();
                host.setUpdatedTime(now);
                host.setName(receivedMessage.getName());
                host.setVersion(receivedMessage.getVersion());
                host.setServices(receivedMessage.getServices());
                host.setPort(receivedMessage.getPort());
                host.setAddress(datagramPacket.getAddress());
                handler.addHost(receivedMessage.getUuid(), host);
                handler.listHosts();

            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

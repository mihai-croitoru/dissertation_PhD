package heartbeat;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class HBSender implements Runnable {

    HBMessage message;
    DatagramSocket socket;
    private InetAddress address;
    private int port;
    public InetAddress getAddress() {
        return address;
    }

    public HBSender setAddress(InetAddress address) {
        this.address = address;
        return this;
    }

    public int getPort() {
        return port;
    }

    public HBSender setPort(int port) {
        this.port = port;
        return this;
    }

    public HBMessage getMessage() {
        return message;
    }

    public HBSender setMessage(HBMessage message) {
        this.message = message;
        return this;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public HBSender setSocket(DatagramSocket socket) {
        this.socket = socket;
        return this;
    }

    public void run() {

        while (true) {
            DatagramPacket datagramPacket;
            Gson gson = new Gson();
            byte[] buffer = gson.toJson(message).getBytes();
            try {
                datagramPacket = new DatagramPacket(buffer, buffer.length, address, port);
                socket.send(datagramPacket);
                System.out.println("Heart beat sent!");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

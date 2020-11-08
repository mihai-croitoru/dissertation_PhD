package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;

public class Service implements Runnable {
    private ExecutorService executorService;

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public Service setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
        return this;
    }

    @Override
    public void run() {
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(Node.portvalue);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        byte[] buffer = new byte[4096];
        // while (true && !stop) {
        while (true) {
            try {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                InetAddress address = request.getAddress();
                int requestPort = request.getPort();

                String requestAsString = new String(request.getData(), request.getOffset(),
                        request.getLength());

                ServiceExecutor servExec = new ServiceExecutor();
                servExec.setRequestAsString(requestAsString);
                servExec.setPort(requestPort);
                servExec.setAddress(address);
                servExec.setDatagramSocket(socket);
                executorService.execute(servExec);

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}

package main;

import cleaning.Cleaner;
import heartbeat.HBReceiver;
import heartbeat.HBSender;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static message.MessageCreator.createHeartBeatMessage;

public class Clustering {

    public static void endClustering() {
        System.out.println("Ending the program!");
        System.exit(0);
    }

    public static void startClustering() throws SocketException, UnknownHostException {
        InetAddress address = InetAddress.getByName("230.0.0.1");
        int port = 4444;

        HeartBeatHandler handler = new HeartBeatHandler();
        HBSender hbSender = new HBSender();
        hbSender.setAddress(address);
        hbSender.setPort(port);

        Cleaner hbCleaner = new Cleaner();
        hbCleaner.setHandler(handler);

        ClusterHandler clusterHandler = new ClusterHandler();
        clusterHandler.setHandler(handler);
        clusterHandler.setPort(4499);

        hbSender.setMessage(createHeartBeatMessage());
        DatagramSocket sendSocket = new DatagramSocket();
        hbSender.setSocket(sendSocket);

        HBReceiver HBReceiver = new HBReceiver();

        HBReceiver.setHandler(handler);
        HBReceiver.setAddress(address);
        HBReceiver.setPort(port);

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        ExecutorService serviceExecutor = Executors.newFixedThreadPool(12);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        main.Service service = new main.Service();
        service.setExecutorService(serviceExecutor);

        executorService.execute(HBReceiver);
        executorService.execute(hbSender);
        executorService.execute(clusterHandler);
        executorService.execute(service);
        scheduledExecutorService.scheduleWithFixedDelay(hbCleaner, 10, 3, TimeUnit.SECONDS);
    }
}

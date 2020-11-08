package cleaning;

import main.HeartBeatHandler;

import java.time.Duration;
import java.time.Instant;

public class Cleaner implements Runnable {
    private HeartBeatHandler handler;

    public HeartBeatHandler getHandler() {
        return handler;
    }

    public Cleaner setHandler(HeartBeatHandler handler) {
        this.handler = handler;
        return this;
    }

    @Override
    public void run() {

        System.out.println("Starting the Cleaning service...");
        Instant now = Instant.now();

        handler.getMonitoredHosts()
                .entrySet()
                .removeIf(uuidHostEntry -> {

                    System.out.println("Duration: " + Duration.between(uuidHostEntry.getValue()
                            .getUpdatedTime(), now)
                            .getSeconds() + " seconds since the last Heart-Beat; ");

                    return Duration.between(uuidHostEntry.getValue()
                            .getUpdatedTime(), now)
                            .getSeconds() >= 10;
                });
    }
}

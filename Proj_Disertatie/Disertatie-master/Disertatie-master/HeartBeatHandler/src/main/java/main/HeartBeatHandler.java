package main;

import heartbeat.Host;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HeartBeatHandler {
    private Map<UUID, Host> monitoredHosts;
    public Map<UUID, Host> getMonitoredHosts() {
        return monitoredHosts;
    }

    public HeartBeatHandler setMonitoredHosts(Map<UUID, Host> monitoredHosts) {
        this.monitoredHosts = monitoredHosts;
        return this;
    }

    public HeartBeatHandler() {
        this.monitoredHosts = new HashMap<>();
    }

    public HeartBeatHandler(Map<UUID, Host> monitoredHosts) {
        this.monitoredHosts = monitoredHosts;
    }

    public void addHost(UUID uuid, Host host) {
        if (monitoredHosts.get(uuid) == null) {
            monitoredHosts.put(uuid, host);
        } else {
            monitoredHosts.replace(uuid, host);
        }
    }

    public boolean hostExists(UUID uuid) {
        return monitoredHosts.containsKey(uuid);
    }

    public void removeHost(String name) {
        monitoredHosts.remove(name);
    }

    public void listHosts() {

        monitoredHosts.forEach((uuid, host) -> {
            System.out.println(uuid + " host: " + host);
        });
    }
}

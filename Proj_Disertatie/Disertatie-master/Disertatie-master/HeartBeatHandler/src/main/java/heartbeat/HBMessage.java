package heartbeat;

import java.util.List;
import java.util.UUID;

public class HBMessage {
    private UUID uuid;
    private String name;
    private String version;
    private int port;
    private List<Service> services;

    public int getPort() {
        return port;
    }

    public HBMessage setPort(int port) {
        this.port = port;
        return this;
    }

    public UUID getUuid() {
        return uuid;
    }

    public HBMessage setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public HBMessage setName(String name) {
        this.name = name;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public HBMessage setVersion(String version) {
        this.version = version;
        return this;
    }

    public List<Service> getServices() {
        return services;
    }

    public HBMessage setServices(List<Service> services) {
        this.services = services;
        return this;
    }

    @Override
    public String toString() {
        return "uuid: " + uuid + "," + "name: " + name + "," + "version: "
                + version + "," + "services: " + services;
    }
}

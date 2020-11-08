package heartbeat;

import java.util.List;

public class OpRequestMsg {

    List<String> params;
    String serviceName;

    public OpRequestMsg() {

    }

    public List<String> getParams() {
        return params;
    }

    public OpRequestMsg setParams(List<String> params) {
        this.params = params;
        return this;
    }

    public String getServiceName() {
        return serviceName;
    }

    public OpRequestMsg setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }
}

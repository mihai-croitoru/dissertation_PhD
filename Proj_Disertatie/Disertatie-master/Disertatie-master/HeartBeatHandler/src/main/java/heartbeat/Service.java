package heartbeat;

public class Service {
    String serviceName;
    String inputParameters;
    String outputParameter;

    public String getServiceName() {
        return serviceName;
    }

    public Service setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public String getInputParameters() {
        return inputParameters;
    }

    public Service setInputParameters(String inputParameters) {
        this.inputParameters = inputParameters;
        return this;
    }

    public String getOutputParameter() {
        return outputParameter;
    }

    public Service setOutputParameter(String outputParameter) {
        this.outputParameter = outputParameter;
        return this;
    }

    @Override
    public String toString() {
        return "{serviceName:" + serviceName + ", " + "inputParameters:"
                + inputParameters + ", " + "outputParameter:" + outputParameter + "}";
    }
}

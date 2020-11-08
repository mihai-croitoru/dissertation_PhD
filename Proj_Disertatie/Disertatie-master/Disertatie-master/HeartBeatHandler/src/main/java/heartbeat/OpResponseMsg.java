package heartbeat;

import states.OpState;

public class OpResponseMsg {
    String response;
    OpState state;
    String errorMessage;

    public OpState getState() {
        return state;
    }

    public OpResponseMsg setState(OpState state) {
        this.state = state;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public OpResponseMsg setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public String getResponse() {
        return response;
    }

    public OpResponseMsg setResponse(String response) {
        this.response = response;
        return this;
    }
}

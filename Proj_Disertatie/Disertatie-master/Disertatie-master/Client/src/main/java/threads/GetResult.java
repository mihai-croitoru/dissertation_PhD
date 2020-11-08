package threads;

import com.google.gson.Gson;
import form.Client;
import heartbeat.OpRequestMsg;
import heartbeat.OpResponseMsg;
import states.OpState;

import javax.swing.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.List;

public class GetResult implements Runnable {
    private Client client;

    public GetResult(Client client) {
        this.client = client;
    }

    @Override
    public void run() {
        OpRequestMsg opReqMessage = new OpRequestMsg();
        List<String> inputParameters = Arrays.asList(client.getInputParamsJTextField()
                .getText()
                .split(" "));

        opReqMessage.setServiceName(client.getSelectedServiceName());
        opReqMessage.setParams(inputParameters);

        InetAddress serviceClientAddress;
        int serviceClientPort;

        try {
            serviceClientAddress = client.getSelectedHost()
                    .getAddress();
            serviceClientPort = client.getSelectedHost()
                    .getPort();

            DatagramPacket request, response;
            DatagramSocket socket = null;
            Gson gson = new Gson();

            byte[] buffer = gson.toJson(opReqMessage)
                    .getBytes();

            try {
                socket = new DatagramSocket();
                socket.setSoTimeout(30000);

                request = new DatagramPacket(buffer, buffer.length, serviceClientAddress,
                        serviceClientPort);
                socket.send(request);

                buffer = new byte[4096];
                response = new DatagramPacket(buffer, buffer.length);
                socket.receive(response);

                String responseAsString = new String(response.getData(), response.getOffset(),
                        response.getLength());

                System.err.println(responseAsString);
                OpResponseMsg responseMessage = gson.fromJson(responseAsString,
                        OpResponseMsg.class);

                if (responseMessage.getState() == OpState.SUCCESS) {

                    client.updateResultTextArea(responseMessage.getResponse());
                    if(responseMessage.getErrorMessage() != null){
                        client.updateErrosAndSqlResultTextArea(responseMessage.getErrorMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), responseMessage.getErrorMessage(),
                            "Erroare", JOptionPane.ERROR_MESSAGE);

                }

            } catch (Exception ex) {
                if (ex instanceof SocketTimeoutException) {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Este posibil ca serviciul pe care încercați să îl utilizați să nu fie disponibil. Vă rugăm actualizați serviciile din arborele de servicii.",
                            "Erroare", JOptionPane.ERROR_MESSAGE);
                            client.updateErrosAndSqlResultTextArea(String.valueOf(ex));
                    client.updateErrosAndSqlResultTextArea(String.valueOf(JOptionPane.ERROR_MESSAGE));
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                    client.updateErrosAndSqlResultTextArea(String.valueOf(ex));
                    client.updateErrosAndSqlResultTextArea(String.valueOf(JOptionPane.ERROR_MESSAGE));
                }
            } finally {
                socket.close();
            }
        } catch (Exception ex) {
            if (ex instanceof SocketTimeoutException) {
                JOptionPane.showMessageDialog(new JFrame(),
                        "Este posibil ca serviciul pe care încercați să îl utilizați să nu fie disponibil. Vă rugăm Actualizați serviciile din arborele de servicii.",
                        "Erroare", JOptionPane.ERROR_MESSAGE);
                client.updateErrosAndSqlResultTextArea(String.valueOf(ex));
                client.updateErrosAndSqlResultTextArea(String.valueOf(JOptionPane.ERROR_MESSAGE));

            } else {
                JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
                client.updateErrosAndSqlResultTextArea(String.valueOf(ex));
                client.updateErrosAndSqlResultTextArea(String.valueOf(JOptionPane.ERROR_MESSAGE));
            }
        }
    }
}

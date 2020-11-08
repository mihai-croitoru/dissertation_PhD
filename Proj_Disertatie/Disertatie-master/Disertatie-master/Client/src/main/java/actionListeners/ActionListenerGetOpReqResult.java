package actionListeners;

import form.Client;
import threads.GetResult;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerGetOpReqResult implements ActionListener {
    Client client;

    public ActionListenerGetOpReqResult(Client client) {
        this.client = client;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Thread(new GetResult(client)).start();
    }
}

package actionListeners;

import form.Client;
import heartbeat.Service;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ActionListenerGetServiceTree implements TreeSelectionListener {

    Client client;

    public ActionListenerGetServiceTree(Client client) {
        this.client = client;
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath treePath = client.getHostsJTree()
                .getSelectionPath();
        if (treePath != null && treePath.getPathCount() > 2) {
            //in cazul in care jPanel-ul care contine tree-ul cu lista de noduri din clustere si in cazul in care counterul catre calea ce a fost
            //selectata >2 -adica este selectat / sa facut click pe numele serviciului,
            // 1=hosts,
                // 2=name+uuid,
                    // 3=service name

            UUID uuid = UUID.fromString(treePath.getPathComponent(1)
                    .toString()
                    .split(":")[1].replace(" ", ""));
            String serviceName = treePath.getLastPathComponent()
                    .toString();

            List<Service> services = client.getHosts()
                    .get(uuid)
                    .getServices();

            Service service = services.stream()
                    .filter(service1 -> service1.getServiceName()
                            .equals(serviceName))
                    .collect(Collectors.toList())
                    .get(0);

            client.updateSelectedServiceName(serviceName);
            client.updateSelectedHost(client.getHosts()
                    .get(uuid));
            client.updateInputParametersTextArea(
                    "Informatii serviciu selectat: \n" +   service.getServiceName() + " " + service.getOutputParameter() + " "
                            + "( " + service.getInputParameters() + " );");
        }
    }
}

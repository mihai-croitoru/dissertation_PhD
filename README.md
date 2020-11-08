# dissertation_PhD
Disseration(PhD) project. Distributed app similar to a CRM/CNM(Cluster Resource Manager/ Cluster Nodes Manager). 
• The application is focused on heart-beats;     
• Communication between nodes takes place through UDP packets;     
• The application identifies at the time of running each node separately, the topology of a cluster (of the nodes in the cluster), as well as the services running on these nodes;    
• Each node has a list of services that can be run by the user.     
• Each service present on the node is described using the following structure: UUID, name, version, input parameters to be retrieved from the user, and return parameters.   
• Each node in the cluster has both a multicast server and a multicast client to ensure inter-nodal communication;     
• The server / node application automatically detects new nodes entering the cluster as well as nodes that have stopped running;    
• The client application has a graphical interface with the user, it offers the possibility to connect to any node in the cluster to take over the topology and to select the node and the service to be run!     
• The client application provides a detailed description of each service that is present on the node;    
• For scaling, a node in the cluster runs the services requested by clients using the concurrent runtime environment provided by java.util.concurrent.ThreadPoolExecutor.

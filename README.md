# dissertation_PhD

Disseration(PhD) project (defended in 2020, TUIASI).
Distributed app similar to a CRM/CNM(Cluster Resource Manager/ Cluster Nodes Manager). 
DATABASE/MYSQL FUCNTIONALITY EXCLUDED/COMMENTED; CLUSTER LOAD-BALANCING EXCLUDED ENTIRELY!
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
************************************************** HOW TO RUN ******************************************************************
Can be runned from IDE or using .jar files/archives. 
When running from IDE be sure to check "Allow paralel run" or equivalent ! and be sure to set a new port to any other new runnable instance, default is 5566!
When running via .jar files the commands are as follows : 
--for Node app : " java -cp Node.jar main.Node 5566 "// the 4 digit number is for specifying the port, when running multiple instances on the same machine be sure to change! 
-- for Client app : " java -cp Client.jar main.ClientAppMain " 
    In the Client app use the Cluster handler port (default 4499) not the node port set at runtime ! and for the ip it should be the host machine ip that the node app is running from. 
!!!!! MULTIPLE INSTANCES OF NODES CAN BE RUN ON THE SAME MACHINE WITH SAME IP, JUST BY CHANGING THE PORT NUMBER, HENCE SIMULATING SCALABILITY OF NODES IN CLUSTER !!!     
  

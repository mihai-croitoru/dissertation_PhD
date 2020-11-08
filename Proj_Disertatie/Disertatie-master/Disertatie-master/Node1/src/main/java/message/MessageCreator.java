package message;

import heartbeat.HBMessage;
import heartbeat.Service;

import java.util.Arrays;
import java.util.UUID;

import static main.Node.portvalue;

public class MessageCreator {
    public static HBMessage createHeartBeatMessage() {

        Service sumService = new Service();
        sumService.setServiceName("sumTwoIntegers");
        sumService.setInputParameters("int param1; int param2");
        sumService.setOutputParameter("int result");

        Service subService = new Service();
        subService.setServiceName("subTwoIntegers");
        subService.setInputParameters("int param1; int param2");
        subService.setOutputParameter("int result");

        Service multiplyService = new Service();
        multiplyService.setServiceName("multiplyTwoIntegers");
        multiplyService.setInputParameters("int param1; int param2");
        multiplyService.setOutputParameter("int result");

        Service divideService = new Service();
        divideService.setServiceName("divideTwoIntegers");
        divideService.setInputParameters("int param1; int param2");
        divideService.setOutputParameter("double result");

        Service squareRoot = new Service();
        squareRoot.setServiceName("squareRoot");
        squareRoot.setInputParameters("int param1 ");
        squareRoot.setOutputParameter("double result");

        Service stringMultiplicationService = new Service();
        stringMultiplicationService.setServiceName("stringMultiplication");
        stringMultiplicationService.setInputParameters("String s; int multiplicationTimes");
        stringMultiplicationService.setOutputParameter("String result");

        /*Service addEmployeeToDBService = new Service();
        addEmployeeToDBService.setServiceName("addEmployeeToDB");
        addEmployeeToDBService.setInputParameters("String firstName; String lastName; String jobTitle");
        addEmployeeToDBService.setOutputParameter("String result");*/

        Service stopNode = new Service();
        stopNode.setServiceName("stopNode");
        stopNode.setInputParameters("int param1 : if value = 1 : node stops execution;  else if value = 0: node continues running.");
        stopNode.setOutputParameter("String result");

        HBMessage message = new HBMessage();
        message.setName("Node");
        //  message.setName(machineName);
        message.setVersion("v1");
        message.setPort(portvalue);
        message.setUuid(UUID.randomUUID());
        //message.setServices(Arrays.asList(multiplyService, sumService, subService, divideService, stringMultiplicationService, squareRoot, addEmployeeToDBService, stopNode));
        message.setServices(Arrays.asList(multiplyService, sumService, subService, divideService, stringMultiplicationService, squareRoot, stopNode));

        return message;
    }


}

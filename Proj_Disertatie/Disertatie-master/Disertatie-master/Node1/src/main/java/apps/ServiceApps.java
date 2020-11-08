package apps;

import heartbeat.OpRequestMsg;

import java.sql.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.lang.Math.sqrt;

public class ServiceApps {

    public static void nodeShutdownTimer() {
        try {
            TimeUnit.SECONDS.sleep(2);

        } catch (InterruptedException e) {

            e.printStackTrace();
        } finally {
            main.Clustering.endClustering();
        }
    }

    public static String stopNode(int param1) {
        String setMessage = null;
        String stopMessage = "Executia nodului a fost oprita! ";
        String continueMessage = "Nodul inca ruleaza! ";
        String anyOtherValue = "Valoare introdusa nu apartine listei de valori acceptate(0 sau 1)!";

        if (param1 == 1) {
            setMessage = stopMessage;
            nodeShutdownTimer();

        } else if (param1 == 0) {
            setMessage = continueMessage;

        } else {
            setMessage = anyOtherValue;
        }
        return setMessage;
    }

  /*  public static String addEmployeeToDB(String firstName, String lastName, String jobTitle) {

        try {
            String dbHost = "jdbc:mysql://localhost:3306/employees";
            String uName = "root";
            String uPass = "root1234";
            Connection connection = DriverManager.getConnection(dbHost, uName, uPass);

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "select * from workers";
            ResultSet rs = statement.executeQuery(sql);
            rs.last();
            int idnew = rs.getInt("idworkers") + 1;
            rs.moveToInsertRow();
            rs.updateInt("idworkers", idnew);
            rs.updateString("First_Name", firstName);
            rs.updateString("Last_Name", lastName);
            rs.updateString("Job_Title", jobTitle);
            rs.insertRow();
            rs.beforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error Msg Body :" + e.getMessage());
            return e.getMessage();
        }
        return "Entry Successfully added to DB!";
    }*/

    public static String stringMultiplication(String s, int multiplicationTimes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < multiplicationTimes; i++) {
            stringBuilder.append(s);
        }
             return stringBuilder.toString();
    }

    public static int sumTwoIntegers(int param1, int param2) {
        return param1 + param2;
    }

    public static int subTwoIntegers(int param1, int param2) {

        return param1 - param2;
    }

    public static int multiplyTwoIntegers(int param1, int param2) {
        return param1 * param2;
    }

    public static double divideTwoIntegers(int param1, int param2) {
        return param1 / param2;
    }

    public static double squareRoot(int param1) {
        return sqrt(param1);
    }

    private static List<Integer> getParamsFromString(List<String> paramsAsString) {
        List<Integer> params = paramsAsString.stream()
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());
        return params;
    }

    public static String getResult(OpRequestMsg opMessage) {

        switch (opMessage.getServiceName()) {
            case "sumTwoIntegers":
                List<Integer> paramsSum = getParamsFromString(
                        opMessage.getParams());
                return String.valueOf(sumTwoIntegers(paramsSum.get(0), paramsSum.get(1)));
            case "subTwoIntegers":
                List<Integer> paramsSub = getParamsFromString(
                        opMessage.getParams());
                return String.valueOf(subTwoIntegers(paramsSub.get(0), paramsSub.get(1)));
            case "multiplyTwoIntegers":
                List<Integer> paramsMult = getParamsFromString(
                        opMessage.getParams());
                return String.valueOf(multiplyTwoIntegers(paramsMult.get(0), paramsMult.get(1)));
            case "divideTwoIntegers":
                List<Integer> paramsDiv = getParamsFromString(
                        opMessage.getParams());
                return String.valueOf(divideTwoIntegers(paramsDiv.get(0), paramsDiv.get(1)));
            case "stringMultiplication":
                return stringMultiplication(opMessage.getParams()
                        .get(0), Integer.parseInt(opMessage.getParams()
                        .get(1)));
            case "squareRoot":
                List<Integer> param = getParamsFromString(
                        opMessage.getParams());
                return String.valueOf(squareRoot(param.get(0)));
            /*case "addEmployeeToDB":
                List<String> paramsAddDB = opMessage.getParams();
                return String.valueOf(addEmployeeToDB(paramsAddDB.get(0), paramsAddDB.get(1), paramsAddDB.get(2)));*/
            case "stopNode":
                List<Integer> paramStop = getParamsFromString(opMessage.getParams());
                return String.valueOf(stopNode(paramStop.get(0)));
        }
        return String.valueOf(0);
    }


}

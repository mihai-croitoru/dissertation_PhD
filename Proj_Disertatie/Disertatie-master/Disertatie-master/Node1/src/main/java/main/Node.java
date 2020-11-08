package main;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class Node {
    public static int portvalue;
    //public static final String machineName = System.getProperty("java.vm.name");

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < args.length; i++) {
            portvalue = parseInt(args[i]);
        }

        Clustering.startClustering();
    }


}

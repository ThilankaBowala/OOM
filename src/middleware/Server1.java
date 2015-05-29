package middleware;

import java.io.*;
import java.net.*;

/**
 *
 * @author 2012cs024
 */
public class Server1 {

    static int[] results;
    static ServerSocket socketWithServerSkeleton;
    static Socket connectToServerSkeleton;
    static OutputStream outWithServerSkeleton;
    static BufferedReader inWithServerSkeleton;
    static String request;
    static String parameterString;

    static void bubbleSort(int[] toSort) {
        results = toSort;
        int length = results.length;
        int swap;

        for (int c = 0; c < (length - 1); c++) {
            for (int d = 0; d < length - c - 1; d++) {
                if (results[d] > results[d + 1]) {
                    swap = results[d];
                    results[d] = results[d + 1];
                    results[d + 1] = swap;
                }
            }
        }
    }

    static void getRequestFromServerSkeleton() throws IOException {
        socketWithServerSkeleton = new ServerSocket(60001);
        connectToServerSkeleton = socketWithServerSkeleton.accept();
        outWithServerSkeleton = connectToServerSkeleton.getOutputStream();
        inWithServerSkeleton = new BufferedReader(new InputStreamReader(connectToServerSkeleton.getInputStream()));

        request = inWithServerSkeleton.readLine();
    }

    static void sendResponceToServerSkeleton() throws IOException {
        //message to ServerSkeleton : 
        outWithServerSkeleton.write(results.toString().getBytes(), 0, results.length);
    }

    public static void main(String args[]) {

        try {
            getRequestFromServerSkeleton();
            parameterString = request;
            parameterString = parameterString.replace(null, "bubbleSort"); // not sure

            String[] items = parameterString.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
            int[] parameter = new int[items.length];

            for (int i = 0; i < items.length; i++) {
                try {
                    parameter[i] = Integer.parseInt(items[i]);
                } catch (NumberFormatException nfe) {
                    System.out.println(nfe);
                }
            }
            
            /*parameterString = in.readLine();
            System.out.println("get data");

            String[] items = parameterString.split(",");
            int[] parameter = new int[items.length];

            */

            bubbleSort(parameter);
            sendResponceToServerSkeleton();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}

package middleware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author 2012cs024
 */
public class Server2 {
    
    static int[] results;
    static ServerSocket socketWithServerSkeleton;
    static Socket connectToServerSkeleton;
    static OutputStream outWithServerSkeleton;
    static BufferedReader inWithServerSkeleton;
    static String request;
    static String parameterString;
    
    static void insertionSort(int[] toSort){
        results = toSort;
        
        for (int i=1; i < results.length; i++) {
            int next = results[i];
            int j = i;
            while (j > 0 && results[j - 1] > next) {
                results[j] = results[j - 1];
                j--;
            }
            results[j] = next;
        }
    }
    
    
    static void getRequestFromServerSkeleton() throws IOException {
        socketWithServerSkeleton = new ServerSocket(60002);
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
            parameterString = parameterString.replace(null, "insertionSort"); // not sure

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

            for (int i = 0; i < items.length; i++) {
                try {
                    parameter[i] = Integer.parseInt(items[i]);
                } catch (NumberFormatException nfe) {
                }
            }*/

            insertionSort(parameter);
            sendResponceToServerSkeleton();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}

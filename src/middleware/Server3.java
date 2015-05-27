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
public class Server3 {
    
    static int[] results;
    static ServerSocket socketWithServerSkeleton;
    static Socket connectToServerSkeleton;
    static OutputStream outWithServerSkeleton;
    static BufferedReader inWithServerSkeleton;
    static String request;
    static String parameterString;
    
    static void selectionSort(int[] toSort){
        results = toSort;
        
        for (int i = 0; i < results.length - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < results.length; j++)
                if (results[j] < results[index])
                    index = j;
      
            int smallerNumber = results[index]; 
            results[index] = results[i];
            results[i] = smallerNumber;
        }
    }
    
    
    static void getRequestFromServerSkeleton() throws IOException {
        socketWithServerSkeleton = new ServerSocket(60003);
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
            parameterString = parameterString.replace(null, "selectionSort"); // not sure

            String[] items = parameterString.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
            int[] parameter = new int[items.length];

            for (int i = 0; i < items.length; i++) {
                try {
                    parameter[i] = Integer.parseInt(items[i]);
                } catch (NumberFormatException nfe) {
                    System.out.println(nfe);
                }
            }

            selectionSort(parameter);
            sendResponceToServerSkeleton();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}

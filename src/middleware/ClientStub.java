package middleware;

import java.io.*;
import java.net.*;

/**
 *
 * @author Thilanka Bowala <Thilanka Bowala at GIGABYTE>
 */
public class ClientStub {

    //static int[] toSort;
    static ServerSocket socketWithClient;
    static Socket connectToClient, connectToServer;
    static OutputStream outWithClient, outWithServer;
    static BufferedReader inWithClient, inWithServer;
    static String request, name, messageToServer, result;
    static int parameter;

    static void getRequestsFromClient() throws IOException {

        socketWithClient = new ServerSocket(5555);
        connectToClient = socketWithClient.accept();
        outWithClient = connectToClient.getOutputStream();
        inWithClient = new BufferedReader(new InputStreamReader(connectToClient.getInputStream()));
        
        request = inWithClient.readLine();

//        name = inWithClient.readLine();
//        parameter = Integer.parseInt(inWithClient.readLine());

//            if ("bubbleSort".equals(name)) {
//                bubbleSort(parameter);
//            }else if ("insertionSort".equals(name)) {
//                insertionSort(parameter);
//            }else if ("selectionSort".equals(name)) {
//                selectionSort(parameter);
//            }else if ("quickSort".equals(name)) {
//                quickSort(parameter);
//            }        

    }

    static void sendRequestsToServer() throws IOException {

        connectToServer = new Socket("localhost", 5556);
        outWithServer = connectToServer.getOutputStream();
        inWithServer = new BufferedReader(new InputStreamReader(connectToServer.getInputStream()));

        //messageToServer = 
        outWithServer.write(request.getBytes(), 0, request.length());     //marshalling and send         
    }

    static void getResponseFromServer() {
    }

    static void sendResponseToClient() {
    }

    public static void main(String[] args) {

        try {
            synchronized (ClientStub.class) {
                getRequestsFromClient();
                sendRequestsToServer();
                getResponseFromServer();
                sendResponseToClient();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}

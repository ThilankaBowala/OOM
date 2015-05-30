package middleware;

import java.io.*;
import java.net.*;

/**
 *
 * @author Thilanka Bowala <Thilanka Bowala at GIGABYTE>
 */
public class ServerSkeleton extends Thread {

    static String methodName;
    static ServerSocket socketWithClient;
    static Socket connectToClient, connectToServer;
    static OutputStream outWithClient, outWithServer;
    static BufferedReader inWithClient, inWithServer;
    static String request, response;
    static int portNumber;

    static void getRequestFromClientStub() throws IOException {
        socketWithClient = new ServerSocket(5556);
        connectToClient = socketWithClient.accept();
        outWithClient = connectToClient.getOutputStream();
        inWithClient = new BufferedReader(new InputStreamReader(connectToClient.getInputStream()));

        request = inWithClient.readLine();
    }

    static void setPortNumber() {

        if (request.contains("bubbleSort")) {
            methodName = "bubbleSort";
        } else if (request.contains("insertionSort")) {
            methodName = "insertionSort";
        } else if (request.contains("selectionSort")) {
            methodName = "selectionSort";
        } else if (request.contains("quickSort")) {
            methodName = "quickSort";
        }
        portNumber = Integer.parseInt(BusinessLogicLayer.readXML(methodName));
    }

    /*static void setData() {
     String[] input = request.split(",");
     methodName = input[0];

     messageToServer = "";
     for (int j = 1; j < input.length; j++) {
     messageToServer = messageToServer + input[j] + ",";
     }
     }*/
    static void sendRequestToServer() throws IOException {
        connectToServer = new Socket("localhost", portNumber);
        outWithServer = connectToServer.getOutputStream();
        inWithServer = new BufferedReader(new InputStreamReader(connectToServer.getInputStream()));

        //message to Server :
        outWithServer.write(request.getBytes(), 0, request.length());
    }

    static void getResponseFromServer() throws IOException {
        response = inWithServer.readLine();
    }

    static void sendResponceToClientStub() throws IOException {
        //message to ClientStub : 
        outWithClient.write(response.getBytes(), 0, response.length());     //marshalling and send
    }

    @Override
    public void run() {

        try {
            synchronized (ClientStub.class) {
                getRequestFromClientStub();
                setPortNumber();
                sendRequestToServer();
                getResponseFromServer();
                sendResponceToClientStub();
            }
        } catch (Exception ex) {
            System.out.println(ex + "ss");
        }
    }
}

package middleware;

import java.io.*;
import java.net.*;

/**
 *
 * @author Thilanka Bowala <Thilanka Bowala at GIGABYTE>
 */
public class ClientStub extends Thread {

    static ServerSocket socketWithClient;
    static Socket connectToClient, connectToServer;
    static OutputStream outWithClient, outWithServer;
    static BufferedReader inWithClient, inWithServer;
    static String request, response;

    static void getRequestsFromClient() throws IOException {

        socketWithClient = new ServerSocket(5555);
        connectToClient = socketWithClient.accept();
        System.out.println("Connected to Client");
        outWithClient = connectToClient.getOutputStream();
        inWithClient = new BufferedReader(new InputStreamReader(connectToClient.getInputStream()));
        
        
        request = inWithClient.readLine();
        
        System.out.println("Got request from Client : " + request);
    }

    static void sendRequestsToServerSkeleton() throws IOException {

        connectToServer = new Socket("localhost", 5556);
        outWithServer = connectToServer.getOutputStream();
        inWithServer = new BufferedReader(new InputStreamReader(connectToServer.getInputStream()));

        //message to ServerSkeleton = 
        outWithServer.write(request.getBytes(), 0, request.length());     //marshalling and send      
        System.out.println("dddddddd");
    }

    static void getResponseFromServerSkeleton() throws IOException {
        response = inWithServer.readLine();
    }

    static void sendResponseToClient() throws IOException {
        //message to Client = 
        outWithClient.write(response.getBytes(), 0, response.length());
    }

    @Override
    public void run() {

        try {
            synchronized (ClientStub.class) {
                getRequestsFromClient();
                sendRequestsToServerSkeleton();
                getResponseFromServerSkeleton();
                sendResponseToClient();
            }
        } catch (Exception ex) {
            System.out.println(ex + " cs");
        }
    }
}

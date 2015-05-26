package middleware;

import java.io.*;
import java.net.*;

/**
 *
 * @author Thilanka Bowala <Thilanka Bowala at GIGABYTE>
 */
public class ClientApplication1 {

    static int[] integersToSort;
    static Socket connectToServer;
    static OutputStream out;
    static BufferedReader in;
    static String msg, methodName, result;

    static void sendRequest() throws IOException {

        connectToServer = new Socket("localhost", 5555);
        out = connectToServer.getOutputStream();
        in = new BufferedReader(new InputStreamReader(connectToServer.getInputStream()));

        msg = methodName + "(" + integersToSort + ")";
        out.write(msg.getBytes(), 0, msg.length());

    }

    static void getResponse() throws IOException {
        result = in.readLine();
        System.out.println(result);
    }
    
    static void setData(){
        //integersToSort = ;
        //methodName = ;
    }

    public static void main(String args[]) {

        setData();
        
        try {
            sendRequest();
            getResponse();
            connectToServer.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}

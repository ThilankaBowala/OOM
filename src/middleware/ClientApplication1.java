package middleware;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Thilanka Bowala <Thilanka Bowala at GIGABYTE>
 */
public class ClientApplication1 extends Thread {

    static int[] integersToSort = {4, 2, 65, 87, 2, 76};
    static Socket connectToServer;
    static BufferedWriter out;
    static BufferedReader in;
    static String msg, methodName = "bubbleSort", result;

    static void sendRequest() throws IOException {

//        if(Socket.){}
        connectToServer = new Socket("localhost", 5555);
        System.out.println("Connected to Client Stub");
        out = new BufferedWriter(new OutputStreamWriter(connectToServer.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(connectToServer.getInputStream()));

//        msg = methodName + "(" + integersToSort + ")";
//        out.write(msg.getBytes(), 0, msg.length());
        msg = methodName;
        for (int n = 0; n < integersToSort.length; n++) {
            msg = msg + "," + Integer.toString(integersToSort[n]);
        }
        
        //msg = msg.concat("\n");
        
        System.out.println("Sending message to Client Stub to pass :");
        out.write(msg);
        System.out.println("Message sent : " + msg);

    }

    static void getResponse() throws IOException {
        result = in.readLine();
        System.out.println(result);
    }

    static void setData() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<Integer>();

        System.out.println("Enter your sorting numbers:");
        while (sc.hasNextInt()) {
            int input = sc.nextInt();

            if (input == 0000) {
                break;
            }

            numbers.add(input);
        }

        integersToSort = new int[numbers.size()];

        if (numbers.isEmpty()) {
            System.out.println("There are not user inputs");
        } else {
            for (int j = 0; j < numbers.size(); j++) {
                integersToSort[j] = numbers.get(j);
            }

        }

        System.out.println("Elements:");
        for (int k : integersToSort) {
            System.out.print(k + " ");
        }
        System.out.println();

        System.out.println("Enter sorting method:");
        methodName = sc.next();
    }

    @Override
    public void run() {

        //setData();

        try {
            sendRequest();
            getResponse();
            connectToServer.close();
        } catch (Exception ex) {
            System.out.println(ex + " c1");
        }

    }
}

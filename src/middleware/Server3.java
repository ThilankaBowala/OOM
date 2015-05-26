package middleware;

import java.io.BufferedReader;
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
    
    
    public static void main(String args[]) {
        ServerSocket c;
        Socket cs;
        OutputStream out;
        BufferedReader in;
        String name;
        String parameterString;

        try {
            c = new ServerSocket(60003);
            cs = c.accept();
            out = cs.getOutputStream();
            in = new BufferedReader(new InputStreamReader(cs.getInputStream()));

            name = in.readLine();//////////////////////////////////
            parameterString = in.readLine();///////////////////////

            String[] items = parameterString.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
            int[] parameter = new int[items.length];

            for (int i = 0; i < items.length; i++) {
                try {
                    parameter[i] = Integer.parseInt(items[i]);
                } catch (NumberFormatException nfe) {
                }
            }

            if ("selectionSort".equals(name)) {
                selectionSort(parameter);
            }

            out.write(results.toString().getBytes(), 0, results.length);

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}

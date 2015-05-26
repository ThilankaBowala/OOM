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
public class Server2 {
    static int[] results;
    
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
    
    
    public static void main(String args[]) {
        ServerSocket c;
        Socket cs;
        OutputStream out;
        BufferedReader in;
        String name;
        String parameterString;

        try {
            c = new ServerSocket(60002);
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

            if ("insertionSort".equals(name)) {
                insertionSort(parameter);
            }

            out.write(results.toString().getBytes(), 0, results.length);

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}

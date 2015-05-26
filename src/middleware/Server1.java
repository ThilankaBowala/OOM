package middleware;

import java.io.*;
import java.net.*;

/**
 *
 * @author 2012cs024
 */
public class Server1 {

    static int[] results;

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

    public static void main(String args[]) {
        ServerSocket c;
        Socket cs;
        OutputStream out;
        BufferedReader in;
        String name;
        String parameterString;

        try {
            c = new ServerSocket(60001);
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

            if ("bubbleSort".equals(name)) {
                bubbleSort(parameter);
            }

            out.write(results.toString().getBytes(), 0, results.length);

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}

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
public class Server4 {

    static int[] results;

    public static void quickSort(int[] toSort) {
        results = toSort;
        int left = 0;
        int right = results.length - 1;
        sort(left, right);
    }

    private static void sort(int left, int right) {
        if (left >= right) {
            return;
        }
        double pivot = results[right];
        int partition = partition(left, right, pivot);
        sort(0, partition - 1);
        sort(partition + 1, right);
    }

    private static int partition(int left, int right, double pivot) {
        int leftCursor = left - 1;
        int rightCursor = right;
        while (leftCursor < rightCursor) {
            while (results[++leftCursor] < pivot);
            while (rightCursor > 0 && results[--rightCursor] > pivot);
            if (leftCursor >= rightCursor) {
                break;
            } else {
                swap(leftCursor, rightCursor);
            }
        }
        swap(leftCursor, right);
        return leftCursor;
    }

    private static void swap(int left, int right) {
        int temp = results[left];
        results[left] = results[right];
        results[right] = temp;
    }
    
    
    public static void main(String args[]) {
        ServerSocket c;
        Socket cs;
        OutputStream out;
        BufferedReader in;
        String name;
        String parameterString;

        try {
            c = new ServerSocket(60004);
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

            if ("quickSort".equals(name)) {
                quickSort(parameter);
            }

            out.write(results.toString().getBytes(), 0, results.length);

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}

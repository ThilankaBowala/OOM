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
public class Server4 {

    static int[] results;
    static ServerSocket socketWithServerSkeleton;
    static Socket connectToServerSkeleton;
    static OutputStream outWithServerSkeleton;
    static BufferedReader inWithServerSkeleton;
    static String request;
    static String parameterString;

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
    
    
    static void getRequestFromServerSkeleton() throws IOException {
        socketWithServerSkeleton = new ServerSocket(60004);
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
            parameterString = parameterString.replace(null, "quickSort"); // not sure

            String[] items = parameterString.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
            int[] parameter = new int[items.length];

            for (int i = 0; i < items.length; i++) {
                try {
                    parameter[i] = Integer.parseInt(items[i]);
                } catch (NumberFormatException nfe) {
                    System.out.println(nfe);
                }
            }

            quickSort(parameter);
            sendResponceToServerSkeleton();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}

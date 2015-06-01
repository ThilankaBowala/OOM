package middleware;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2012cs024
 */
public class Middleware {

    public static void main(String[] args) {
//        try {
//            ClientStub.socketWithClient = new ServerSocket(5555);
//            ClientApplication1.connectToServer = new Socket("localhost", 5555);
            
            Server1 s1 = new Server1();
            Server2 s2 = new Server2();
            Server3 s3 = new Server3();
            Server4 s4 = new Server4();

            ServerSkeleton ss = new ServerSkeleton();
            ClientStub cs = new ClientStub();
            ClientApplication1 c1 = new ClientApplication1();

            s1.setPriority(1);
            s2.setPriority(1);
            s2.setPriority(1);
            s2.setPriority(1);

            ss.setPriority(2);
            cs.setPriority(3);
            c1.setPriority(4);

            s1.start();
            s2.start();
            s3.start();
            s4.start();
            ss.start();
            cs.start();
            if(cs.isAlive()){
                c1.start();
            }
//        } catch (IOException ex) {
//            Logger.getLogger(Middleware.class.getName()).log(Level.SEVERE, null, ex);
//        }
        

    }
}

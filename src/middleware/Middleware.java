package middleware;

/**
 *
 * @author 2012cs024
 */
public class Middleware {

    public static void main(String[] args) {

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

        ss.setPriority(1);
        cs.setPriority(1);
        c1.setPriority(1);

        s1.start();
        s2.start();
        s3.start();
        s4.start();
        ss.start();
        c1.start();
        cs.start();
       

    }
}

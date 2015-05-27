package middleware;

/**
 *
 * @author 2012cs024
 */
public class Middleware {

    public static void main(String[] args) {
        Server1.main(args);
        Server2.main(args);
        Server3.main(args);
        Server4.main(args);
        
        ServerSkeleton.main(args);
        ClientStub.main(args);
        
        ClientApplication1.main(args);
        ClientApplication2.main(args);
    }
}

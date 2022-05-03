import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final String HOST = "8.8.8.8";
    public static void main(String[] args) throws IOException {
        while(true) {
            try {
                InetAddress address = InetAddress.getByName(HOST);
                if (!address.isReachable(800)) {
                    System.out.println("Internet has gone down");
                }
                Thread.sleep(TimeUnit.MINUTES.toMillis(1));
            } catch (Exception e) {
                System.err.println("[Error] " + e.getMessage());
            }
        }
    }
}

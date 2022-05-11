import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class InternetConnectionMonitor implements Runnable {
    private static final String HOST = "8.8.8.8";
    private final List<InternetConnectionObserver> observers = new ArrayList<>();
    private boolean internetDisconnected = false;
    
    @Override
    public void run() {
        while(Thread.currentThread().isAlive()) {
            try {
                InetAddress address = InetAddress.getByName(HOST);
                if (!address.isReachable(800)) {
                    internetDisconnected = true;
                    observers.forEach(InternetConnectionObserver::onConnectionDrop);
                } else {
                    if(internetDisconnected) {
                        internetDisconnected = false;
                        observers.forEach(InternetConnectionObserver::onReconnect);
                    }
                }
                Thread.sleep(TimeUnit.MINUTES.toMillis(1));
            } catch (Exception e) {
                observers.forEach(InternetConnectionObserver::onConnectionDrop);
            }
        }
    }

    public void addObserver(InternetConnectionObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(InternetConnectionObserver observer) {
        observers.remove(observer);
    }

    public List<InternetConnectionObserver> getObservers() {
        return observers;
    }
}

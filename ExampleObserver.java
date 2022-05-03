
public class ExampleObserver implements InternetConnectionObserver {

    @Override
    public void onConnectionDrop() {
        System.out.println("Internet has gone down");
    }

    @Override
    public void onReconnect() {
        System.out.println("Internet is back up");
    }
}

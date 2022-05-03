public class Main {
    public static void main(String[] args) {
        InternetConnectionMonitor monitor = new InternetConnectionMonitor();
        monitor.addObserver(new ExampleObserver());
        new Thread(monitor).start();
    }
}

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MainClient {
    public static void main(String[] args) throws IOException {
        DatagramSocketClient socketClient = new DatagramSocketClient();

        socketClient.init("192.168.22.113",5555);

        socketClient.runClient();
    }
}
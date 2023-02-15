import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.Scanner;

public class DatagramSocketClient {
    InetAddress serverIP;
    int serverPort;
    DatagramSocket socket;

    public void init(String host, int port) throws SocketException,
            UnknownHostException {
        serverIP = InetAddress.getByName(host);
        serverPort = port;
        socket = new DatagramSocket();
    }

    public void runClient() throws IOException {
        byte [] receivedData = new byte[1024];
        byte [] sendingData;

//a l'inici
        sendingData = getFirstRequest();
//el servidor atén el port indefinidament
        while(mustContinue(sendingData)){
            DatagramPacket packet = new DatagramPacket(sendingData,
                    sendingData.length,
                    serverIP,
                    serverPort);
//enviament de la resposta
            socket.send(packet);

//creació del paquet per rebre les dades
            packet = new DatagramPacket(receivedData, 1024);
//espera de les dades
            socket.receive(packet);
//processament de les dades rebudes i obtenció de la resposta
            sendingData = getDataToRequest(packet.getData(), packet.getLength());
        }
    }

    private byte[] getDataToRequest(byte[] data, int length) {
//procés diferent per cada aplicació
        String rebut = new String(data, 0,length);
        System.out.println("Respuesta del server : \n" + rebut +"\n");

        System.out.println("Introduce el mensaje que quieres enviar al sevidor");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        return str.getBytes();
    }

    private byte[] getFirstRequest() {
        System.out.println("Introduce el nombre de usuario");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
//procés diferent per cada aplicació
        return name.getBytes();
    }

    private boolean mustContinue(byte[] sendingData) {
        String str = "adios";
    //procés diferent per cada aplicació
        return !Arrays.equals(sendingData, str.getBytes());
    }
}

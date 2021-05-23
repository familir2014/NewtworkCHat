

import ClientHandler.ClientHandler;
//import com.sun.deploy.net.MessageHeader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;


public class Server {


    private static ServerSocket server;
    private static Socket socket;
    private static final int PORT = 8189;
    private final MessageHeader client;
    private List<ClientHandler> clients;
    private AuthService authService;

    public Server(MessageHeader client) {
        authService = new SimpleAuthService();
        this.client = client;
        clients = new CopyOnWriteArrayList<>();
        try {
            server = new ServerSocket(PORT);
            System.out.println("Server start");
            while (true) {
                Socket socket = server.accept();
                System.out.println(socket.getLocalSocketAddress());
                System.out.println("Client Login: " + socket.getRemoteSocketAddress());
                subscribe(new ClientHandler(this, socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {


        try {
            Socket clientSocket = null;
            ServerSocket serverSocket = new ServerSocket(8189);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Server started!");
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
            clientSocket = serverSocket.accept();
            System.out.println("Клиент подключен " + clientSocket.getRemoteSocketAddress());

//            socket = server.accept();
//            System.out.println("Client connected!");
            Thread threadVision = new Thread();
            {
                try {
                    while (true) {
                        try {
                            outputStream.writeUTF(scanner.nextLine());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    threadVision.start();
                    threadVision.setDaemon(true);


                    while (true) {

                        String str = inputStream.readUTF();
                        if (str.equals("/end")) {
                            System.out.println("Клиент вышел с сервера");
                            outputStream.writeUTF("/end");
                            break;
                        } else {
                            System.out.println(" " + str);
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        server.close();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcastMSg(String msg) {
        Iterable<? extends ClientHandler> clients = null;
        for (ClientHandler c : clients) {
            c.sendMsg(msg);
        }

    }

    public void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

        public void unsubscribe(ClientHandler clientHandler){
            clients.remove(clientHandler);

        }


    public AuthService getAuthService() {
        return authService;
    }
}

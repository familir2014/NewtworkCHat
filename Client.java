
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

    public class Client {

        private static final String ServerAdr = "hosts";
        private static final int ServerPort = 8189;

        public static void main(String[] args) throws IOException {

            Socket socket = null;
            Scanner scanner = new Scanner(System.in);
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            try {
                socket = new Socket(ServerAdr, ServerPort);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Thread threadVision = new Thread();
            {
                try {
                    while (true) {
                        outputStream.writeUTF(scanner.nextLine());
                    }
                } catch (IOException e) {
                    e.printStackTrace();

            }
            threadVision.start();
            threadVision.setDaemon(true);

            while (true) {
                String str = inputStream.readUTF();
                if (str.equals("/end")) {
                    System.out.println("Нет соединения");
                    outputStream.writeUTF("/end");
                    break;
                } else {
                    System.out.println(" " + str);
                }
            }
        }
    }
}

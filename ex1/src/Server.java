import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                while (true) {
                    GeometricObject geometricObject = (GeometricObject) ois.readObject();

                    if (geometricObject == null)
                        break;

                    double area = geometricObject.calculateArea();
                    oos.writeDouble(area);
                    oos.flush();
                }

                System.out.println("Client disconnected: " + socket.getInetAddress().getHostAddress());
                socket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

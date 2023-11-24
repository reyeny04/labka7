import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8888);
            System.out.println("Connected to server.");

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println("Enter the type of geometric object (Circle/C, Rectangle/R):");
                String objectType = reader.readLine().toUpperCase();

                if (objectType.equals("Q")) {
                    oos.writeObject(null);
                    break;
                }

                System.out.println("Enter parameters for the geometric object (comma-separated):");
                String[] parameters = reader.readLine().split(",");

                GeometricObject geometricObject;
                switch (objectType) {
                    case "C":
                        geometricObject = new Circle(Double.parseDouble(parameters[0]));
                        break;
                    case "R":
                        geometricObject = new Rectangle(Double.parseDouble(parameters[0]), Double.parseDouble(parameters[1]));
                        break;
                    default:
                        System.out.println("Invalid input. Please enter Circle/C or Rectangle/R.");
                        continue;
                }

                oos.writeObject(geometricObject);
                oos.flush();

                double area = ois.readDouble();
                System.out.println("Server response: Area = " + area);
            }

            System.out.println("Closing client.");
            socket.close();
            oos.close();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

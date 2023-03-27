import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private String nickname;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private Scanner scanner;


    public Client(Socket socket) throws IOException {
        this.socket = socket;
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
        scanner = new Scanner(System.in);
        System.out.print("Hello! Enter your nickname please : ");
        nickname = scanner.nextLine();
        System.out.println("Welcome to server \"schizophrenia\", " + nickname + "!");
    }

    public void start() {
        String message;
        try {
            while (true) {
                System.out.print(nickname + ": ");
                message = scanner.nextLine();
                output.println(nickname + ": " + message);
                input.readLine();
            }
        } catch (IOException e) {
            System.out.println("Can't register new client " + e.getMessage());
        }
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost", 8080);
        Client client = new Client(socket);
        client.start();
    }
}
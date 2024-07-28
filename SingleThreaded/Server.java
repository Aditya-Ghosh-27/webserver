import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    // A method to define all functionalities
    public void run() throws IOException{ // Run method
        int port = 8000;
        try{
            ServerSocket socket = new ServerSocket(port);  // opening a socket in the 2 way communication
            socket.setSoTimeout(10000);
            while(true){
                try{
                    System.out.println("Server is listening on PORT: " + port);
                    Socket acceptedConnection = socket.accept();
                    System.out.println("Connection accepted from client: " + acceptedConnection.getRemoteSocketAddress());
                    PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream());
                    BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
                    toClient.println("Hello from the server");
                    toClient.close();
                    fromClient.close();
                    acceptedConnection.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        } 
        catch(IOException e){
            System.err.println("Error creating ServerSocket: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try{
            server.run();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
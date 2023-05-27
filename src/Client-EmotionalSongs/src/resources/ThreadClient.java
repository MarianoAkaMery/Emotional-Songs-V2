package resources;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Thread for clients
 */
public class ThreadClient implements Runnable {

    private Socket socket;
    private BufferedReader cin;

    public ThreadClient(Socket socket) throws IOException {
        this.socket = socket;
        this.cin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        System.out.println("chaimo run");
    }

    
}
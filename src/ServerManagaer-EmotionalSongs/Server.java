import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private ServerSocket serverSocket;
    private List<ClientHandler> clients;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clients = new ArrayList<>();
            System.out.println("Server avviato sulla porta " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Nuova connessione accettata: " + socket);

                ClientHandler clientHandler = new ClientHandler(socket);
                clients.add(clientHandler);
                clientHandler.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void broadcast(Serializable data) {
        for (ClientHandler client : clients) {
            client.sendData(data);
        }
    }

    public static void main(String[] args) {
        int port = 8080;
        Server server = new Server(port);
        server.start();
    }

    private class ClientHandler extends Thread {
        private Socket socket;
        private ObjectInputStream inputStream;
        private ObjectOutputStream outputStream;

        public ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        }

        public void run() {
            try {
                while (true) {
                    Informazioni data = (Informazioni) inputStream.readObject();
                    data.display();
                    broadcast(data);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }

        public void sendData(Serializable data) {
            try {
                outputStream.writeObject(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void close() {
            try {
                if (outputStream != null)
                    outputStream.close();
                if (inputStream != null)
                    inputStream.close();
                if (socket != null)
                    socket.close();
                clients.remove(this);
                System.out.println("Connessione chiusa: " + socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
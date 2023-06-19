package server;

import java.io.*;
import java.net.*;
import java.util.*;
import resources.InformazioniRegister;
import resources.DbConnector;

/**
 * The Server class represents a server that accepts client connections and handles communication with clients.
 */
public class Server {
    private ServerSocket serverSocket;
    private List<ClientHandler> clients;

    /**
     * Constructs a Server object with the specified port.
     *
     * @param port the port number on which the server will listen for incoming connections
     */
    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clients = new ArrayList<>();
            System.out.println("Server avviato sulla porta " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the server and accepts client connections.
     */
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

    /**
     * Broadcasts data to all connected clients.
     *
     * @param data the data to broadcast
     */
    public synchronized void broadcast(Serializable data) {
        for (ClientHandler client : clients) {
            client.sendData(data);
            System.out.println("Dato ricevuto!!");
        }
    }

    /**
     * The main entry point of the server application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        int port = 1234;
        Server server = new Server(port);
        server.start();
    }

    /**
     * The ClientHandler class represents a client connection and handles communication with a client.
     */
    private class ClientHandler extends Thread {
        private Socket socket;
        private ObjectInputStream inputStream;
        private ObjectOutputStream outputStream;
        private boolean running;

        /**
         * Constructs a ClientHandler object with the specified socket.
         *
         * @param socket the socket representing the client connection
         * @throws IOException if an I/O error occurs when creating input/output streams
         */
        public ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            running = true;
        }

        /**
         * Starts the client handler thread.
         */
        public void run() {
            try {
                while (running) {
                    try {
                        InformazioniRegister data = new InformazioniRegister(getName(), getName(), getName(),
                                getName());
                        try {
                            data = (InformazioniRegister) inputStream.readObject();

                        } catch (EOFException e) {
                            // End of stream reached, client has disconnected
                            running = false;

                        } catch (SocketException e) {
                            // Connection interrupted
                            running = false;
                        }

                        if (data != null) {
                            String Action = data.getAction();
                            if (Action.equals("Register")) {
                                Boolean registrationSuccess;
                                DbConnector dbConnector = new DbConnector();
                                registrationSuccess = dbConnector.registerUser(data.getEmail(), data.getNome(),
                                        data.getPassword());
                                if (registrationSuccess) {
                                    broadcast(data);
                                }
                            }

                            if (Action.equals("Emotion")) {
                                DbConnector dbConnector = new DbConnector();
                                dbConnector.updateSongEmotion(data.getAmazementPoint(), data.getSolemnityPoint(),
                                        data.getTendernessPoint(), data.getNostalgiaPoint(), data.getCalmnessPoint(),
                                        data.getPowerPoint(), data.getJoyPoint(), data.getTensionPoint(),
                                        data.getSadnessPoint(), data.getCounter(), data.getSongID());
                                broadcast(data);

                            }

                            if (Action.equals("Playlist")) {
                                Boolean registrationSuccess;
                                DbConnector dbConnector = new DbConnector();
                                registrationSuccess = dbConnector.registerPlaylist(data.getUserID(),
                                        data.getPlaylistName(), data.getSongListbyUser());
                                if (registrationSuccess) {
                                    broadcast(data);
                                }

                            }

                        } else {
                            running = false;
                        }
                    } catch (EOFException e) {
                        // End of stream reached, client has disconnected
                        running = false;

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }

        /**
         * Sends data to the client.
         *
         * @param data the data to send
         */
        public void sendData(Serializable data) {
            try {
                outputStream.reset(); // Reset the stream state before writing a new object
                outputStream.writeObject(data);
                outputStream.flush(); // Flush the stream to ensure data is sent
            } catch (SocketException e) {
                // Handle socket exception (client disconnected)
                System.out.println("Client disconnected abruptly.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Closes the client connection and cleans up resources.
         */
        public void close() {
            try {
                if (outputStream != null) {
                    try {
                        outputStream.flush();
                        outputStream.close();
                    } catch (SocketException e) {
                        // Handle socket exception (client disconnected abruptly)
                        System.out.println("Client disconnected abruptly.");
                    }
                }
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

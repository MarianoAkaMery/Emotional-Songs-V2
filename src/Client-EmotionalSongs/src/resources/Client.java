package resources;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.List;

/**
 * The Client class represents a client that connects to a server using sockets and sends/receives data.
 */
public class Client {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    /**
     * Constructs a Client object and connects to the specified server address and port.
     *
     * @param serverAddress the server address
     * @param serverPort    the server port
     */
    public Client(String serverAddress, int serverPort) {
        try {
            socket = new Socket(serverAddress, serverPort);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connected to the server: " + serverAddress + ":" + serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends the provided serializable data to the server.
     *
     * @param data the data to send
     * @throws InterruptedException if an error occurs while sending the data
     */
    public void sendData(Serializable data) throws InterruptedException {
        try {
            outputStream.writeObject(data);
            outputStream.flush(); // Ensure data is sent immediately
        } catch (IOException e) {
            System.err.println("An error occurred while sending data to the server: " + e.getMessage());
            Thread.sleep(5000);
            // Handle the exception gracefully, e.g., display an error message or attempt to reconnect
        }
    }

    /**
     * Starts a separate thread to receive data from the server.
     */
    public void receiveData() {
        Thread thread = new Thread(() -> {
            try {
                while (!socket.isClosed()) {
                    System.out.println("Data received from the server: 200");
                    close();
                }
            } catch (Exception e) {
                // Handle any exceptions
            }
        });
        thread.start();
    }

    /**
     * Sends login information to the server.
     *
     * @param name     the user's name
     * @param email    the user's email
     * @param password the user's password
     * @param action   the action to perform
     * @throws InterruptedException if an error occurs while sending the data
     */
    public void startSendLogin(String name, String email, String password, String action) throws InterruptedException {
        InformazioniRegister info = new InformazioniRegister(name, email, password, action);
        sendData(info);
        receiveData();
    }

    /**
     * Sends playlist information to the server.
     *
     * @param userID       the user ID
     * @param playlistName the playlist name
     * @param songListByUser   the list of songs by the user
     * @param action       the action to perform
     * @throws InterruptedException if an error occurs while sending the data
     */
    public void startSendPlaylist(String userID, String playlistName, List<String> songListByUser, String action)
            throws InterruptedException {
        InformazioniRegister info = new InformazioniRegister(userID, playlistName, songListByUser, action);
        sendData(info);
        receiveData();
    }

    /**
     * Sends emotion song information to the server.
     *
     * @param amazementPoint   the amazement point
     * @param solemnityPoint   the solemnity point
     * @param tendernessPoint  the tenderness point
     * @param nostalgiaPoint   the nostalgia point
     * @param calmnessPoint    the calmness point
     * @param powerPoint       the power point
     * @param joyPoint         the joy point
     * @param tensionPoint     the tension point
     * @param sadnessPoint     the sadness point
     * @param counter          the counter
     * @param songID           the song ID
     * @param action           the action to perform
     * @throws InterruptedException if an error occurs while sending the data
     */
    public void startSendEmotionSong(int amazementPoint, int solemnityPoint, int tendernessPoint, int nostalgiaPoint,
                                     int calmnessPoint, int powerPoint, int joyPoint, int tensionPoint, int sadnessPoint,
                                     int counter, String songID, String action) throws InterruptedException {
        InformazioniRegister info = new InformazioniRegister(amazementPoint, solemnityPoint, tendernessPoint,
                nostalgiaPoint, calmnessPoint, powerPoint, joyPoint, tensionPoint, sadnessPoint, counter, songID,
                action);
        sendData(info);
        receiveData();
    }

    /**
     * Closes the client's socket, input/output streams, and displays a connection closed message.
     */
    public void close() {
        try {
            if (outputStream != null)
                outputStream.close();
            if (inputStream != null)
                inputStream.close();
            if (socket != null && !socket.isClosed())
                socket.close();
            System.out.println("Connection closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

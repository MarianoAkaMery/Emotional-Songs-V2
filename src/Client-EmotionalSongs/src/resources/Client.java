package resources;

import java.util.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class Client {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public Client(String serverAddress, int serverPort) {
        try {
            socket = new Socket(serverAddress, serverPort);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connesso al server: " + serverAddress + ":" + serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendData(Serializable data) throws InterruptedException {
        try {
            outputStream.writeObject(data);
            outputStream.flush(); // Ensure data is sent immediately
        } catch (IOException e) {
            System.err.println("An error occurred while sending data to the server: " + e.getMessage());
            Thread.sleep(5000);
            // Handle the exception gracefully, e.g., display an error message or attempt to
            // reconnect
        }
    }

    public void receiveData() {

        Thread thread = new Thread(() -> {
            try {
                while (!socket.isClosed()) {
                    System.out.println("Dato ricevuto dal server: 200");
                    close();
                }
            } catch (Exception e) {
            }
        });
        thread.start();

    }

    public void startSendLogin(String name, String email, String password, String action) throws InterruptedException {

        InformazioniRegister info = new InformazioniRegister(name, email, password, action);

        sendData(info);

        receiveData();
    }

    public void startSendPlaylist(String userID, String PlaylistName, List<String> SongListbyUser, String action)
            throws InterruptedException {

        InformazioniRegister info = new InformazioniRegister(userID, PlaylistName, SongListbyUser, action);

        sendData(info);

        receiveData();
    }

    public void startSendEmotionSong(int AmazementPoint, int SolemnityPoint, int TendernessPoint, int NostalgiaPoint,
            int CalmnessPoint, int PowerPoint, int JoyPoint, int TensionPoint, int SadnessPoint, int counter,
            String songID, String action) throws InterruptedException {

        InformazioniRegister info = new InformazioniRegister(AmazementPoint, SolemnityPoint, TendernessPoint,
                NostalgiaPoint, CalmnessPoint, PowerPoint, JoyPoint, TensionPoint, SadnessPoint, counter, songID,
                action);

        sendData(info);

        receiveData();

    }

    public void close() {
        try {
            if (outputStream != null)
                outputStream.close();
            if (inputStream != null)
                inputStream.close();
            if (socket != null && !socket.isClosed())
                socket.close();
            System.out.println("Connessione chiusa");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

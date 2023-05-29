import java.io.*;
import java.net.*;
import java.util.Scanner;

import javax.sound.midi.MidiDevice.Info;

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

    public void sendData(Serializable data) {
        try {
            outputStream.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveData() {
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    Informazioni data = (Informazioni) inputStream.readObject();
                    System.out.println("Dato ricevuto dal server: " );
                    data.display();
                    
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public void start() {
        receiveData();

        
        while (true) {
            
           boolean flag = true;

            Informazioni info = new Informazioni("ema", 21);
            sendData(info);


            // premo pulsante exit flag = false
            if (flag == false)
            break;
        }
        close();
    }

    public void close() {
        try {
            if (outputStream != null)
                outputStream.close();
            if (inputStream != null)
                inputStream.close();
            if (socket != null)
                socket.close();
            System.out.println("Connessione chiusa");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 8080;
        Client client = new Client(serverAddress, serverPort);
        client.start();
    }
}

package resources;

import java.util.List;
import java.io.Serializable;

public class InformazioniRegister implements Serializable {
    private static final long serialVersionUID = 1;

    private String Nome;
    private String Email;
    private String Password;
    public String Action;
    private int AmazementPoint;
    private int SolemnityPoint;
    private int TendernessPoint;
    private int NostalgiaPoint;
    private int CalmnessPoint;
    private int PowerPoint;
    private int JoyPoint;
    private int TensionPoint;
    private int SadnessPoint;
    private int counter;
    private String songID;
    private String UserID;
    private String PlaylistName;
    private List<String> SongListbyUser;

    public InformazioniRegister(String nome, String email, String password, String action) {

        this.Nome = nome;
        this.Email = email;
        this.Password = password;
        this.Action = action;

    }

    public InformazioniRegister(String userID, String playlistName, List<String> songListbyUser, String action) {

        this.UserID = userID;
        this.PlaylistName = playlistName;
        this.SongListbyUser = songListbyUser;
        this.Action = action;

    }

    public InformazioniRegister(int amazementPoint, int solemnityPoint, int tendernessPoint, int nostalgiaPoint,
            int calmnessPoint, int powerPoint, int joyPoint, int tensionPoint, int sadnessPoint, int counter,
            String songID, String action) {
        this.AmazementPoint = amazementPoint;
        this.SolemnityPoint = solemnityPoint;
        this.TendernessPoint = tendernessPoint;
        this.NostalgiaPoint = nostalgiaPoint;
        this.CalmnessPoint = calmnessPoint;
        this.PowerPoint = powerPoint;
        this.JoyPoint = joyPoint;
        this.TensionPoint = tensionPoint;
        this.SadnessPoint = sadnessPoint;
        this.counter = counter;
        this.songID = songID;
        this.Action = action;

    }

    public void printaction() {
        System.out.println(Action);
    }

    public String getNome() {
        return this.Nome;
    }

    public String getUserID() {
        return this.UserID;
    }

    public String getPlaylistName() {
        return this.PlaylistName;
    }

    public List<String> getSongListbyUser() {
        return this.SongListbyUser;
    }

    public String getEmail() {
        return this.Email;
    }

    public String getPassword() {
        return this.Password;
    }

    public String getAction() {
        return this.Action;
    }

    public int getAmazementPoint() {
        return this.AmazementPoint;
    }

    public int getSolemnityPoint() {
        return this.SolemnityPoint;
    }

    public int getTendernessPoint() {
        return this.TendernessPoint;
    }

    public int getNostalgiaPoint() {
        return this.NostalgiaPoint;
    }

    public int getCalmnessPoint() {
        return this.CalmnessPoint;
    }

    public int getPowerPoint() {
        return this.PowerPoint;
    }

    public int getJoyPoint() {
        return this.JoyPoint;
    }

    public int getTensionPoint() {
        return this.TensionPoint;
    }

    public int getSadnessPoint() {
        return this.SadnessPoint;
    }

    public int getCounter() {
        return this.counter;
    }

    public String getSongID() {
        return this.songID;
    }

}

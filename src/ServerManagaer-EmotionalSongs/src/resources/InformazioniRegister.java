package resources;

import java.util.List;
import java.io.Serializable;

/**
 * The InformazioniRegister class represents information related to user registration, emotions, and playlists.
 * It implements the Serializable interface to support object serialization.
 */
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

    /**
     * Constructs an InformazioniRegister object for user registration.
     *
     * @param nome     the name of the user
     * @param email    the email address of the user
     * @param password the password of the user
     * @param action   the action associated with the information (e.g., "Register")
     */
    public InformazioniRegister(String nome, String email, String password, String action) {

        this.Nome = nome;
        this.Email = email;
        this.Password = password;
        this.Action = action;

    }

    /**
     * Constructs an InformazioniRegister object for playlist registration.
     *
     * @param userID         the ID of the user
     * @param playlistName   the name of the playlist
     * @param songListbyUser the list of songs in the playlist
     * @param action         the action associated with the information (e.g., "Playlist")
     */
    public InformazioniRegister(String userID, String playlistName, List<String> songListbyUser, String action) {

        this.UserID = userID;
        this.PlaylistName = playlistName;
        this.SongListbyUser = songListbyUser;
        this.Action = action;

    }

    /**
     * Constructs an InformazioniRegister object for updating song emotions.
     *
     * @param amazementPoint  the amazement point value
     * @param solemnityPoint  the solemnity point value
     * @param tendernessPoint the tenderness point value
     * @param nostalgiaPoint  the nostalgia point value
     * @param calmnessPoint   the calmness point value
     * @param powerPoint      the power point value
     * @param joyPoint        the joy point value
     * @param tensionPoint    the tension point value
     * @param sadnessPoint    the sadness point value
     * @param counter         the counter value
     * @param songID          the ID of the song
     * @param action          the action associated with the information (e.g., "Emotion")
     */
    public InformazioniRegister(int amazementPoint, int solemnityPoint, int tendernessPoint, int nostalgiaPoint,
                                int calmnessPoint, int powerPoint, int joyPoint, int tensionPoint, int sadnessPoint,
                                int counter, String songID, String action) {
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

    /**
     * Prints the action associated with the information.
     */
    public void printaction() {
        System.out.println(Action);
    }

    /**
     * Gets the name of the user.
     *
     * @return the name of the user
     */
    public String getNome() {
        return this.Nome;
    }

    /**
     * Gets the ID of the user.
     *
     * @return the ID of the user
     */
    public String getUserID() {
        return this.UserID;
    }

    /**
     * Gets the name of the playlist.
     *
     * @return the name of the playlist
     */
    public String getPlaylistName() {
        return this.PlaylistName;
    }

    /**
     * Gets the list of songs in the playlist.
     *
     * @return the list of songs in the playlist
     */
    public List<String> getSongListbyUser() {
        return this.SongListbyUser;
    }

    /**
     * Gets the email address of the user.
     *
     * @return the email address of the user
     */
    public String getEmail() {
        return this.Email;
    }

    /**
     * Gets the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return this.Password;
    }

    /**
     * Gets the action associated with the information.
     *
     * @return the action associated with the information
     */
    public String getAction() {
        return this.Action;
    }

    /**
     * Gets the amazement point value.
     *
     * @return the amazement point value
     */
    public int getAmazementPoint() {
        return this.AmazementPoint;
    }

    /**
     * Gets the solemnity point value.
     *
     * @return the solemnity point value
     */
    public int getSolemnityPoint() {
        return this.SolemnityPoint;
    }

    /**
     * Gets the tenderness point value.
     *
     * @return the tenderness point value
     */
    public int getTendernessPoint() {
        return this.TendernessPoint;
    }

    /**
     * Gets the nostalgia point value.
     *
     * @return the nostalgia point value
     */
    public int getNostalgiaPoint() {
        return this.NostalgiaPoint;
    }

    /**
     * Gets the calmness point value.
     *
     * @return the calmness point value
     */
    public int getCalmnessPoint() {
        return this.CalmnessPoint;
    }

    /**
     * Gets the power point value.
     *
     * @return the power point value
     */
    public int getPowerPoint() {
        return this.PowerPoint;
    }

    /**
     * Gets the joy point value.
     *
     * @return the joy point value
     */
    public int getJoyPoint() {
        return this.JoyPoint;
    }

    /**
     * Gets the tension point value.
     *
     * @return the tension point value
     */
    public int getTensionPoint() {
        return this.TensionPoint;
    }

    /**
     * Gets the sadness point value.
     *
     * @return the sadness point value
     */
    public int getSadnessPoint() {
        return this.SadnessPoint;
    }

    /**
     * Gets the counter value.
     *
     * @return the counter value
     */
    public int getCounter() {
        return this.counter;
    }

    /**
     * Gets the ID of the song.
     *
     * @return the ID of the song
     */
    public String getSongID() {
        return this.songID;
    }

}

package resources;

import java.util.List;
import java.io.Serializable;

/**
 * The InformazioniRegister class represents registration information for the application.
 * It holds various properties related to user registration and actions.
 */
public class InformazioniRegister implements Serializable {
    private static final long serialVersionUID = 1L;

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
     * Constructs a new InformazioniRegister object with the provided name, email, password, and action.
     *
     * @param nome     The name of the user
     * @param email    The email address of the user
     * @param password The password of the user
     * @param action   The action associated with the registration
     */
    public InformazioniRegister(String nome, String email, String password, String action) {
        this.Nome = nome;
        this.Email = email;
        this.Password = password;
        this.Action = action;
    }

    /**
     * Constructs a new InformazioniRegister object with the provided user ID, playlist name,
     * song list by user, and action.
     *
     * @param userID        The ID of the user
     * @param playlistName  The name of the playlist
     * @param songListbyUser The list of songs associated with the user
     * @param action        The action associated with the registration
     */
    public InformazioniRegister(String userID, String playlistName, List<String> songListbyUser, String action) {
        this.UserID = userID;
        this.PlaylistName = playlistName;
        this.SongListbyUser = songListbyUser;
        this.Action = action;
    }

    /**
     * Constructs a new InformazioniRegister object with the provided points, counter, song ID, and action.
     *
     * @param amazementPoint The amazement point value
     * @param solemnityPoint The solemnity point value
     * @param tendernessPoint The tenderness point value
     * @param nostalgiaPoint The nostalgia point value
     * @param calmnessPoint The calmness point value
     * @param powerPoint The power point value
     * @param joyPoint The joy point value
     * @param tensionPoint The tension point value
     * @param sadnessPoint The sadness point value
     * @param counter The counter value
     * @param songID The song ID
     * @param action The action associated with the registration
     */
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

    /**
     * Prints the action associated with the registration.
     */
    public void printAction() {
        System.out.println(Action);
    }

    // Getters for the properties

    /**
     * Returns the name of the user.
     *
     * @return The name of the user
     */
    public String getNome() {
        return this.Nome;
    }

    /**
     * Returns the ID of the user.
     *
     * @return The ID of the user
     */
    public String getUserID() {
        return this.UserID;
    }

    /**
     * Returns the name of the playlist.
     *
     * @return The name of the playlist
     */
    public String getPlaylistName() {
        return this.PlaylistName;
    }

    /**
     * Returns the list of songs associated with the user.
     *
     * @return The list of songs associated with the user
     */
    public List<String> getSongListbyUser() {
        return this.SongListbyUser;
    }

    /**
     * Returns the email address of the user.
     *
     * @return The email address of the user
     */
    public String getEmail() {
        return this.Email;
    }

    /**
     * Returns the password of the user.
     *
     * @return The password of the user
     */
    public String getPassword() {
        return this.Password;
    }

    /**
     * Returns the action associated with the registration.
     *
     * @return The action associated with the registration
     */
    public String getAction() {
        return this.Action;
    }

    /**
     * Returns the amazement point value.
     *
     * @return The amazement point value
     */
    public int getAmazementPoint() {
        return this.AmazementPoint;
    }

    /**
     * Returns the solemnity point value.
     *
     * @return The solemnity point value
     */
    public int getSolemnityPoint() {
        return this.SolemnityPoint;
    }

    /**
     * Returns the tenderness point value.
     *
     * @return The tenderness point value
     */
    public int getTendernessPoint() {
        return this.TendernessPoint;
    }

    /**
     * Returns the nostalgia point value.
     *
     * @return The nostalgia point value
     */
    public int getNostalgiaPoint() {
        return this.NostalgiaPoint;
    }

    /**
     * Returns the calmness point value.
     *
     * @return The calmness point value
     */
    public int getCalmnessPoint() {
        return this.CalmnessPoint;
    }

    /**
     * Returns the power point value.
     *
     * @return The power point value
     */
    public int getPowerPoint() {
        return this.PowerPoint;
    }

    /**
     * Returns the joy point value.
     *
     * @return The joy point value
     */
    public int getJoyPoint() {
        return this.JoyPoint;
    }

    /**
     * Returns the tension point value.
     *
     * @return The tension point value
     */
    public int getTensionPoint() {
        return this.TensionPoint;
    }

    /**
     * Returns the sadness point value.
     *
     * @return The sadness point value
     */
    public int getSadnessPoint() {
        return this.SadnessPoint;
    }

    /**
     * Returns the counter value.
     *
     * @return The counter value
     */
    public int getCounter() {
        return this.counter;
    }

    /**
     * Returns the song ID.
     *
     * @return The song ID
     */
    public String getSongID() {
        return this.songID;
    }
}

package resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;
import java.time.LocalDate;
import java.sql.Date;

/**
 * A class that handles database connectivity and various operations on the
 * database.
 */

public class DbConnector {

    private final String url = "jdbc:postgresql://localhost/postgres";
    private final String user = "postgres";
    private final String password = "ciao1234";

    /**
     * Establishes a connection to the PostgreSQL database.
     *
     * @return The database connection object.
     */

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Checks if a user with the given email exists in the database.
     *
     * @param emailToCheck The email to check for existence.
     * @return True if the user exists, False otherwise.
     */

    public Boolean checkUserExist(String emailToCheck) {
        Boolean check = false;
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"Users\"");
            while (rs.next()) {
                String emailInDb = rs.getString("UserEmail");
                if (emailToCheck.equals(emailInDb)) {
                    check = true;
                }
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return check;
    }

    /**
     * Registers a new user in the database.
     *
     * @param emailToRegister    The email of the user to register.
     * @param UsernameToRegister The username of the user to register.
     * @param passwordToRegister The password of the user to register.
     * @return True if the user registration is successful, False otherwise.
     */

    public Boolean registerUser(String emailToRegister, String UsernameToRegister, String passwordToRegister) {
        Boolean check = false;
        Connection conn = null;
        Statement stmt = null;
        Random random = new Random();
        int randomId = random.nextInt(Integer.MAX_VALUE);
        String completeId = ("USER - " + Integer.toString(randomId));

        LocalDate currentDate = LocalDate.now();
        Date date = Date.valueOf(currentDate);
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            String query = "INSERT INTO public.\"Users\" (\"UserID\",\"UserEmail\",\"UserName\",\"UserPassword\",\"UserLastUse\") VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, completeId);
            pstmt.setString(2, emailToRegister);
            pstmt.setString(3, UsernameToRegister);
            pstmt.setString(4, passwordToRegister);
            pstmt.setDate(5, date);
            pstmt.executeUpdate();
            pstmt.close();
            stmt.close();
            conn.close();
            check = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return check;
    }

    /**
     * Registers a new playlist in the database.
     *
     * @param userID         The ID of the user who owns the playlist.
     * @param PlaylistName   The name of the playlist.
     * @param SongListbyUser The list of song IDs in the playlist.
     * @return True if the playlist registration is successful, False otherwise.
     */

    public Boolean registerPlaylist(String userID, String PlaylistName, List<String> SongListbyUser) {
        Boolean check = false;
        Connection conn = null;
        Statement stmt = null;
        Random random = new Random();
        int randomId = random.nextInt(Integer.MAX_VALUE);
        String completeId = ("PLAYLIST - " + Integer.toString(randomId));
        LocalDate currentDate = LocalDate.now();
        Date date = Date.valueOf(currentDate);
        // ignore
        List<String> songList = SongListbyUser;
        String[] songArray = songList.toArray(new String[0]);

        try {
            conn = DriverManager.getConnection(url, user, password);
            java.sql.Array songArraySql = conn.createArrayOf("text", songArray);

            stmt = conn.createStatement();
            String query = "INSERT INTO public.\"Playlists\" (\"PlaylistID\",\"UserID\",\"SongList\",\"PlaylistName\",\"CreationDate\") VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, completeId);
            pstmt.setString(2, userID);
            pstmt.setArray(3, songArraySql);
            pstmt.setString(4, PlaylistName);
            pstmt.setDate(5, date);
            pstmt.executeUpdate();

            String query2 = "SELECT \"UserPlaylists\" FROM public.\"Users\" WHERE \"UserID\"= ?";
            PreparedStatement pstmt2 = conn.prepareStatement(query2);
            pstmt2.setString(1, userID);
            ResultSet rs = pstmt2.executeQuery();
            java.sql.Array SongPlaylistList = null;
            while (rs.next()) {
                try {
                    SongPlaylistList = rs.getArray("UserPlaylists");
                } catch (Exception e) {
                }
            }

            System.out.println(SongPlaylistList);

            java.sql.Array updatedSongPlaylistList;
            if (SongPlaylistList == null) {

                List<Object> newArray = new ArrayList<>();
                newArray.add(completeId);
                Object[] updatedArray = newArray.toArray();
                updatedSongPlaylistList = conn.createArrayOf("text", updatedArray);
            } else {

                Object[] existingArray = (Object[]) SongPlaylistList.getArray();
                List<Object> newArray = new ArrayList<>(List.of(existingArray));
                newArray.add(completeId);
                Object[] updatedArray = newArray.toArray();
                updatedSongPlaylistList = conn.createArrayOf("text", updatedArray);
            }

            String query3 = "UPDATE public.\"Users\"  SET \"UserPlaylists\"= ? WHERE \"UserID\"= ?";
            PreparedStatement pstmt3 = conn.prepareStatement(query3);
            pstmt3.setArray(1, updatedSongPlaylistList);
            pstmt3.setString(2, userID);

            pstmt3.executeUpdate();

            pstmt.close();
            pstmt2.close();
            pstmt3.close();
            stmt.close();
            conn.close();
            check = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return check;
    }

    /**
     * Updates the "UserLastUse" field for a user with the given email in the
     * database.
     *
     * @param email The email of the user.
     * @return True if the update is successful, False otherwise.
     */
    public void updateLastUse(String email) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        LocalDate currentDate = LocalDate.now();
        Date date = Date.valueOf(currentDate);
        try {
            conn = DriverManager.getConnection(url, user, password);
            String query = "UPDATE public.\"Users\" SET \"UserLastUse\" = ? WHERE \"UserEmail\" = ?;";
            pstmt = conn.prepareStatement(query);
            pstmt.setDate(1, date);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates the emotion scores for a song in the database.
     *
     * @param Amazement   The score for amazement emotion.
     * @param Solemnity   The score for solemnity emotion.
     * @param Tenderness  The score for tenderness emotion.
     * @param Nostalgia   The score for nostalgia emotion.
     * @param Calmness    The score for calmness emotion.
     * @param Power       The score for power emotion.
     * @param Joy         The score for joy emotion.
     * @param Tension     The score for tension emotion.
     * @param Sadness     The score for sadness emotion.
     * @param ReviewTotal The total number of reviews for the song.
     * @param SongID      The ID of the song.
     * @return True if the update is successful, False otherwise.
     */

    public void updateSongEmotion(int Amazement, int Solemnity, int Tenderness, int Nostalgia, int Calmness, int Power,
            int Joy, int Tension, int Sadness, int ReviewTotal, String SongID) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            String query = "UPDATE public.\"Songs\" SET \"Amazement\"=?, \"Solemnity\"=?, \"Tenderness\"=?, \"Nostalgia\"=?, \"Calmness\"=?, \"Power\"=?, \"Joy\"=?, \"Tension\"=?, \"Sadness\"=?, \"ReviewTotal\"=? WHERE \"SongID\" = ?;";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Amazement);
            pstmt.setInt(2, Solemnity);
            pstmt.setInt(3, Tenderness);
            pstmt.setInt(4, Nostalgia);
            pstmt.setInt(5, Calmness);
            pstmt.setInt(6, Power);
            pstmt.setInt(7, Joy);
            pstmt.setInt(8, Tension);
            pstmt.setInt(9, Sadness);
            pstmt.setInt(10, ReviewTotal);
            pstmt.setString(11, SongID);

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks the login status of a user by verifying the provided email and
     * password.
     *
     * @param emailGiven    The email provided by the user.
     * @param passwordGiven The password provided by the user.
     * @return true if the email and password match an entry in the "Users" table,
     *         false otherwise.
     */

    public Boolean getLoginStatus(String emailGiven, String passwordGiven) {
        Connection conn = null;
        Statement stmt = null;
        Boolean check = false;
        String emailList = null;
        String passwordList = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"Users\"");
            while (rs.next()) {
                emailList = rs.getString("UserEmail");
                passwordList = rs.getString("UserPassword");
                if (emailList.equals(emailGiven) & passwordList.equals(passwordGiven)) {
                    check = true;
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return check;
    }

    /**
     * Retrieves the email addresses of all users from the "Users" table.
     *
     * @return An array of email addresses.
     */

    public String[] getUserInfo() {
        Connection conn = null;
        Statement stmt = null;
        String UserEmail = null;
        int index = 0;
        String[] EmailList = new String[3000];
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"Users\"");
            while (rs.next()) {
                UserEmail = rs.getString("UserEmail");
                EmailList[index] = UserEmail;
                index++;
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return EmailList;
    }

    /**
     * Retrieves the names of all playlists from the "Playlist" table.
     *
     * @return An array of playlist names.
     */
    public String[] getPlaylistInfo() {
        Connection conn = null;
        Statement stmt = null;
        String playlistName = null;
        int index = 0;
        String[] PlaylistList = new String[3000];
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"Playlist\"");
            while (rs.next()) {
                playlistName = rs.getString("PlaylistName");
                PlaylistList[index] = playlistName;
                index++;
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return PlaylistList;
    }

    /**
     * Retrieves the titles of all songs from the "Songs" table.
     *
     * @return An array of song titles.
     */

    public String[] getSongsInfo() {
        Connection conn = null;
        Statement stmt = null;
        String songName = null;
        int index = 0;
        String[] SongList = new String[8000];
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"Songs\"");
            while (rs.next()) {
                songName = rs.getString("Title");
                SongList[index] = songName;
                index++;
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return SongList;
    }

    /**
     * Retrieves the years of all songs from the "Songs" table.
     *
     * @return An array of song years.
     */

    public String[] getSongsInfoYear() {
        Connection conn = null;
        Statement stmt = null;
        String songYear = null;

        int index = 0;
        String[] SongListYear = new String[8000];

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"Songs\"");
            while (rs.next()) {

                songYear = rs.getString("Year");
                SongListYear[index] = songYear;
                index++;
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return SongListYear;
    }

    /**
     * Retrieves the authors of all songs from the "Songs" table.
     *
     * @return An array of song authors.
     */

    public String[] getSongsInfoAuthor() {
        Connection conn = null;
        Statement stmt = null;
        String songAuthor = null;

        int index = 0;
        String[] SongListAuthor = new String[8000];

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"Songs\"");
            while (rs.next()) {

                songAuthor = rs.getString("Author");
                SongListAuthor[index] = songAuthor;
                index++;
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return SongListAuthor;
    }

    /**
     * Retrieves the information of a single user based on the provided email.
     *
     * @param email The email of the user.
     * @return An array containing the user's ID, name, email, password, and last
     *         use date.
     */

    public String[] getSingleUserInfo(String email) {
        Connection conn = null;
        Statement stmt = null;
        String UserId = null;
        String UserEmail = null;
        String UserName = null;
        String UserPassword = null;
        String UserLastUse = null;
        String[] UserInfo = new String[5];
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"Users\"");
            while (rs.next()) {
                UserEmail = rs.getString("UserEmail");
                if (UserEmail.equals(email)) {
                    UserId = rs.getString("UserID");
                    UserName = rs.getString("UserName");
                    UserLastUse = rs.getString("UserLastUse");
                    UserPassword = rs.getString("UserPassword");

                    break;
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        UserInfo[2] = UserEmail;
        UserInfo[1] = UserName;
        UserInfo[4] = UserLastUse;
        UserInfo[3] = UserPassword;
        UserInfo[0] = UserId;

        return UserInfo;
    }

    /**
     * Retrieves the information of a single playlist based on the provided playlist
     * name.
     *
     * @param playlistname The name of the playlist.
     * @return An array containing the playlist's name and author ID.
     */

    public String[] getSinglePlaylistInfo(String playlistname) {
        Connection conn = null;
        Statement stmt = null;
        String PlaylistName = null;
        String PlaylistAuthor = null;
        String[] PlaylistInfo = new String[2];
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"Playlist\"");
            while (rs.next()) {
                PlaylistName = rs.getString("PlaylistName");
                if (PlaylistName.equals(playlistname)) {
                    PlaylistAuthor = rs.getString("AuthorID");
                    break;
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        PlaylistInfo[0] = PlaylistName;
        PlaylistInfo[1] = PlaylistAuthor;
        return PlaylistInfo;
    }

    /**
     * Retrieves the information of a single song based on the provided song name,
     * including emotional ratings.
     *
     * @param songName The name of the song.
     * @return An array containing the song's ID, title, artist, year, emotional
     *         ratings, and review counter.
     */

    public String[] getSingleSongInfoWithEmotion(String SongName) {
        Connection conn = null;
        Statement stmt = null;
        String SongId = null;
        String SongTitle = null;
        String SongArtist = null;
        String SongYear = null;
        String Amazement = null;
        String Solemnity = null;
        String Tenderness = null;
        String Nostalgia = null;
        String Calmness = null;
        String Power = null;
        String Joy = null;
        String Tension = null;
        String Sadness = null;
        int counter = 0;
        String[] Song = new String[14];

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String query = "SELECT * FROM public.\"Songs\" WHERE \"Title\" = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, SongName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                try {
                    SongId = rs.getString("SongID");
                } catch (Exception e) {
                }
                try {
                    SongTitle = rs.getString("Title");
                } catch (Exception e) {
                }
                try {
                    SongArtist = rs.getString("Author");
                } catch (Exception e) {
                }
                try {
                    SongYear = rs.getString("Year");
                } catch (Exception e) {
                }
                try {
                    Amazement = rs.getString("Amazement");
                } catch (Exception e) {
                }
                try {
                    Solemnity = rs.getString("Solemnity");
                } catch (Exception e) {
                }
                try {
                    Tenderness = rs.getString("Tenderness");
                } catch (Exception e) {
                }
                try {
                    Nostalgia = rs.getString("Nostalgia");
                } catch (Exception e) {
                }
                try {
                    Calmness = rs.getString("Calmness");
                } catch (Exception e) {
                }
                try {
                    Power = rs.getString("Power");
                } catch (Exception e) {
                }
                try {
                    Joy = rs.getString("Joy");
                } catch (Exception e) {
                }
                try {
                    Tension = rs.getString("Tension");
                } catch (Exception e) {
                }
                try {
                    Sadness = rs.getString("Sadness");
                } catch (Exception e) {
                }

                try {
                    counter = rs.getInt("ReviewTotal");
                } catch (Exception e) {
                }

                Song[0] = SongId;
                Song[1] = SongTitle;
                Song[2] = SongArtist;
                Song[3] = SongYear;
                Song[4] = Amazement;
                Song[5] = Solemnity;
                Song[6] = Tenderness;
                Song[7] = Nostalgia;
                Song[8] = Calmness;
                Song[9] = Power;
                Song[10] = Joy;
                Song[11] = Tension;
                Song[12] = Sadness;
                Song[13] = String.valueOf(counter);

            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Song;
    }

    /**
     * Retrieves the information of a single song based on the provided song name.
     *
     * @param songName The name of the song.
     * @return An array containing the song's name, artist, and album.
     */

    public String[] getSingleSongInfo(String SongName) {
        Connection conn = null;
        Statement stmt = null;
        String songname = null;
        String artist = null;
        String album = null;
        String[] SongInfo = new String[3];
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"Songs\"");
            while (rs.next()) {
                songname = rs.getString("Title");
                if (songname.equals(SongName)) {
                    artist = rs.getString("Artist");
                    album = rs.getString("Album");
                    break;
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        SongInfo[0] = songname;
        SongInfo[1] = artist;
        SongInfo[2] = album;
        return SongInfo;
    }
}

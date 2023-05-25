package resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.time.LocalDate;
import java.sql.Date;

public class DbConnector {

    private final String url = "jdbc:postgresql://localhost/postgres";
    private final String user = "postgres";
    private final String password = "ciao1234";

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

    public Boolean checkUserExist(String emailToCheck) {
        Boolean check = false;
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"Users\"");
            while (rs.next()) {
                String emailInDb= rs.getString("UserEmail");
                if (emailToCheck.equals(emailInDb) ) {
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

    public Boolean registerUser(String emailToRegister, String UsernameToRegister, String passwordToRegister) {
        Boolean check = false;
        Connection conn = null;
        Statement stmt = null;
        Random random = new Random();
        int randomId = random.nextInt(Integer.MAX_VALUE);
        LocalDate currentDate = LocalDate.now();
        Date date = Date.valueOf(currentDate);
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            String query = "INSERT INTO public.\"Users\" (\"UserID\",\"UserEmail\",\"UserName\",\"UserPassword\",\"UserLastUse\") VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, randomId);
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

    public String[] getSongsInfo() {
        Connection conn = null;
        Statement stmt = null;
        String songName = null;
        int index = 0;
        String[] SongList = new String[3000];
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"Songs\"");
            while (rs.next()) {
                songName = rs.getString("SongName");
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

    public String[] getSingleUserInfo(String email) {
        Connection conn = null;
        Statement stmt = null;
        String UserEmail = null;
        String UserName = null;
        String UserLastUse = null;
        String[] UserInfo = new String[3];
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"Users\"");
            while (rs.next()) {
                UserEmail = rs.getString("UserEmail");
                if (UserEmail.equals(email)) {
                    UserName = rs.getString("UserName");
                    UserLastUse = rs.getString("UserLastUse");
                    break;
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        UserInfo[0] = UserEmail;
        UserInfo[1] = UserName;
        UserInfo[2] = UserLastUse;
        return UserInfo;
    }

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
                songname = rs.getString("SongName");
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

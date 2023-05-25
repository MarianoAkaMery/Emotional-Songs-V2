package resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The DbConnector class provides methods to establish a connection with a PostgreSQL database and perform various database operations.
 */
public class DbConnector {
    
    private final String url = "jdbc:postgresql://localhost/postgres";
    private final String user = "postgres";
    private final String password = "ciao1234";

    /**
     * Establishes a connection with the PostgreSQL database.
     *
     * @return The Connection object representing the database connection.
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
     * Retrieves the admin information from the database and verifies the given email and password.
     *
     * @param emailGiven    The email provided for authentication
     * @param passwordGiven The password provided for authentication
     * @return True if the email and password match an admin in the database, False otherwise.
     */
    public Boolean getAdminInfo(String emailGiven, String passwordGiven) {
        Connection conn = null;
        Statement stmt = null;
        String emailAdminList = null;
        String passwordAdminList = null;
        Boolean check = false;

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"AdminInfo\"");
            while (rs.next()) {
                emailAdminList = rs.getString("Email");
                passwordAdminList = rs.getString("Password");
                if (emailAdminList.equals(emailGiven) & passwordAdminList.equals(passwordGiven)) {
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
     * Retrieves the user emails from the database.
     *
     * @return An array of user emails.
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
                index = index + 1;
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
     * Retrieves the playlist names from the database.
     *
     * @return An array of playlist names.
     */
    public String[] getPlaylistInfo() {
        Connection conn = null;
        Statement stmt = null;
        String PlaylistName = null;
        int index = 0;
        String[] PlaylistList = new String[3000];

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"Playlist\"");
            while (rs.next()) {
                PlaylistName = rs.getString("PlaylistName");
                PlaylistList[index] = PlaylistName;
                index = index + 1;
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
     * Retrieves the song names from the database.
     *
     * @return An array of song names.
     */
    public String[] getSongsInfo() {
        Connection conn = null;
        Statement stmt = null;
        String SongName = null;
        int index = 0;
        String[] SongList = new String[3000];

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"Songs\"");
            while (rs.next()) {
                SongName = rs.getString("SongName");
                SongList[index] = SongName;
                index = index + 1;
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
     * Retrieves the information of a single user based on the email.
     *
     * @param email The email of the user to retrieve information for.
     * @return An array containing the user's ID, email, name, and last use.
     */
    public String[] getSingleUserInfo(String email) {
        Connection conn = null;
        Statement stmt = null;
        String UserId = null;
        String UserEmail = null;
        String UserName = null;
        String UserLastUse = null;
        String[] User = new String[4];

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String query = "SELECT * FROM public.\"Users\" WHERE \"UserEmail\" = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                try {
                    UserId = rs.getString("UserID");
                } catch (Exception e) {
                }
                try {
                    UserEmail = rs.getString("UserEmail");
                } catch (Exception e) {
                }
                try {
                    UserName = rs.getString("UserName");
                } catch (Exception e) {
                }
                try {
                    UserLastUse = rs.getString("UserLastUse");
                } catch (Exception e) {
                }

                User[0] = UserId;
                User[1] = UserEmail;
                User[2] = UserName;
                User[3] = UserLastUse;
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return User;
    }

    /**
     * Retrieves the information of a single playlist based on the playlist name.
     *
     * @param playlistname The name of the playlist to retrieve information for.
     * @return An array containing the playlist's ID, author ID, song list, name, and last edit.
     */
    public String[] getSinglePlaylistInfo(String playlistname) {
        Connection conn = null;
        Statement stmt = null;
        String PlaylistId = null;
        String PlaylistAuthor = null;
        String SongList = null;
        String PlaylistName = null;
        String PlaylistLastEdit = null;
        String[] Playlist = new String[5];

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String query = "SELECT * FROM public.\"Playlist\" WHERE \"PlaylistName\" = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, playlistname);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                try {
                    PlaylistId = rs.getString("PlaylistID");
                } catch (Exception e) {
                }
                try {
                    PlaylistAuthor = rs.getString("AuthorID");
                } catch (Exception e) {
                }
                try {
                    SongList = rs.getString("SongList");
                } catch (Exception e) {
                }
                try {
                    PlaylistName = rs.getString("PlaylistName");
                } catch (Exception e) {
                }
                try {
                    PlaylistLastEdit = rs.getString("LastEdit");
                } catch (Exception e) {
                }

                Playlist[0] = PlaylistId;
                Playlist[1] = PlaylistAuthor;
                Playlist[2] = SongList;
                Playlist[3] = PlaylistName;
                Playlist[4] = PlaylistLastEdit;
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Playlist;
    }

    /**
     * Retrieves the information of a single song based on the song name.
     *
     * @param SongName The name of the song to retrieve information for.
     * @return An array containing the song's ID, title, artist, year, and emotion values.
     */
    public String[] getSingleSongInfo(String SongName) {
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
        String[] Song = new String[13];

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String query = "SELECT * FROM public.\"Songs\" WHERE \"SongName\" = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, SongName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                try {
                    SongId = rs.getString("SongID");
                } catch (Exception e) {
                }
                try {
                    SongTitle = rs.getString("SongName");
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
     * Inserts a new admin into the database.
     *
     * @param email    The email of the admin.
     * @param password The password of the admin.
     */
    public void insertAdmin(String email, String password) {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            String query = "INSERT INTO public.\"AdminInfo\" (\"Email\", \"Password\") VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            pstmt.executeUpdate();

            pstmt.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Inserts a new user into the database.
     *
     * @param email The email of the user.
     * @param name  The name of the user.
     */
    public void insertUser(String email, String name) {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            String query = "INSERT INTO public.\"Users\" (\"UserEmail\", \"UserName\") VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, name);
            pstmt.executeUpdate();

            pstmt.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Inserts a new playlist into the database.
     *
     * @param playlistName The name of the playlist.
     * @param authorID     The ID of the playlist author.
     * @param songList     The list of songs in the playlist.
     */
    public void insertPlaylist(String playlistName, String authorID, String songList) {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            String query = "INSERT INTO public.\"Playlist\" (\"PlaylistName\", \"AuthorID\", \"SongList\") VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, playlistName);
            pstmt.setString(2, authorID);
            pstmt.setString(3, songList);
            pstmt.executeUpdate();

            pstmt.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Inserts a new song into the database.
     *
     * @param songName    The name of the song.
     * @param author      The author of the song.
     * @param year        The year of the song.
     * @param amazement   The amazement value of the song.
     * @param solemnity   The solemnity value of the song.
     * @param tenderness  The tenderness value of the song.
     * @param nostalgia   The nostalgia value of the song.
     * @param calmness    The calmness value of the song.
     * @param power       The power value of the song.
     * @param joy         The joy value of the song.
     * @param tension     The tension value of the song.
     * @param sadness     The sadness value of the song.
     */
    public void insertSong(String songName, String author, String year, String amazement, String solemnity,
                           String tenderness, String nostalgia, String calmness, String power, String joy,
                           String tension, String sadness) {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            String query = "INSERT INTO public.\"Songs\" (\"SongName\", \"Author\", \"Year\", \"Amazement\", \"Solemnity\", " +
                    "\"Tenderness\", \"Nostalgia\", \"Calmness\", \"Power\", \"Joy\", \"Tension\", \"Sadness\") " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, songName);
            pstmt.setString(2, author);
            pstmt.setString(3, year);
            pstmt.setString(4, amazement);
            pstmt.setString(5, solemnity);
            pstmt.setString(6, tenderness);
            pstmt.setString(7, nostalgia);
            pstmt.setString(8, calmness);
            pstmt.setString(9, power);
            pstmt.setString(10, joy);
            pstmt.setString(11, tension);
            pstmt.setString(12, sadness);
            pstmt.executeUpdate();

            pstmt.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates the information of a user in the database.
     *
     * @param email The email of the user to update information for.
     * @param name  The updated name of the user.
     */
    public void updateUser(String email, String name) {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String query = "UPDATE public.\"Users\" SET \"UserName\" = ? WHERE \"UserEmail\" = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.executeUpdate();

            pstmt.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates the information of a playlist in the database.
     *
     * @param playlistName The name of the playlist to update information for.
     * @param songList     The updated list of songs in the playlist.
     */
    public void updatePlaylist(String playlistName, String songList) {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String query = "UPDATE public.\"Playlist\" SET \"SongList\" = ? WHERE \"PlaylistName\" = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, songList);
            pstmt.setString(2, playlistName);
            pstmt.executeUpdate();

            pstmt.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates the information of a song in the database.
     *
     * @param songName    The name of the song to update information for.
     * @param author      The updated author of the song.
     * @param year        The updated year of the song.
     * @param amazement   The updated amazement value of the song.
     * @param solemnity   The updated solemnity value of the song.
     * @param tenderness  The updated tenderness value of the song.
     * @param nostalgia   The updated nostalgia value of the song.
     * @param calmness    The updated calmness value of the song.
     * @param power       The updated power value of the song.
     * @param joy         The updated joy value of the song.
     * @param tension     The updated tension value of the song.
     * @param sadness     The updated sadness value of the song.
     */
    public void updateSong(String songName, String author, String year, String amazement, String solemnity,
                           String tenderness, String nostalgia, String calmness, String power, String joy,
                           String tension, String sadness) {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String query = "UPDATE public.\"Songs\" SET \"Author\" = ?, \"Year\" = ?, \"Amazement\" = ?, " +
                    "\"Solemnity\" = ?, \"Tenderness\" = ?, \"Nostalgia\" = ?, \"Calmness\" = ?, \"Power\" = ?, " +
                    "\"Joy\" = ?, \"Tension\" = ?, \"Sadness\" = ? WHERE \"SongName\" = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, author);
            pstmt.setString(2, year);
            pstmt.setString(3, amazement);
            pstmt.setString(4, solemnity);
            pstmt.setString(5, tenderness);
            pstmt.setString(6, nostalgia);
            pstmt.setString(7, calmness);
            pstmt.setString(8, power);
            pstmt.setString(9, joy);
            pstmt.setString(10, tension);
            pstmt.setString(11, sadness);
            pstmt.setString(12, songName);
            pstmt.executeUpdate();

            pstmt.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes a user from the database.
     *
     * @param email The email of the user to delete.
     */
    public boolean deleteUser(String email) {
        Connection conn = null;
        Statement stmt = null;
        boolean success= false;


        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String query = "DELETE FROM public.\"Users\" WHERE \"UserEmail\" = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.executeUpdate();

            pstmt.close();
            stmt.close();
            conn.close();
            success= true;


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            success= false;

        }

        return success;

    }

    /**
     * Deletes a playlist from the database.
     *
     * @param playlistName The name of the playlist to delete.
     */
    public void deletePlaylist(String playlistName) {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String query = "DELETE FROM public.\"Playlist\" WHERE \"PlaylistName\" = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, playlistName);
            pstmt.executeUpdate();

            pstmt.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes a song from the database.
     *
     * @param songName The name of the song to delete.
     */
    public boolean deleteSong(String songName) {
        Connection conn = null;
        Statement stmt = null;
        boolean success= false;


        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String query = "DELETE FROM public.\"Songs\" WHERE \"SongName\" = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, songName);
            pstmt.executeUpdate();

            pstmt.close();
            stmt.close();
            conn.close();
            success= true;


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            success= false;

        }

        return success;

    }

    /**
     * Checks if an email exists in the database.
     *
     * @param email The email to check.
     * @return True if the email exists, false otherwise.
     */
    public boolean checkEmailExists(String email) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean exists = false;

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String query = "SELECT \"Email\" FROM public.\"AdminInfo\" WHERE \"Email\" = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();

            exists = rs.next();

            rs.close();
            pstmt.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return exists;
    }

    /**
     * Checks if a playlist name exists in the database.
     *
     * @param playlistName The playlist name to check.
     * @return True if the playlist name exists, false otherwise.
     */
    public boolean checkPlaylistExists(String playlistName) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean exists = false;

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String query = "SELECT \"PlaylistName\" FROM public.\"Playlist\" WHERE \"PlaylistName\" = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, playlistName);
            rs = pstmt.executeQuery();

            exists = rs.next();

            rs.close();
            pstmt.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return exists;
    }

    /**
     * Checks if a song name exists in the database.
     *
     * @param songName The song name to check.
     * @return True if the song name exists, false otherwise.
     */
    public boolean checkSongExists(String songName) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean exists = false;

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String query = "SELECT \"SongName\" FROM public.\"Songs\" WHERE \"SongName\" = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, songName);
            rs = pstmt.executeQuery();

            exists = rs.next();

            rs.close();
            pstmt.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return exists;
    }
}

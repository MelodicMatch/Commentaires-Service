package controllers;

import kafka.Producer;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseController {
    static private String connectionURL = "jdbc:mysql://localhost:3306/commentaires";
    static private String user = "root";
    static private String password = "alpha76";

    private Connection getConn() throws SQLException {
        return DriverManager.getConnection(connectionURL, user, password);
    }

    public List<MusicComment> getMusicComments(int musicID, Sorting sort, int amount, int startAt) {
        List<MusicComment> list = new ArrayList<MusicComment>();

        try {
            Connection conn = getConn();

            String query = "SELECT * FROM musicComments WHERE musicID = ? " + sort.toSQL() + "LIMIT ?, ?;";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, musicID);
            statement.setInt(2, startAt-1);
            statement.setInt(3, amount);

            ResultSet results = statement.executeQuery();

            MusicComment comment;
            while (results.next()) {
                comment = new MusicComment();

                comment.setID(results.getInt(1));
                comment.setUserID(results.getInt(3));
                comment.setReplyTo(results.getInt(4));
                if (results.wasNull()) {
                    comment.setReplyTo(null);
                }
                comment.setRating(results.getInt(5));
                if (results.wasNull()) {
                    comment.setRating(null);
                }
                comment.setContent(results.getString(6));
                comment.setDate(new Timestamp(results.getDate(7).getTime()).toLocalDateTime());

                list.add(comment);
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return list;
    }

    public List<EventComment> getEventComments(int musicID, Sorting sort, int amount, int startAt) {
        List<EventComment> list = new ArrayList<EventComment>();

        try {
            Connection conn = getConn();

            String query = "SELECT * FROM eventComments WHERE eventID = ? " + sort.toSQL() + "LIMIT ?, ?;";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, musicID);
            statement.setInt(2, startAt-1);
            statement.setInt(3, amount);

            ResultSet results = statement.executeQuery();

            EventComment comment;
            while (results.next()) {
                comment = new EventComment();

                comment.setID(results.getInt(1));
                comment.setUserID(results.getInt(3));
                comment.setReplyTo(results.getInt(4));
                if (results.wasNull()) {
                    comment.setReplyTo(null);
                }
                comment.setContent(results.getString(5));
                comment.setDate(new Timestamp(results.getDate(6).getTime()).toLocalDateTime());

                list.add(comment);
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return list;
    }

    public List<PlaylistComment> getPlaylistComments(int musicID, Sorting sort, int amount, int startAt) {
        List<PlaylistComment> list = new ArrayList<PlaylistComment>();

        try {
            Connection conn = getConn();

            String query = "SELECT * FROM playlistComments WHERE playlistID = ? " + sort.toSQL() + "LIMIT ?, ?;";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, musicID);
            statement.setInt(2, startAt-1);
            statement.setInt(3, amount);

            ResultSet results = statement.executeQuery();

            PlaylistComment comment;
            while (results.next()) {
                comment = new PlaylistComment();

                comment.setID(results.getInt(1));
                comment.setUserID(results.getInt(3));
                comment.setReplyTo(results.getInt(4));
                if (results.wasNull()) {
                    comment.setReplyTo(null);
                }
                comment.setContent(results.getString(5));
                comment.setDate(new Timestamp(results.getDate(6).getTime()).toLocalDateTime());

                list.add(comment);
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return list;
    }

    public int addMusicComment(MusicComment comment) throws SQLException {
        int id;

        try {
            Connection conn = getConn();
            conn.setAutoCommit(false);

            String query = "INSERT INTO musicComments (musicID, userID, replyTo, rating, content) " +
                    "VALUES (?, ?, ?, ?, ?);";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, comment.getMusicID());
            statement.setInt(2, comment.getUserID());
            if (comment.getReplyTo() == null) {
                statement.setNull(3, Types.INTEGER);
            } else {
                statement.setInt(3, comment.getReplyTo());
            }
            if (comment.getRating() == null) {
                statement.setNull(4, Types.INTEGER);
            } else {
                statement.setInt(4, comment.getRating());
            }
            statement.setString(5, comment.getContent());

            statement.execute();

            query = "SELECT LAST_INSERT_ID() ;";

            statement = conn.prepareStatement(query);

            ResultSet result = statement.executeQuery();

            result.next();
            id = result.getInt(1);

            conn.commit();
            conn.close();

            new Producer().sendMessage(comment.getUserID(), comment.getReplyTo(), "music", comment.getMusicID());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }

        return id;
    }

    public int addEventComment(EventComment comment) throws SQLException {
        int id;

        try {
            Connection conn = getConn();
            conn.setAutoCommit(false);

            String query = "INSERT INTO eventComments (eventID, userID, replyTo, content) " +
                    "VALUES (?, ?, ?, ?);";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, comment.getEventID());
            statement.setInt(2, comment.getUserID());
            if (comment.getReplyTo() == null) {
                statement.setNull(3, Types.INTEGER);
            } else {
                statement.setInt(3, comment.getReplyTo());
            }
            statement.setString(4, comment.getContent());

            statement.execute();

            query = "SELECT LAST_INSERT_ID() ;";

            statement = conn.prepareStatement(query);

            ResultSet result = statement.executeQuery();

            result.next();
            id = result.getInt(1);

            conn.commit();
            conn.close();

            new Producer().sendMessage(comment.getUserID(), comment.getReplyTo(), "event", comment.getEventID());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }

        return id;
    }

    public int addPlaylistComment(PlaylistComment comment) throws SQLException {
        int id;

        try {
            Connection conn = getConn();
            conn.setAutoCommit(false);

            String query = "INSERT INTO playlistComments (playlistID, userID, replyTo, content) " +
                    "VALUES (?, ?, ?, ?);";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, comment.getPlaylistID());
            statement.setInt(2, comment.getUserID());
            if (comment.getReplyTo() == null) {
                statement.setNull(3, Types.INTEGER);
            } else {
                statement.setInt(3, comment.getReplyTo());
            }
            statement.setString(4, comment.getContent());

            statement.execute();

            query = "SELECT LAST_INSERT_ID() ;";

            statement = conn.prepareStatement(query);

            ResultSet result = statement.executeQuery();

            result.next();
            id = result.getInt(1);

            conn.commit();
            conn.close();

            new Producer().sendMessage(comment.getUserID(), comment.getReplyTo(), "playlist", comment.getPlaylistID());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }

        return id;
    }

    public int deleteEventComment(int id) {
        try {
            Connection conn = getConn();

            String query = "UPDATE eventComments SET userID = 0, content = 'Deleted by user.' WHERE eventCommentID = ?;";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            statement.execute();

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }

        return 1;
    }

    public int deleteMusicComment(int id) {
        try {
            Connection conn = getConn();

            String query = "UPDATE musicComments SET userID = 0, content = 'Deleted by user.', rating = NULL WHERE musicCommentID = ?;";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            statement.execute();

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }

        return 1;
    }

    public int deletePlaylistComment(int id) {
        try {
            Connection conn = getConn();

            String query = "UPDATE playlistComments SET userID = 0, content = 'Deleted by user.' WHERE playlistCommentID = ?;";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            statement.execute();

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }

        return 1;
    }

    public int canRateMusic(int user, int id) {
        int count;
        try {
            Connection conn = getConn();

            String query = "SELECT COUNT(*) FROM musicComments WHERE userID = ? AND musicID = ?;";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, user);
            statement.setInt(2, id);

            ResultSet result = statement.executeQuery();

            result.next();

            count = result.getInt(1);

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }

        return (1-count);
    }

    public int deleteUserComments(int user) {
        try {
            Connection conn = getConn();
            conn.setAutoCommit(false);

            String query = "UPDATE eventComments SET userID = 0, content = 'User deleted.' WHERE userID = ?;";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, user);

            statement.execute();

            query = "UPDATE playlistComments SET userID = 0, content = 'User deleted.' WHERE userID = ?;";

            statement = conn.prepareStatement(query);
            statement.setInt(1, user);

            statement.execute();

            query = "UPDATE musicComments SET userID = 0, content = 'User deleted.' WHERE userID = ?;";

            statement = conn.prepareStatement(query);
            statement.setInt(1, user);

            statement.execute();

            conn.commit();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }

        return 1;
    }
}

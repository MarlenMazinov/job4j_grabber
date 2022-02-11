package ru.job4j.grabber;

import ru.job4j.html.Post;
import ru.job4j.html.SqlRuParse;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {

    private Connection cnn;

    public PsqlStore(Properties cfg) throws SQLException {
        try {
            Class.forName(cfg.getProperty("driver-class-name"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        cnn = DriverManager.getConnection(
                cfg.getProperty("url"),
                cfg.getProperty("username"),
                cfg.getProperty("password")
        );
    }

    @Override
    public void save(Post post) {
        try (PreparedStatement statement =
                     cnn.prepareStatement("insert into posts(id, name, text, link, created)"
                             + "values (?, ?, ?, ?, ?)")) {
            statement.setInt(1, post.getId());
            statement.setString(2, post.getTitle());
            statement.setString(3, post.getDescription());
            statement.setString(4, post.getLink());
            statement.setTimestamp(5, Timestamp.valueOf(post.getCreated()));
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> rsl = new ArrayList<>();
        try (PreparedStatement statement = cnn.prepareStatement("select * from posts")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    rsl.add(new Post(resultSet.getInt("id"), resultSet.getString("name"),
                            resultSet.getString("link"), resultSet.getString("text"),
                            resultSet.getTimestamp("created").toLocalDateTime()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public Post findById(int id) {
        Post rsl = null;
        try (PreparedStatement statement =
                     cnn.prepareStatement("select * from posts where id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    rsl = new Post(resultSet.getInt("id"), resultSet.getString("name"),
                            resultSet.getString("link"), resultSet.getString("text"),
                            resultSet.getTimestamp("created").toLocalDateTime());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }

    public static void main(String[] args) {
        SqlRuParse sqlRuParse = new SqlRuParse(new SqlRuDateTimeParser());
        try (InputStream in = PsqlStore.class.getClassLoader().
                getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            PsqlStore psqlStore = new PsqlStore(config);
            psqlStore.save(sqlRuParse.detail("https://www.sql.ru/forum/1325330/lidy-be-fe-senior"
                    + "-cistemnye-analitiki-qa-i-devops-moskva-do-200t"));
            psqlStore.getAll();
            System.out.println(psqlStore.findById(psqlStore.getAll().get(0).getId()));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
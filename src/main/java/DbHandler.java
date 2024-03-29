import measure.Measure;
import org.sqlite.JDBC;

import java.sql.*;
import java.util.*;

public class DbHandler {
    private static final String DB_CONNECTION = "jdbc:sqlite:measures.db";

    private static DbHandler instance = null;

    public static synchronized DbHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DbHandler();
        return instance;
    }

    private Connection connection;

    private DbHandler() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection(DB_CONNECTION);
    }

    public List<Measure> getAllMeasures() {
        try (Statement statement = this.connection.createStatement()) {
            List<Measure> result = new ArrayList<>();
            ResultSet set = statement.executeQuery("SELECT id, date, temperature, humidity, pressure FROM measure");
            while (set.next()) {
                result.add(new Measure(set.getInt("id"), set.getString("date"), set.getDouble("temperature"),
                        set.getDouble("humidity"), set.getDouble("pressure")));
            }
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void addMeasure(Measure measure) {
        try (PreparedStatement ps = this.connection
                .prepareStatement("INSERT INTO measure(`date`, `temperature`, `humidity`, `pressure`) " + "VALUES(?, ?, ?, ?)")) {
            ps.setObject(1, measure.getDate());
            ps.setObject(2, measure.getTemperature());
            ps.setObject(3, measure.getHumidity());
            ps.setObject(4, measure.getPressure());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

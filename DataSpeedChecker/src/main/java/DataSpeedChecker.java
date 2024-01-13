import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DataSpeedChecker {

    public static void main(String[] args) {

        String host = "mhlxr74qa01";
        Integer port = 1521;
        String username = "shraddha";
        String password = "shraddha";
        String database = "pdb1.informatica.com";

        String connectString = String.format("jdbc:oracle:thin:@//%s:%d/%s", host, port, database);

        String sqlQuery = "SELECT COUNT(*) FROM TPCH.LINEITEM_SF10 upt ";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectString, username, password);
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                Date start = new Date();

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Retrieve the value of COUNT(*)
                        int count = resultSet.getInt(1);
                        System.out.println("Total count of records: " + count);
                    }
                }
                Date end = new Date();
                long elapsedTime = (end.getTime() - start.getTime()) / 1000;

                System.out.println("Fetch completed in " + elapsedTime + " seconds");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

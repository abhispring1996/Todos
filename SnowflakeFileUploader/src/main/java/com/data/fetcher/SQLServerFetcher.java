package com.data.fetcher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLServerFetcher {

    public static void main(String[] args) {

        String classpath = System.getProperty("java.class.path");
        if (classpath.contains("dwsqlserver-6.0.0.001765.jar")) {
            System.out.println("JAR is present in classpath!");
        } else {
            System.out.println("JAR is missing from classpath.");
        }

        String connectString = "jdbc:sqlserver://EMSQLDB4:1433;DatabaseName=MIDBSRC;LoginTimeout=60;QueryTimeout=60;EnableCancelTimeout=true;encrypt=false";
        String infaConnectString = "jdbc:informatica:sqlserver://EMSQLDB4:1433;DatabaseName=MIDBSRC;LoginTimeout=60;QueryTimeout=60;EnableCancelTimeout=true;encrypt=false;responseBuffering=adaptive";

        String username = "dbmi01";
        String password = "dbmi@123";

        String query ="SELECT [L_ORDERKEY], [L_PARTKEY], [L_SUPPKEY], [L_LINENUMBER], [L_QUANTITY], [L_EXTENDEDPRICE]," +
                " [L_DISCOUNT], [L_TAX], [L_RETURNFLAG], [L_LINESTATUS], [L_SHIPDATE], [L_COMMITDATE], [L_RECEIPTDATE]," +
                " [L_SHIPINSTRUCT], [L_SHIPMODE], [L_COMMENT] FROM [MIDBSRC].[dbo].[LINEITEM_SF1_noElement]";

        try  {
            Connection connection = DriverManager.getConnection(infaConnectString, username, password);
            Statement statement = connection.createStatement();
            statement.setFetchSize(500);
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("Connection established. Fetching data...");
            int recordsIterated = 0;
            // Iterate over the result set
            while (resultSet.next()) {
                recordsIterated++;

                if(recordsIterated==1000){
                    break;
                }
            }
            System.out.println("Closing the resultSet");
            Statement resultSetStatement = resultSet.getStatement();
            resultSetStatement.cancel();
            resultSetStatement.close();
            resultSet.close();
            System.out.println("Is ResultSetClosed " + resultSet.isClosed());
//            System.out.println("ResultSet closed");

        } catch (SQLException e) {
            System.out.println(e);
        }

        System.out.println("Was able to break out");
    }
}
